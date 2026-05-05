package com.redmi14c.optimizer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tweak_settings")

class TweakDataStore(private val context: Context) {

    // Tweak states
    val beastModeEnabled: Flow<Boolean> = context.dataStore.data.map { it[BEAST_MODE] ?: false }
    val refreshRateEnabled: Flow<Boolean> = context.dataStore.data.map { it[REFRESH_RATE] ?: false }
    val highPerfEnabled: Flow<Boolean> = context.dataStore.data.map { it[HIGH_PERF] ?: false }
    val batteryOptEnabled: Flow<Boolean> = context.dataStore.data.map { it[BATTERY_OPT] ?: false }
    val reduceAnimEnabled: Flow<Boolean> = context.dataStore.data.map { it[REDUCE_ANIM] ?: false }
    val gpuRenderingEnabled: Flow<Boolean> = context.dataStore.data.map { it[GPU_RENDERING] ?: false }
    val hwAccelEnabled: Flow<Boolean> = context.dataStore.data.map { it[HW_ACCEL] ?: false }
    val touchBoostEnabled: Flow<Boolean> = context.dataStore.data.map { it[TOUCH_BOOST] ?: false }
    val ramManagerEnabled: Flow<Boolean> = context.dataStore.data.map { it[RAM_MANAGER] ?: false }
    val networkOptEnabled: Flow<Boolean> = context.dataStore.data.map { it[NETWORK_OPT] ?: false }
    val cpuGovernorEnabled: Flow<Boolean> = context.dataStore.data.map { it[CPU_GOVERNOR] ?: false }
    val freqLockEnabled: Flow<Boolean> = context.dataStore.data.map { it[FREQ_LOCK] ?: false }
    val disableBlurEnabled: Flow<Boolean> = context.dataStore.data.map { it[DISABLE_BLUR] ?: false }
    val appLaunchEnabled: Flow<Boolean> = context.dataStore.data.map { it[APP_LAUNCH] ?: false }
    val schedulerEnabled: Flow<Boolean> = context.dataStore.data.map { it[SCHEDULER] ?: false }
    val zramEnabled: Flow<Boolean> = context.dataStore.data.map { it[ZRAM] ?: false }
    val adaptiveBatteryEnabled: Flow<Boolean> = context.dataStore.data.map { it[ADAPTIVE_BATTERY] ?: false }
    val msaaEnabled: Flow<Boolean> = context.dataStore.data.map { it[MSAA] ?: false }
    val thermalEnabled: Flow<Boolean> = context.dataStore.data.map { it[THERMAL] ?: false }
    val screenOffEnabled: Flow<Boolean> = context.dataStore.data.map { it[SCREEN_OFF] ?: false }
    val autoTurboEnabled: Flow<Boolean> = context.dataStore.data.map { it[AUTO_TURBO] ?: false }
    val fpsOverlayEnabled: Flow<Boolean> = context.dataStore.data.map { it[FPS_OVERLAY] ?: false }
    val tempMonitorEnabled: Flow<Boolean> = context.dataStore.data.map { it[TEMP_MONITOR] ?: false }
    val disableSensorsEnabled: Flow<Boolean> = context.dataStore.data.map { it[DISABLE_SENSORS] ?: false }
    val ioReadaheadEnabled: Flow<Boolean> = context.dataStore.data.map { it[IO_READAHEAD] ?: false }
    val disableBloatEnabled: Flow<Boolean> = context.dataStore.data.map { it[DISABLE_BLOAT] ?: false }
    val gpuTurboEnabled: Flow<Boolean> = context.dataStore.data.map { it[GPU_TURBO] ?: false }
    val disableMiuiOptEnabled: Flow<Boolean> = context.dataStore.data.map { it[DISABLE_MIUI_OPT] ?: false }
    val forceFpsEnabled: Flow<Boolean> = context.dataStore.data.map { it[FORCE_FPS] ?: false }
    val disableAutoBrightnessEnabled: Flow<Boolean> = context.dataStore.data.map { it[DISABLE_AUTO_BRIGHTNESS] ?: false }
    val rendererType: Flow<String> = context.dataStore.data.map { it[RENDERER_TYPE] ?: "default" }
    val cpuGovernorType: Flow<String> = context.dataStore.data.map { it[CPU_GOVERNOR_TYPE] ?: "performance" }
    val activeProfile: Flow<String> = context.dataStore.data.map { it[ACTIVE_PROFILE] ?: "balanced" }
    val language: Flow<String> = context.dataStore.data.map { it[LANGUAGE] ?: "id" }
    val darkMode: Flow<Boolean> = context.dataStore.data.map { it[DARK_MODE] ?: true }
    val materialYou: Flow<Boolean> = context.dataStore.data.map { it[MATERIAL_YOU] ?: true }

