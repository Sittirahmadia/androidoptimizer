package com.redmi14c.optimizer.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.redmi14c.optimizer.data.*
import com.redmi14c.optimizer.shizuku.ShizukuManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OptimizerViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = TweakDataStore(application)
    private val database = AppDatabase.getDatabase(application)
    private val customStringDao = database.customStringDao()
    private val gameProfileDao = database.gameProfileDao()
    private val backupDao = database.backupDao()

    // Shizuku Status
    private val _shizukuStatus = MutableStateFlow(ShizukuStatus.NOT_RUNNING)
    val shizukuStatus: StateFlow<ShizukuStatus> = _shizukuStatus.asStateFlow()

    // Active Tweaks Count
    private val _activeTweaksCount = MutableStateFlow(0)
    val activeTweaksCount: StateFlow<Int> = _activeTweaksCount.asStateFlow()

    // Commands Preview
    private val _commandsPreview = MutableStateFlow<List<String>>(emptyList())
    val commandsPreview: StateFlow<List<String>> = _commandsPreview.asStateFlow()

    // Apply Result
    private val _applyResult = MutableStateFlow<ApplyResult?>(null)
    val applyResult: StateFlow<ApplyResult?> = _applyResult.asStateFlow()

    // Tweak States
    val beastMode = dataStore.beastModeEnabled
    val refreshRate = dataStore.refreshRateEnabled
    val highPerf = dataStore.highPerfEnabled
    val batteryOpt = dataStore.batteryOptEnabled
    val reduceAnim = dataStore.reduceAnimEnabled
    val gpuRendering = dataStore.gpuRenderingEnabled
    val hwAccel = dataStore.hwAccelEnabled
    val touchBoost = dataStore.touchBoostEnabled
    val ramManager = dataStore.ramManagerEnabled
    val networkOpt = dataStore.networkOptEnabled
    val cpuGovernor = dataStore.cpuGovernorEnabled
    val freqLock = dataStore.freqLockEnabled
    val disableBlur = dataStore.disableBlurEnabled
    val appLaunch = dataStore.appLaunchEnabled
    val scheduler = dataStore.schedulerEnabled
    val zram = dataStore.zramEnabled
    val adaptiveBattery = dataStore.adaptiveBatteryEnabled
    val msaa = dataStore.msaaEnabled
    val thermal = dataStore.thermalEnabled
    val screenOff = dataStore.screenOffEnabled
    val autoTurbo = dataStore.autoTurboEnabled
    val fpsOverlay = dataStore.fpsOverlayEnabled
    val tempMonitor = dataStore.tempMonitorEnabled
    val disableSensors = dataStore.disableSensorsEnabled
    val ioReadahead = dataStore.ioReadaheadEnabled
    val disableBloat = dataStore.disableBloatEnabled
    val gpuTurbo = dataStore.gpuTurboEnabled
    val disableMiuiOpt = dataStore.disableMiuiOptEnabled
    val forceFps = dataStore.forceFpsEnabled
    val disableAutoBrightness = dataStore.disableAutoBrightnessEnabled
    val rendererType = dataStore.rendererType
    val cpuGovernorType = dataStore.cpuGovernorType
    val activeProfile = dataStore.activeProfile
    val language = dataStore.language
    val darkMode = dataStore.darkMode
    val materialYou = dataStore.materialYou

    // Database Flows
    val customStrings = customStringDao.getAll()
    val gameProfiles = gameProfileDao.getAll()
    val backups = backupDao.getAll()

    init {
        checkShizukuStatus()
        updateActiveTweaksCount()
    }

    fun checkShizukuStatus() {
        _shizukuStatus.value = when {
            !ShizukuManager.isShizukuRunning() -> ShizukuStatus.NOT_RUNNING
            !ShizukuManager.hasPermission() -> ShizukuStatus.NO_PERMISSION
            else -> ShizukuStatus.CONNECTED
        }
    }

    fun updateActiveTweaksCount() {
        viewModelScope.launch {
            val count = listOf(
                beastMode.first(),
                refreshRate.first(),
                highPerf.first(),
                batteryOpt.first(),
                reduceAnim.first(),
                gpuRendering.first(),
                hwAccel.first(),
                touchBoost.first(),
                ramManager.first(),
                networkOpt.first(),
                cpuGovernor.first(),
                freqLock.first(),
                disableBlur.first(),
                appLaunch.first(),
                scheduler.first(),
                zram.first(),
                adaptiveBattery.first(),
                msaa.first(),
                thermal.first(),
                screenOff.first(),
                autoTurbo.first(),
                fpsOverlay.first(),
                tempMonitor.first(),
                disableSensors.first(),
                ioReadahead.first(),
                disableBloat.first(),
                gpuTurbo.first(),
                disableMiuiOpt.first(),
                forceFps.first(),
                disableAutoBrightness.first()
            ).count { it }
            _activeTweaksCount.value = count
        }
    }

    fun generateCommandsPreview(tweakIds: List<String>) {
        val commands = mutableListOf<String>()
        tweakIds.forEach { id ->
            TweakCommands.getTweakById(id)?.let { tweak ->
                commands.add("# ${tweak.name}")
                commands.addAll(tweak.commands)
                commands.add("")
            }
        }
        _commandsPreview.value = commands
    }

    fun applyTweaks(tweakIds: List<String>) {
        viewModelScope.launch {
            if (!ShizukuManager.isShizukuRunning() || !ShizukuManager.hasPermission()) {
                _applyResult.value = ApplyResult.Error("Shizuku not connected or no permission")
                return@launch
            }

            val results = mutableListOf<ShellResult>()
            tweakIds.forEach { id ->
                TweakCommands.getTweakById(id)?.let { tweak ->
                    tweak.commands.forEach { cmd ->
                        results.add(ShizukuManager.executeCommand(cmd))
                    }
                }
            }

            val success = results.all { it.success }
            _applyResult.value = if (success) {
                ApplyResult.Success("Applied ${tweakIds.size} tweaks successfully")
            } else {
                ApplyResult.PartialSuccess("Some commands failed", results.filter { !it.success }.map { it.error })
            }

            updateActiveTweaksCount()
        }
    }

    fun applyBeastMode() {
        viewModelScope.launch {
            val beastTweaks = listOf(
                "beast_mode", "refresh_rate", "high_perf", "battery_opt",
                "reduce_anim", "gpu_rendering", "touch_boost", "ram_manager",
                "network_opt", "disable_blur", "app_launch", "disable_bloat",
                "gpu_turbo", "disable_miui_opt", "disable_auto_brightness"
            )
            applyTweaks(beastTweaks)
            dataStore.setBeastMode(true)
        }
    }

    fun resetAllTweaks() {
        viewModelScope.launch {
            val resetIds = TweakCommands.ALL_TWEAKS.map { it.id }
            val results = mutableListOf<ShellResult>()

            resetIds.forEach { id ->
                TweakCommands.getTweakById(id)?.let { tweak ->
                    tweak.resetCommands.forEach { cmd ->
                        results.add(ShizukuManager.executeCommand(cmd))
                    }
                }
            }

            dataStore.resetAll()
            _applyResult.value = ApplyResult.Success("All tweaks reset to default")
            updateActiveTweaksCount()
        }
    }

    fun applyProfile(profile: String) {
        viewModelScope.launch {
            when (profile) {
                "balanced" -> applyBalancedProfile()
                "extreme_fps" -> applyExtremeFpsProfile()
                "battery_saver" -> applyBatterySaverProfile()
                else -> {}
            }
            dataStore.setActiveProfile(profile)
        }
    }

    private suspend fun applyBalancedProfile() {
        val tweaks = listOf("reduce_anim", "app_launch", "disable_bloat", "network_opt", "touch_boost")
        applyTweaks(tweaks)
    }

    private suspend fun applyExtremeFpsProfile() {
        val tweaks = listOf(
            "beast_mode", "refresh_rate", "high_perf", "gpu_rendering",
            "gpu_turbo", "thermal", "cpu_governor", "freq_lock",
            "disable_blur", "ram_manager", "touch_boost"
        )
        applyTweaks(tweaks)
    }

    private suspend fun applyBatterySaverProfile() {
        val tweaks = listOf("adaptive_battery", "screen_off", "disable_sensors", "disable_bg")
        applyTweaks(tweaks)
    }

    // Toggle functions
    fun toggleBeastMode(enabled: Boolean) = viewModelScope.launch { dataStore.setBeastMode(enabled) }
    fun toggleRefreshRate(enabled: Boolean) = viewModelScope.launch { dataStore.setRefreshRate(enabled) }
    fun toggleHighPerf(enabled: Boolean) = viewModelScope.launch { dataStore.setHighPerf(enabled) }
    fun toggleBatteryOpt(enabled: Boolean) = viewModelScope.launch { dataStore.setBatteryOpt(enabled) }
    fun toggleReduceAnim(enabled: Boolean) = viewModelScope.launch { dataStore.setReduceAnim(enabled) }
    fun toggleGpuRendering(enabled: Boolean) = viewModelScope.launch { dataStore.setGpuRendering(enabled) }
    fun toggleHwAccel(enabled: Boolean) = viewModelScope.launch { dataStore.setHwAccel(enabled) }
    fun toggleTouchBoost(enabled: Boolean) = viewModelScope.launch { dataStore.setTouchBoost(enabled) }
    fun toggleRamManager(enabled: Boolean) = viewModelScope.launch { dataStore.setRamManager(enabled) }
    fun toggleNetworkOpt(enabled: Boolean) = viewModelScope.launch { dataStore.setNetworkOpt(enabled) }
    fun toggleCpuGovernor(enabled: Boolean) = viewModelScope.launch { dataStore.setCpuGovernor(enabled) }
    fun toggleFreqLock(enabled: Boolean) = viewModelScope.launch { dataStore.setFreqLock(enabled) }
    fun toggleDisableBlur(enabled: Boolean) = viewModelScope.launch { dataStore.setDisableBlur(enabled) }
    fun toggleAppLaunch(enabled: Boolean) = viewModelScope.launch { dataStore.setAppLaunch(enabled) }
    fun toggleScheduler(enabled: Boolean) = viewModelScope.launch { dataStore.setScheduler(enabled) }
    fun toggleZram(enabled: Boolean) = viewModelScope.launch { dataStore.setZram(enabled) }
    fun toggleAdaptiveBattery(enabled: Boolean) = viewModelScope.launch { dataStore.setAdaptiveBattery(enabled) }
    fun toggleMsaa(enabled: Boolean) = viewModelScope.launch { dataStore.setMsaa(enabled) }
    fun toggleThermal(enabled: Boolean) = viewModelScope.launch { dataStore.setThermal(enabled) }
    fun toggleScreenOff(enabled: Boolean) = viewModelScope.launch { dataStore.setScreenOff(enabled) }
    fun toggleAutoTurbo(enabled: Boolean) = viewModelScope.launch { dataStore.setAutoTurbo(enabled) }
    fun toggleFpsOverlay(enabled: Boolean) = viewModelScope.launch { dataStore.setFpsOverlay(enabled) }
    fun toggleTempMonitor(enabled: Boolean) = viewModelScope.launch { dataStore.setTempMonitor(enabled) }
    fun toggleDisableSensors(enabled: Boolean) = viewModelScope.launch { dataStore.setDisableSensors(enabled) }
    fun toggleIoReadahead(enabled: Boolean) = viewModelScope.launch { dataStore.setIoReadahead(enabled) }
    fun toggleDisableBloat(enabled: Boolean) = viewModelScope.launch { dataStore.setDisableBloat(enabled) }
    fun toggleGpuTurbo(enabled: Boolean) = viewModelScope.launch { dataStore.setGpuTurbo(enabled) }
    fun toggleDisableMiuiOpt(enabled: Boolean) = viewModelScope.launch { dataStore.setDisableMiuiOpt(enabled) }
    fun toggleForceFps(enabled: Boolean) = viewModelScope.launch { dataStore.setForceFps(enabled) }
    fun toggleDisableAutoBrightness(enabled: Boolean) = viewModelScope.launch { dataStore.setDisableAutoBrightness(enabled) }
    fun setRendererType(type: String) = viewModelScope.launch { dataStore.setRendererType(type) }
    fun setCpuGovernorType(type: String) = viewModelScope.launch { dataStore.setCpuGovernorType(type) }
    fun setLanguage(lang: String) = viewModelScope.launch { dataStore.setLanguage(lang) }
    fun setDarkMode(enabled: Boolean) = viewModelScope.launch { dataStore.setDarkMode(enabled) }
    fun setMaterialYou(enabled: Boolean) = viewModelScope.launch { dataStore.setMaterialYou(enabled) }

    // Database operations
    fun addCustomString(entry: CustomStringEntry) = viewModelScope.launch {
        customStringDao.insert(entry)
    }

    fun deleteCustomString(entry: CustomStringEntry) = viewModelScope.launch {
        customStringDao.delete(entry)
    }

    fun addGameProfile(profile: GameProfile) = viewModelScope.launch {
        gameProfileDao.insert(profile)
    }

    fun deleteGameProfile(profile: GameProfile) = viewModelScope.launch {
        gameProfileDao.delete(profile)
    }

    fun addBackup(backup: BackupEntry) = viewModelScope.launch {
        backupDao.insert(backup)
    }

    fun deleteBackup(backup: BackupEntry) = viewModelScope.launch {
        backupDao.delete(backup)
    }

    fun clearApplyResult() {
        _applyResult.value = null
    }
}

sealed class ShizukuStatus {
    object NOT_RUNNING : ShizukuStatus()
    object NO_PERMISSION : ShizukuStatus()
    object CONNECTED : ShizukuStatus()
}

sealed class ApplyResult {
    data class Success(val message: String) : ApplyResult()
    data class PartialSuccess(val message: String, val errors: List<String>) : ApplyResult()
    data class Error(val message: String) : ApplyResult()
}
