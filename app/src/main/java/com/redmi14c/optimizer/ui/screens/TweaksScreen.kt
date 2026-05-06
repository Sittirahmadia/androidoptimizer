package com.redmi14c.optimizer.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.clickable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.redmi14c.optimizer.data.TweakCommand
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.redmi14c.optimizer.data.TweakCommands
import com.redmi14c.optimizer.ui.components.*
import com.redmi14c.optimizer.viewmodel.OptimizerViewModel
import com.redmi14c.optimizer.viewmodel.ShizukuStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TweaksScreen(
    navController: NavController,
    viewModel: OptimizerViewModel = viewModel()
) {
    val shizukuStatus by viewModel.shizukuStatus.collectAsStateWithLifecycle()

    // Tweak states
    val beastMode by viewModel.beastMode.collectAsStateWithLifecycle(initialValue = false)
    val refreshRate by viewModel.refreshRate.collectAsStateWithLifecycle(initialValue = false)
    val highPerf by viewModel.highPerf.collectAsStateWithLifecycle(initialValue = false)
    val batteryOpt by viewModel.batteryOpt.collectAsStateWithLifecycle(initialValue = false)
    val reduceAnim by viewModel.reduceAnim.collectAsStateWithLifecycle(initialValue = false)
    val gpuRendering by viewModel.gpuRendering.collectAsStateWithLifecycle(initialValue = false)
    val hwAccel by viewModel.hwAccel.collectAsStateWithLifecycle(initialValue = false)
    val touchBoost by viewModel.touchBoost.collectAsStateWithLifecycle(initialValue = false)
    val ramManager by viewModel.ramManager.collectAsStateWithLifecycle(initialValue = false)
    val networkOpt by viewModel.networkOpt.collectAsStateWithLifecycle(initialValue = false)
    val cpuGovernor by viewModel.cpuGovernor.collectAsStateWithLifecycle(initialValue = false)
    val freqLock by viewModel.freqLock.collectAsStateWithLifecycle(initialValue = false)
    val disableBlur by viewModel.disableBlur.collectAsStateWithLifecycle(initialValue = false)
    val appLaunch by viewModel.appLaunch.collectAsStateWithLifecycle(initialValue = false)
    val scheduler by viewModel.scheduler.collectAsStateWithLifecycle(initialValue = false)
    val zram by viewModel.zram.collectAsStateWithLifecycle(initialValue = false)
    val adaptiveBattery by viewModel.adaptiveBattery.collectAsStateWithLifecycle(initialValue = false)
    val msaa by viewModel.msaa.collectAsStateWithLifecycle(initialValue = false)
    val thermal by viewModel.thermal.collectAsStateWithLifecycle(initialValue = false)
    val screenOff by viewModel.screenOff.collectAsStateWithLifecycle(initialValue = false)
    val disableSensors by viewModel.disableSensors.collectAsStateWithLifecycle(initialValue = false)
    val ioReadahead by viewModel.ioReadahead.collectAsStateWithLifecycle(initialValue = false)
    val disableBloat by viewModel.disableBloat.collectAsStateWithLifecycle(initialValue = false)
    val gpuTurbo by viewModel.gpuTurbo.collectAsStateWithLifecycle(initialValue = false)
    val disableMiuiOpt by viewModel.disableMiuiOpt.collectAsStateWithLifecycle(initialValue = false)
    val forceFps by viewModel.forceFps.collectAsStateWithLifecycle(initialValue = false)
    val disableAutoBrightness by viewModel.disableAutoBrightness.collectAsStateWithLifecycle(initialValue = false)

    val rendererType by viewModel.rendererType.collectAsStateWithLifecycle(initialValue = "default")
    val cpuGovernorType by viewModel.cpuGovernorType.collectAsStateWithLifecycle(initialValue = "performance")

    var showCommandsDialog by remember { mutableStateOf(false) }
    var selectedTweakId by remember { mutableStateOf("") }
    var showWarningDialog by remember { mutableStateOf(false) }
    var warningTweak by remember { mutableStateOf<TweakCommand?>(null) }
    var pendingToggle by remember { mutableStateOf<(() -> Unit)?>(null) }

    LaunchedEffect(Unit) {
        viewModel.checkShizukuStatus()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("System Tweaks", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                actions = {
                    TextButton(onClick = { viewModel.resetAllTweaks() }) {
                        Text("Reset All")
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                ShizukuStatusCard(
                    isConnected = shizukuStatus == ShizukuStatus.CONNECTED,
                    onGrantPermission = { viewModel.checkShizukuStatus() }
                )
            }

            item {
                SectionHeader(title = "Display & Visual")
            }

            item {
                val tweak = TweakCommands.getTweakById("refresh_rate")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = refreshRate,
                        onToggle = { enabled ->
                            if (enabled && shizukuStatus == ShizukuStatus.CONNECTED) {
                                viewModel.toggleRefreshRate(true)
                                viewModel.applyTweaks(listOf("refresh_rate"))
                            } else {
                                viewModel.toggleRefreshRate(false)
                            }
                        },
                        onPreview = {
                            selectedTweakId = "refresh_rate"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_blur")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = disableBlur,
                        onToggle = { enabled ->
                            viewModel.toggleDisableBlur(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("disable_blur"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_blur"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("reduce_anim")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = reduceAnim,
                        onToggle = { enabled ->
                            viewModel.toggleReduceAnim(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("reduce_anim"))
                        },
                        onPreview = {
                            selectedTweakId = "reduce_anim"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_auto_brightness")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = disableAutoBrightness,
                        onToggle = { enabled ->
                            viewModel.toggleDisableAutoBrightness(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("disable_auto_brightness"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_auto_brightness"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Performance & CPU")
            }

            item {
                val tweak = TweakCommands.getTweakById("high_perf")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = highPerf,
                        onToggle = { enabled ->
                            viewModel.toggleHighPerf(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("high_perf"))
                        },
                        onPreview = {
                            selectedTweakId = "high_perf"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("cpu_governor")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = cpuGovernor,
                        onToggle = { enabled ->
                            if (enabled) {
                                warningTweak = tweak
                                pendingToggle = {
                                    viewModel.toggleCpuGovernor(true)
                                    viewModel.applyTweaks(listOf("cpu_governor"))
                                }
                                showWarningDialog = true
                            } else {
                                viewModel.toggleCpuGovernor(false)
                            }
                        },
                        onPreview = {
                            selectedTweakId = "cpu_governor"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("freq_lock")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = freqLock,
                        onToggle = { enabled ->
                            if (enabled) {
                                warningTweak = tweak
                                pendingToggle = {
                                    viewModel.toggleFreqLock(true)
                                    viewModel.applyTweaks(listOf("freq_lock"))
                                }
                                showWarningDialog = true
                            } else {
                                viewModel.toggleFreqLock(false)
                            }
                        },
                        onPreview = {
                            selectedTweakId = "freq_lock"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("app_launch")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = appLaunch,
                        onToggle = { enabled ->
                            viewModel.toggleAppLaunch(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("app_launch"))
                        },
                        onPreview = {
                            selectedTweakId = "app_launch"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "GPU & Rendering")
            }

            item {
                val tweak = TweakCommands.getTweakById("gpu_rendering")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = gpuRendering,
                        onToggle = { enabled ->
                            viewModel.toggleGpuRendering(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("gpu_rendering"))
                        },
                        onPreview = {
                            selectedTweakId = "gpu_rendering"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("gpu_turbo")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = gpuTurbo,
                        onToggle = { enabled ->
                            if (enabled) {
                                warningTweak = tweak
                                pendingToggle = {
                                    viewModel.toggleGpuTurbo(true)
                                    viewModel.applyTweaks(listOf("gpu_turbo"))
                                }
                                showWarningDialog = true
                            } else {
                                viewModel.toggleGpuTurbo(false)
                            }
                        },
                        onPreview = {
                            selectedTweakId = "gpu_turbo"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("msaa")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = msaa,
                        onToggle = { enabled ->
                            viewModel.toggleMsaa(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("msaa"))
                        },
                        onPreview = {
                            selectedTweakId = "msaa"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("hw_accel")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = hwAccel,
                        onToggle = { enabled ->
                            viewModel.toggleHwAccel(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("hw_accel"))
                        },
                        onPreview = {
                            selectedTweakId = "hw_accel"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                RendererSelector(
                    currentRenderer = rendererType,
                    onRendererSelected = { viewModel.setRendererType(it) }
                )
            }

            item {
                SectionHeader(title = "RAM & Storage")
            }

            item {
                val tweak = TweakCommands.getTweakById("ram_manager")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = ramManager,
                        onToggle = { enabled ->
                            viewModel.toggleRamManager(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("ram_manager"))
                        },
                        onPreview = {
                            selectedTweakId = "ram_manager"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("zram")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = zram,
                        onToggle = { enabled ->
                            viewModel.toggleZram(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("zram"))
                        },
                        onPreview = {
                            selectedTweakId = "zram"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("scheduler")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = scheduler,
                        onToggle = { enabled ->
                            viewModel.toggleScheduler(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("scheduler"))
                        },
                        onPreview = {
                            selectedTweakId = "scheduler"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("io_readahead")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = ioReadahead,
                        onToggle = { enabled ->
                            viewModel.toggleIoReadahead(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("io_readahead"))
                        },
                        onPreview = {
                            selectedTweakId = "io_readahead"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("clear_cache")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = false,
                        onToggle = { enabled ->
                            if (enabled) viewModel.applyTweaks(listOf("clear_cache"))
                        },
                        onPreview = {
                            selectedTweakId = "clear_cache"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Battery & Power")
            }

            item {
                val tweak = TweakCommands.getTweakById("battery_opt")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = batteryOpt,
                        onToggle = { enabled ->
                            viewModel.toggleBatteryOpt(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("battery_opt"))
                        },
                        onPreview = {
                            selectedTweakId = "battery_opt"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("adaptive_battery")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = adaptiveBattery,
                        onToggle = { enabled ->
                            viewModel.toggleAdaptiveBattery(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("adaptive_battery"))
                        },
                        onPreview = {
                            selectedTweakId = "adaptive_battery"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("screen_off")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = screenOff,
                        onToggle = { enabled ->
                            viewModel.toggleScreenOff(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("screen_off"))
                        },
                        onPreview = {
                            selectedTweakId = "screen_off"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Thermal Management")
            }

            item {
                val tweak = TweakCommands.getTweakById("thermal")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = thermal,
                        onToggle = { enabled ->
                            if (enabled) {
                                warningTweak = tweak
                                pendingToggle = {
                                    viewModel.toggleThermal(true)
                                    viewModel.applyTweaks(listOf("thermal"))
                                }
                                showWarningDialog = true
                            } else {
                                viewModel.toggleThermal(false)
                            }
                        },
                        onPreview = {
                            selectedTweakId = "thermal"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Touch & Input")
            }

            item {
                val tweak = TweakCommands.getTweakById("touch_boost")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = touchBoost,
                        onToggle = { enabled ->
                            viewModel.toggleTouchBoost(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("touch_boost"))
                        },
                        onPreview = {
                            selectedTweakId = "touch_boost"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_sensors")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = disableSensors,
                        onToggle = { enabled ->
                            viewModel.toggleDisableSensors(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("disable_sensors"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_sensors"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Network")
            }

            item {
                val tweak = TweakCommands.getTweakById("network_opt")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = networkOpt,
                        onToggle = { enabled ->
                            viewModel.toggleNetworkOpt(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("network_opt"))
                        },
                        onPreview = {
                            selectedTweakId = "network_opt"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "System & Bloat")
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_bg")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = false,
                        onToggle = { enabled ->
                            if (enabled) viewModel.applyTweaks(listOf("disable_bg"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_bg"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_bloat")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = disableBloat,
                        onToggle = { enabled ->
                            viewModel.toggleDisableBloat(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("disable_bloat"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_bloat"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                val tweak = TweakCommands.getTweakById("disable_miui_opt")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = disableMiuiOpt,
                        onToggle = { enabled ->
                            viewModel.toggleDisableMiuiOpt(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("disable_miui_opt"))
                        },
                        onPreview = {
                            selectedTweakId = "disable_miui_opt"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                SectionHeader(title = "Gaming Specific")
            }

            item {
                val tweak = TweakCommands.getTweakById("force_fps")
                if (tweak != null) {
                    TweakCard(
                        tweak = tweak,
                        isEnabled = forceFps,
                        onToggle = { enabled ->
                            viewModel.toggleForceFps(enabled)
                            if (enabled) viewModel.applyTweaks(listOf("force_fps"))
                        },
                        onPreview = {
                            selectedTweakId = "force_fps"
                            showCommandsDialog = true
                        }
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }

    // Commands Preview Dialog
    if (showCommandsDialog) {
        CommandsPreviewDialog(
            tweakId = selectedTweakId,
            onDismiss = { showCommandsDialog = false },
            onApply = {
                viewModel.applyTweaks(listOf(selectedTweakId))
                showCommandsDialog = false
            }
        )
    }

    // Warning Dialog
    if (showWarningDialog && warningTweak != null) {
        AlertDialog(
            onDismissRequest = { 
                showWarningDialog = false
                pendingToggle = null
            },
            title = { Text("⚠️ Peringatan Keamanan") },
            text = {
                Column {
                    Text(
                        text = warningTweak!!.warningMessage,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Tweak: ${warningTweak!!.name}",
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "Estimasi: ${warningTweak!!.fpsEstimate} | Baterai: ${warningTweak!!.batteryImpact}",
                        fontSize = 12.sp
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        pendingToggle?.invoke()
                        showWarningDialog = false
                        pendingToggle = null
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text("Saya Mengerti & Lanjutkan")
                }
            },
            dismissButton = {
                TextButton(onClick = { 
                    showWarningDialog = false
                    pendingToggle = null
                }) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun RendererSelector(
    currentRenderer: String,
    onRendererSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val renderers = listOf(
        "default" to "Default",
        "skiavk" to "Vulkan (SkiaVK)",
        "skiagl" to "SkiaGL",
        "opengl" to "OpenGL ES",
        "skiavk_pipeline" to "Vulkan Pipeline"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Renderer Pipeline",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            Text(
                text = "Pilih renderer untuk performa optimal",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            renderers.forEach { (value, label) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onRendererSelected(value) }
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = currentRenderer == value,
                        onClick = { onRendererSelected(value) }
                    )
                    Text(
                        text = label,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CommandsPreviewDialog(
    tweakId: String,
    onDismiss: () -> Unit,
    onApply: () -> Unit
) {
    val tweak = TweakCommands.getTweakById(tweakId)

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Commands Preview") },
        text = {
            LazyColumn {
                if (tweak != null) {
                    item {
                        Text(
                            text = "Commands to execute:",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }
                    items(count = tweak.commands.size) { index ->
                        val cmd = tweak.commands[index]
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 2.dp),
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                        ) {
                            Text(
                                text = cmd,
                                fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                fontSize = 11.sp,
                                modifier = Modifier.padding(8.dp)
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(onClick = onApply) {
                Text("Apply")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