    suspend fun setBeastMode(enabled: Boolean) = context.dataStore.edit { it[BEAST_MODE] = enabled }
    suspend fun setRefreshRate(enabled: Boolean) = context.dataStore.edit { it[REFRESH_RATE] = enabled }
    suspend fun setHighPerf(enabled: Boolean) = context.dataStore.edit { it[HIGH_PERF] = enabled }
    suspend fun setBatteryOpt(enabled: Boolean) = context.dataStore.edit { it[BATTERY_OPT] = enabled }
    suspend fun setReduceAnim(enabled: Boolean) = context.dataStore.edit { it[REDUCE_ANIM] = enabled }
    suspend fun setGpuRendering(enabled: Boolean) = context.dataStore.edit { it[GPU_RENDERING] = enabled }
    suspend fun setHwAccel(enabled: Boolean) = context.dataStore.edit { it[HW_ACCEL] = enabled }
    suspend fun setTouchBoost(enabled: Boolean) = context.dataStore.edit { it[TOUCH_BOOST] = enabled }
    suspend fun setRamManager(enabled: Boolean) = context.dataStore.edit { it[RAM_MANAGER] = enabled }
    suspend fun setNetworkOpt(enabled: Boolean) = context.dataStore.edit { it[NETWORK_OPT] = enabled }
    suspend fun setCpuGovernor(enabled: Boolean) = context.dataStore.edit { it[CPU_GOVERNOR] = enabled }
    suspend fun setFreqLock(enabled: Boolean) = context.dataStore.edit { it[FREQ_LOCK] = enabled }
    suspend fun setDisableBlur(enabled: Boolean) = context.dataStore.edit { it[DISABLE_BLUR] = enabled }
    suspend fun setAppLaunch(enabled: Boolean) = context.dataStore.edit { it[APP_LAUNCH] = enabled }
    suspend fun setScheduler(enabled: Boolean) = context.dataStore.edit { it[SCHEDULER] = enabled }
    suspend fun setZram(enabled: Boolean) = context.dataStore.edit { it[ZRAM] = enabled }
    suspend fun setAdaptiveBattery(enabled: Boolean) = context.dataStore.edit { it[ADAPTIVE_BATTERY] = enabled }
    suspend fun setMsaa(enabled: Boolean) = context.dataStore.edit { it[MSAA] = enabled }
    suspend fun setThermal(enabled: Boolean) = context.dataStore.edit { it[THERMAL] = enabled }
    suspend fun setScreenOff(enabled: Boolean) = context.dataStore.edit { it[SCREEN_OFF] = enabled }
    suspend fun setAutoTurbo(enabled: Boolean) = context.dataStore.edit { it[AUTO_TURBO] = enabled }
    suspend fun setFpsOverlay(enabled: Boolean) = context.dataStore.edit { it[FPS_OVERLAY] = enabled }
    suspend fun setTempMonitor(enabled: Boolean) = context.dataStore.edit { it[TEMP_MONITOR] = enabled }
    suspend fun setDisableSensors(enabled: Boolean) = context.dataStore.edit { it[DISABLE_SENSORS] = enabled }
    suspend fun setIoReadahead(enabled: Boolean) = context.dataStore.edit { it[IO_READAHEAD] = enabled }
    suspend fun setDisableBloat(enabled: Boolean) = context.dataStore.edit { it[DISABLE_BLOAT] = enabled }
    suspend fun setGpuTurbo(enabled: Boolean) = context.dataStore.edit { it[GPU_TURBO] = enabled }
    suspend fun setDisableMiuiOpt(enabled: Boolean) = context.dataStore.edit { it[DISABLE_MIUI_OPT] = enabled }
    suspend fun setForceFps(enabled: Boolean) = context.dataStore.edit { it[FORCE_FPS] = enabled }
    suspend fun setDisableAutoBrightness(enabled: Boolean) = context.dataStore.edit { it[DISABLE_AUTO_BRIGHTNESS] = enabled }
    suspend fun setRendererType(type: String) = context.dataStore.edit { it[RENDERER_TYPE] = type }
    suspend fun setCpuGovernorType(type: String) = context.dataStore.edit { it[CPU_GOVERNOR_TYPE] = type }
    suspend fun setActiveProfile(profile: String) = context.dataStore.edit { it[ACTIVE_PROFILE] = profile }
    suspend fun setLanguage(lang: String) = context.dataStore.edit { it[LANGUAGE] = lang }
    suspend fun setDarkMode(enabled: Boolean) = context.dataStore.edit { it[DARK_MODE] = enabled }
    suspend fun setMaterialYou(enabled: Boolean) = context.dataStore.edit { it[MATERIAL_YOU] = enabled }

    suspend fun resetAll() = context.dataStore.edit { preferences ->
        preferences.clear()
    }

    companion object {
        private val BEAST_MODE = booleanPreferencesKey("beast_mode")
        private val REFRESH_RATE = booleanPreferencesKey("refresh_rate")
        private val HIGH_PERF = booleanPreferencesKey("high_perf")
        private val BATTERY_OPT = booleanPreferencesKey("battery_opt")
        private val REDUCE_ANIM = booleanPreferencesKey("reduce_anim")
        private val GPU_RENDERING = booleanPreferencesKey("gpu_rendering")
        private val HW_ACCEL = booleanPreferencesKey("hw_accel")
        private val TOUCH_BOOST = booleanPreferencesKey("touch_boost")
        private val RAM_MANAGER = booleanPreferencesKey("ram_manager")
        private val NETWORK_OPT = booleanPreferencesKey("network_opt")
        private val CPU_GOVERNOR = booleanPreferencesKey("cpu_governor")
        private val FREQ_LOCK = booleanPreferencesKey("freq_lock")
        private val DISABLE_BLUR = booleanPreferencesKey("disable_blur")
        private val APP_LAUNCH = booleanPreferencesKey("app_launch")
        private val SCHEDULER = booleanPreferencesKey("scheduler")
        private val ZRAM = booleanPreferencesKey("zram")
        private val ADAPTIVE_BATTERY = booleanPreferencesKey("adaptive_battery")
        private val MSAA = booleanPreferencesKey("msaa")
        private val THERMAL = booleanPreferencesKey("thermal")
        private val SCREEN_OFF = booleanPreferencesKey("screen_off")
        private val AUTO_TURBO = booleanPreferencesKey("auto_turbo")
        private val FPS_OVERLAY = booleanPreferencesKey("fps_overlay")
        private val TEMP_MONITOR = booleanPreferencesKey("temp_monitor")
        private val DISABLE_SENSORS = booleanPreferencesKey("disable_sensors")
        private val IO_READAHEAD = booleanPreferencesKey("io_readahead")
        private val DISABLE_BLOAT = booleanPreferencesKey("disable_bloat")
        private val GPU_TURBO = booleanPreferencesKey("gpu_turbo")
        private val DISABLE_MIUI_OPT = booleanPreferencesKey("disable_miui_opt")
        private val FORCE_FPS = booleanPreferencesKey("force_fps")
        private val DISABLE_AUTO_BRIGHTNESS = booleanPreferencesKey("disable_auto_brightness")
        private val RENDERER_TYPE = stringPreferencesKey("renderer_type")
        private val CPU_GOVERNOR_TYPE = stringPreferencesKey("cpu_governor_type")
        private val ACTIVE_PROFILE = stringPreferencesKey("active_profile")
        private val LANGUAGE = stringPreferencesKey("language")
        private val DARK_MODE = booleanPreferencesKey("dark_mode")
        private val MATERIAL_YOU = booleanPreferencesKey("material_you")
    }
}
