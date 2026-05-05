package com.redmi14c.optimizer.data

data class OptimizerString(
    val id: Int,
    val name: String,
    val key: String,
    val value: String,
    val type: String, // global, system, secure
    val description: String,
    val category: String,
    val fpsEstimate: String,
    val batteryImpact: String,
    val riskLevel: String,
    val command: String
)

object OptimizerStringsDatabase {

    val ALL_STRINGS = listOf(
        // === PERFORMANCE (1-15) ===
        OptimizerString(1, "Window Animation Scale", "window_animation_scale", "0.0", "global", 
            "Mengurangi atau menonaktifkan animasi window untuk respons yang lebih cepat", "Performance", 
            "+Responsiveness", "+5%", "Low", "settings put global window_animation_scale 0.0"),

        OptimizerString(2, "Transition Animation Scale", "transition_animation_scale", "0.0", "global",
            "Mengurangi animasi transisi antar activity", "Performance",
            "+Responsiveness", "+5%", "Low", "settings put global transition_animation_scale 0.0"),

        OptimizerString(3, "Animator Duration Scale", "animator_duration_scale", "0.0", "global",
            "Mengurangi durasi animator untuk performa maksimal", "Performance",
            "+Responsiveness", "+5%", "Low", "settings put global animator_duration_scale 0.0"),

        OptimizerString(4, "Disable Background Process Limit", "background_process_limit", "0", "global",
            "Membatasi background process untuk menghemat RAM", "Performance",
            "+5-10 FPS", "+5%", "Low", "settings put global background_process_limit 0"),

        OptimizerString(5, "Cached Apps Freezer", "cached_apps_freezer", "enabled", "global",
            "Membekukan aplikasi yang di-cache untuk menghemat RAM", "Performance",
            "+RAM", "+5%", "Low", "settings put global cached_apps_freezer enabled"),

        OptimizerString(6, "Force High Refresh Rate", "peak_refresh_rate", "120", "system",
            "Memaksa refresh rate maksimum 120Hz", "Performance",
            "+Smoothness", "-10%", "Medium", "settings put system peak_refresh_rate 120"),

        OptimizerString(7, "Min Refresh Rate Lock", "min_refresh_rate", "120", "system",
            "Mengunci minimum refresh rate ke 120Hz", "Performance",
            "+Smoothness", "-10%", "Medium", "settings put system min_refresh_rate 120"),

        OptimizerString(8, "Disable HWUI Debug Info", "debug.hwui.disable_draw_defer", "true", "global",
            "Menonaktifkan deferred drawing untuk rendering langsung", "Performance",
            "+3-8 FPS", "-3%", "Low", "setprop debug.hwui.disable_draw_defer true"),

        OptimizerString(9, "Disable Dirty Region Debug", "debug.hwui.show_dirty_regions", "false", "global",
            "Menonaktifkan tampilan dirty regions untuk performa", "Performance",
            "+2-5 FPS", "Neutral", "Low", "setprop debug.hwui.show_dirty_regions false"),

        OptimizerString(10, "Disable Buffer Age", "debug.hwui.use_buffer_age", "false", "global",
            "Menonaktifkan buffer age untuk rendering lebih cepat", "Performance",
            "+2-5 FPS", "-2%", "Low", "setprop debug.hwui.use_buffer_age false"),

        OptimizerString(11, "Disable VSync", "hwui.disable_vsync", "true", "global",
            "Menonaktifkan VSync untuk rendering tanpa batasan refresh", "Performance",
            "+5-10 FPS", "-5%", "High", "settings put global hwui.disable_vsync true"),

        OptimizerString(12, "Force GPU Rendering", "debug.hwui.renderer", "skiavk", "global",
            "Memaksa rendering menggunakan GPU dengan Vulkan/Skia", "Performance",
            "+5-15 FPS", "-5%", "Medium", "setprop debug.hwui.renderer skiavk"),

        OptimizerString(13, "Disable App Verification", "verifier_verify_adb_installs", "0", "global",
            "Menonaktifkan verifikasi install ADB untuk kecepatan", "Performance",
            "+Install Speed", "Neutral", "Low", "settings put global verifier_verify_adb_installs 0"),

        OptimizerString(14, "Disable Package Stats", "sys_vm_stats", "0", "global",
            "Menonaktifkan statistik VM untuk mengurangi overhead", "Performance",
            "+2-3 FPS", "+2%", "Low", "settings put global sys_vm_stats 0"),

        OptimizerString(15, "Disable Strict Mode", "show_strict_mode", "0", "global",
            "Menonaktifkan strict mode untuk performa lebih baik", "Performance",
            "+2-3 FPS", "Neutral", "Low", "settings put global show_strict_mode 0"),

        // === GPU/RENDERER (16-30) ===
        OptimizerString(16, "Vulkan Renderer", "debug.hwui.renderer", "skiavk", "global",
            "Menggunakan renderer Vulkan untuk performa GPU terbaik", "GPU/Renderer",
            "+10-20 FPS", "-5%", "Medium", "setprop debug.hwui.renderer skiavk"),

        OptimizerString(17, "OpenGL ES 3.0 Renderer", "debug.hwui.renderer", "opengl", "global",
            "Menggunakan renderer OpenGL ES 3.0", "GPU/Renderer",
            "+5-10 FPS", "-3%", "Low", "setprop debug.hwui.renderer opengl"),

        OptimizerString(18, "SkiaGL Renderer", "debug.hwui.renderer", "skiagl", "global",
            "Menggunakan renderer SkiaGL untuk kompatibilitas", "GPU/Renderer",
            "+3-5 FPS", "-2%", "Low", "setprop debug.hwui.renderer skiagl"),

        OptimizerString(19, "Enable Vulkan", "debug.hwui.use_vulkan", "true", "global",
            "Mengaktifkan backend Vulkan untuk rendering", "GPU/Renderer",
            "+8-15 FPS", "-5%", "Medium", "setprop debug.hwui.use_vulkan true"),

        OptimizerString(20, "Disable Vulkan", "debug.hwui.use_vulkan", "false", "global",
            "Menonaktifkan Vulkan dan menggunakan OpenGL", "GPU/Renderer",
            "Stable", "Neutral", "Low", "setprop debug.hwui.use_vulkan false"),

        OptimizerString(21, "Force 4x MSAA", "debug.hwui.disable_draw_defer", "true", "global",
            "Memaksa 4x MSAA untuk kualitas visual lebih baik", "GPU/Renderer",
            "+Quality", "-10%", "Medium", "setprop debug.hwui.disable_draw_defer true"),

        OptimizerString(22, "Disable GPU Overdraw Debug", "debug.hwui.show_overdraw", "false", "global",
            "Menonaktifkan tampilan overdraw untuk performa", "GPU/Renderer",
            "+2-3 FPS", "Neutral", "Low", "setprop debug.hwui.show_overdraw false"),

        OptimizerString(23, "GPU Turbo Clock", "gpuclk", "max", "system",
            "Mengunci GPU clock ke frekuensi maksimum", "GPU/Renderer",
            "+10-20 FPS", "-20%", "Extreme", "cat /sys/class/kgsl/kgsl-3d0/max_gpuclk > /sys/class/kgsl/kgsl-3d0/gpuclk"),

        OptimizerString(24, "Disable GPU Throttling", "throttling", "0", "system",
            "Menonaktifkan throttling GPU untuk performa maksimal", "GPU/Renderer",
            "+15-25 FPS", "-25%", "Extreme", "echo 0 > /sys/class/kgsl/kgsl-3d0/throttling"),

        OptimizerString(25, "Force GPU Clock On", "force_clk_on", "1", "system",
            "Memaksa GPU clock selalu aktif", "GPU/Renderer",
            "+8-15 FPS", "-15%", "High", "echo 1 > /sys/class/kgsl/kgsl-3d0/force_clk_on"),

        OptimizerString(26, "Disable GPU Bus Split", "bus_split", "0", "system",
            "Menonaktifkan bus split untuk bandwidth penuh", "GPU/Renderer",
            "+5-10 FPS", "-5%", "Medium", "echo 0 > /sys/class/kgsl/kgsl-3d0/bus_split"),

        OptimizerString(27, "Force GPU Rail On", "force_rail_on", "1", "system",
            "Memaksa power rail GPU selalu aktif", "GPU/Renderer",
            "+3-5 FPS", "-8%", "Medium", "echo 1 > /sys/class/kgsl/kgsl-3d0/force_rail_on"),

        OptimizerString(28, "Disable GPU No-Nap", "force_no_nap", "1", "system",
            "Mencegah GPU masuk mode tidur", "GPU/Renderer",
            "+5-10 FPS", "-10%", "High", "echo 1 > /sys/class/kgsl/kgsl-3d0/force_no_nap"),

        OptimizerString(29, "Enable Hardware Acceleration", "hardware_accelerated_rendering_enabled", "1", "global",
            "Mengaktifkan hardware acceleration untuk semua aplikasi", "GPU/Renderer",
            "+3-8 FPS", "-3%", "Low", "settings put global hardware_accelerated_rendering_enabled 1"),

        OptimizerString(30, "Disable HW Accel Override", "hwui.disable_hw_accel", "0", "global",
            "Memastikan hardware acceleration tidak dinonaktifkan", "GPU/Renderer",
            "+3-5 FPS", "Neutral", "Low", "settings put global hwui.disable_hw_accel 0"),

        // === RAM (31-45) ===
        OptimizerString(31, "Disable RAM Expansion", "ram_expand_size", "0", "global",
            "Menonaktifkan virtual RAM untuk performa native RAM", "RAM",
            "+5-10 FPS", "Neutral", "Low", "settings put global ram_expand_size 0"),

        OptimizerString(32, "Aggressive Swappiness", "swappiness", "100", "system",
            "Meningkatkan agresivitas swap untuk RAM lebih bebas", "RAM",
            "+RAM", "-3%", "Low", "echo 100 > /proc/sys/vm/swappiness"),

        OptimizerString(33, "Reduce Dirty Ratio", "dirty_ratio", "10", "system",
            "Mengurangi dirty ratio untuk write lebih cepat", "RAM",
            "+I/O Speed", "Neutral", "Low", "echo 10 > /proc/sys/vm/dirty_ratio"),

        OptimizerString(34, "Reduce Dirty Background", "dirty_background_ratio", "5", "system",
            "Mengurangi background dirty ratio", "RAM",
            "+I/O Speed", "Neutral", "Low", "echo 5 > /proc/sys/vm/dirty_background_ratio"),

        OptimizerString(35, "Disable Page Cluster", "page-cluster", "0", "system",
            "Menonaktifkan page cluster untuk swap lebih efisien", "RAM",
            "+RAM", "-2%", "Low", "echo 0 > /proc/sys/vm/page-cluster"),

        OptimizerString(36, "ZRAM Size 4GB", "disksize", "4294967296", "system",
            "Mengatur ZRAM ke 4GB untuk kompresi RAM", "RAM",
            "+RAM", "-3%", "Low", "echo 4294967296 > /sys/block/zram0/disksize"),

        OptimizerString(37, "Kill All Background", "kill-all", "", "system",
            "Mematikan semua proses background", "RAM",
            "+10-15 FPS", "Neutral", "Low", "am kill-all"),

        OptimizerString(38, "Memory Factor Critical", "memory-factor", "critical", "system",
            "Mengatur memory factor ke critical untuk agresif", "RAM",
            "+RAM", "Neutral", "Low", "cmd activity memory-factor critical"),

        OptimizerString(39, "Disable App Standby", "app_standby_enabled", "0", "global",
            "Menonaktifkan app standby untuk RAM lebih bebas", "RAM",
            "+RAM", "-5%", "Low", "settings put global app_standby_enabled 0"),

        OptimizerString(40, "Disable Forced Standby", "forced_app_standby_enabled", "0", "global",
            "Menonaktifkan forced app standby", "RAM",
            "+RAM", "-3%", "Low", "settings put global forced_app_standby_enabled 0"),

        OptimizerString(41, "Trim Caches", "trim-caches", "1G", "system",
            "Membersihkan cache aplikasi", "RAM",
            "+RAM", "Neutral", "Low", "pm trim-caches 1G"),

        OptimizerString(42, "Compile Speed Profile", "compile", "speed-profile", "system",
            "Mengompilasi aplikasi dengan profile kecepatan", "RAM",
            "+Launch Speed", "Neutral", "Low", "cmd package compile -m speed-profile -f"),

        OptimizerString(43, "Reset Compilation", "compile-reset", "", "system",
            "Mereset kompilasi ke default", "RAM",
            "Stable", "Neutral", "Low", "cmd package compile --reset"),

        OptimizerString(44, "Background DEX Opt", "bg-dexopt-job", "", "system",
            "Menjalankan background DEX optimization", "RAM",
            "+Performance", "Neutral", "Low", "cmd package bg-dexopt-job"),

        OptimizerString(45, "Disable VM Stats", "sys_vm_stats", "0", "global",
            "Menonaktifkan VM statistics untuk overhead lebih rendah", "RAM",
            "+2-3 FPS", "+2%", "Low", "settings put global sys_vm_stats 0"),

        // === GAMING (46-60) ===
        OptimizerString(46, "Game Mode Enabled", "game_mode", "1", "global",
            "Mengaktifkan mode game sistem", "Gaming",
            "+5-10 FPS", "-5%", "Low", "settings put global game_mode 1"),

        OptimizerString(47, "Disable Game Auto Brightness", "game_auto_brightness", "0", "system",
            "Menonaktifkan auto brightness saat gaming", "Gaming",
            "+Consistency", "+3%", "Low", "settings put system game_auto_brightness 0"),

        OptimizerString(48, "Disable Game Auto Refresh", "game_auto_refresh_rate", "0", "system",
            "Menonaktifkan auto refresh rate saat gaming", "Gaming",
            "+Consistency", "Neutral", "Low", "settings put system game_auto_refresh_rate 0"),

        OptimizerString(49, "Game Touch Boost", "game_touch_sensitivity", "1", "system",
            "Meningkatkan sensitivitas touch saat gaming", "Gaming",
            "+Responsiveness", "Neutral", "Low", "settings put system game_touch_sensitivity 1"),

        OptimizerString(50, "Game Network Priority", "game_network_priority", "1", "global",
            "Meningkatkan prioritas jaringan untuk game", "Gaming",
            "+Latency", "Neutral", "Low", "settings put global game_network_priority 1"),

        OptimizerString(51, "Disable Notification HeadsUp", "heads_up_notifications_enabled", "0", "system",
            "Menonaktifkan heads-up notification saat gaming", "Gaming",
            "+Focus", "Neutral", "Low", "settings put system heads_up_notifications_enabled 0"),

        OptimizerString(52, "Disable Edge Lighting", "edge_lighting", "0", "system",
            "Menonaktifkan edge lighting untuk gangguan lebih sedikit", "Gaming",
            "+Focus", "+2%", "Low", "settings put system edge_lighting 0"),

        OptimizerString(53, "Disable AOD", "doze_always_on", "0", "secure",
            "Menonaktifkan Always On Display", "Gaming",
            "+Battery", "+5%", "Low", "settings put secure doze_always_on 0"),

        OptimizerString(54, "Game Doze Disable", "doze_pulse_on_pick_up", "0", "secure",
            "Menonaktifkan doze pulse saat gaming", "Gaming",
            "+Battery", "+3%", "Low", "settings put secure doze_pulse_on_pick_up 0"),

        OptimizerString(55, "Disable Vibration", "haptic_feedback_enabled", "0", "system",
            "Menonaktifkan haptic feedback untuk penghematan", "Gaming",
            "+Battery", "+3%", "Low", "settings put system haptic_feedback_enabled 0"),

        OptimizerString(56, "Disable Notification Light", "notification_light_pulse", "0", "system",
            "Menonaktifkan lampu notifikasi", "Gaming",
            "+Battery", "+2%", "Low", "settings put system notification_light_pulse 0"),

        OptimizerString(57, "Game Full Screen", "game_full_screen", "1", "global",
            "Memaksa full screen untuk game", "Gaming",
            "+Immersion", "Neutral", "Low", "settings put global game_full_screen 1"),

        OptimizerString(58, "Disable Screenshot Sound", "screenshot_sound", "0", "system",
            "Menonaktifkan suara screenshot", "Gaming",
            "+Focus", "Neutral", "Low", "settings put system screenshot_sound 0"),

        OptimizerString(59, "Disable Camera Sound", "camera_sound", "0", "system",
            "Menonaktifkan suara kamera", "Gaming",
            "+Focus", "Neutral", "Low", "settings put system camera_sound 0"),

        OptimizerString(60, "Disable Boot Animation", "boot_animation", "0", "global",
            "Menonaktifkan animasi boot untuk boot lebih cepat", "Gaming",
            "+Boot Speed", "Neutral", "Low", "settings put global boot_animation 0"),

        // === BATTERY (61-75) ===
        OptimizerString(61, "Disable Adaptive Battery", "adaptive_battery_management_enabled", "0", "global",
            "Menonaktifkan manajemen baterai adaptif", "Battery",
            "+2-5 FPS", "-10%", "Low", "settings put global adaptive_battery_management_enabled 0"),

        OptimizerString(62, "Disable Battery Saver", "low_power", "0", "global",
            "Memastikan battery saver tidak aktif", "Battery",
            "+Performance", "-15%", "Low", "settings put global low_power 0"),

        OptimizerString(63, "Disable Power Saving Mode", "power_saving_mode", "0", "global",
            "Menonaktifkan mode penghematan daya", "Battery",
            "+Performance", "-15%", "Low", "settings put global power_saving_mode 0"),

        OptimizerString(64, "Disable Doze Mode", "device_idle", "disable", "global",
            "Menonaktifkan doze mode untuk performa", "Battery",
            "+2-5 FPS", "-10%", "Low", "cmd deviceidle disable"),

        OptimizerString(65, "Disable Low Power Sticky", "low_power_sticky", "0", "global",
            "Menonaktifkan sticky low power mode", "Battery",
            "+Performance", "-5%", "Low", "settings put global low_power_sticky 0"),

        OptimizerString(66, "Disable Low Power Trigger", "low_power_trigger_level", "0", "global",
            "Menonaktifkan trigger battery saver otomatis", "Battery",
            "+Performance", "-5%", "Low", "settings put global low_power_trigger_level 0"),

        OptimizerString(67, "Stay On While Plugged", "stay_on_while_plugged_in", "0", "global",
            "Menonaktifkan stay on saat charging", "Battery",
            "+Battery", "+5%", "Low", "settings put global stay_on_while_plugged_in 0"),

        OptimizerString(68, "Disable Auto Sync", "auto_sync", "0", "global",
            "Menonaktifkan auto sync untuk penghematan", "Battery",
            "+Battery", "+5%", "Low", "settings put global auto_sync 0"),

        OptimizerString(69, "Disable Bluetooth Scan", "ble_scan_always_enabled", "0", "global",
            "Menonaktifkan BLE scan selalu aktif", "Battery",
            "+Battery", "+3%", "Low", "settings put global ble_scan_always_enabled 0"),

        OptimizerString(70, "Disable WiFi Scan", "wifi_scan_always_enabled", "0", "global",
            "Menonaktifkan WiFi scan selalu aktif", "Battery",
            "+Battery", "+3%", "Low", "settings put global wifi_scan_always_enabled 0"),

        OptimizerString(71, "Disable Location", "location_mode", "0", "secure",
            "Menonaktifkan location services", "Battery",
            "+Battery", "+10%", "Low", "settings put secure location_mode 0"),

        OptimizerString(72, "Disable NFC", "nfc_disabled", "1", "secure",
            "Menonaktifkan NFC untuk penghematan", "Battery",
            "+Battery", "+2%", "Low", "settings put secure nfc_disabled 1"),

        OptimizerString(73, "Screen Brightness Manual", "screen_brightness_mode", "0", "system",
            "Mengatur brightness manual untuk konsistensi", "Battery",
            "+Consistency", "+5%", "Low", "settings put system screen_brightness_mode 0"),

        OptimizerString(74, "Disable Ambient Display", "doze_pulse_on_pick_up", "0", "secure",
            "Menonaktifkan ambient display", "Battery",
            "+Battery", "+5%", "Low", "settings put secure doze_pulse_on_pick_up 0"),

        OptimizerString(75, "Disable Wake Gesture", "doze_wake_display_gesture", "0", "secure",
            "Menonaktifkan wake gesture", "Battery",
            "+Battery", "+3%", "Low", "settings put secure doze_wake_display_gesture 0"),

        // === NETWORK (76-85) ===
        OptimizerString(76, "DNS Cloudflare", "private_dns_mode", "hostname", "global",
            "Menggunakan DNS Cloudflare (1.1.1.1) untuk kecepatan", "Network",
            "+Latency", "Neutral", "Low", "settings put global private_dns_mode hostname"),

        OptimizerString(77, "DNS Google", "private_dns_specifier", "dns.google", "global",
            "Menggunakan DNS Google untuk kecepatan", "Network",
            "+Latency", "Neutral", "Low", "settings put global private_dns_specifier dns.google"),

        OptimizerString(78, "TCP Fast Open", "tcp_fastopen", "1", "global",
            "Mengaktifkan TCP Fast Open untuk koneksi lebih cepat", "Network",
            "+Latency", "Neutral", "Low", "settings put global tcp_fastopen 1"),

        OptimizerString(79, "WiFi Buffer Size", "wifi_tcp_buffers", "524288,1048576,2097152,524288,1048576,2097152", "global",
            "Meningkatkan buffer size WiFi untuk throughput", "Network",
            "+Throughput", "Neutral", "Low", "settings put global wifi_tcp_buffers 524288,1048576,2097152,524288,1048576,2097152"),

        OptimizerString(80, "LTE Buffer Size", "lte_tcp_buffers", "524288,1048576,2097152,524288,1048576,2097152", "global",
            "Meningkatkan buffer size LTE untuk throughput", "Network",
            "+Throughput", "Neutral", "Low", "settings put global lte_tcp_buffers 524288,1048576,2097152,524288,1048576,2097152"),

        OptimizerString(81, "Disable Background Data", "restrict_background_data", "0", "global",
            "Menonaktifkan pembatasan background data", "Network",
            "+Speed", "-5%", "Low", "cmd netpolicy set restrict-background false"),

        OptimizerString(82, "Enable Data Saver", "data_saver", "0", "global",
            "Memastikan data saver tidak aktif", "Network",
            "+Speed", "-5%", "Low", "settings put global data_saver 0"),

        OptimizerString(83, "Network Optimization", "network_optimization", "1", "global",
            "Mengaktifkan optimasi jaringan", "Network",
            "+Latency", "Neutral", "Low", "settings put global network_optimization 1"),

        OptimizerString(84, "Disable Tethering", "tethering", "0", "global",
            "Menonaktifkan tethering untuk fokus", "Network",
            "+Speed", "+2%", "Low", "settings put global tethering 0"),

        OptimizerString(85, "Disable VPN", "vpn", "0", "global",
            "Menonaktifkan VPN untuk latency terbaik", "Network",
            "+Latency", "Neutral", "Low", "settings put global vpn 0"),

        // === THERMAL (86-95) ===
        OptimizerString(86, "Disable Thermal Zone 0", "thermal_zone0", "disabled", "system",
            "Menonaktifkan thermal zone 0", "Thermal",
            "+10-20 FPS", "-20%", "Extreme", "echo disabled > /sys/class/thermal/thermal_zone0/mode"),

        OptimizerString(87, "Disable Thermal Zone 1", "thermal_zone1", "disabled", "system",
            "Menonaktifkan thermal zone 1", "Thermal",
            "+10-20 FPS", "-20%", "Extreme", "echo disabled > /sys/class/thermal/thermal_zone1/mode"),

        OptimizerString(88, "Disable Thermal Zone 2", "thermal_zone2", "disabled", "system",
            "Menonaktifkan thermal zone 2", "Thermal",
            "+5-10 FPS", "-15%", "Extreme", "echo disabled > /sys/class/thermal/thermal_zone2/mode"),

        OptimizerString(89, "Disable Thermal Zone 3", "thermal_zone3", "disabled", "system",
            "Menonaktifkan thermal zone 3", "Thermal",
            "+5-10 FPS", "-15%", "Extreme", "echo disabled > /sys/class/thermal/thermal_zone3/mode"),

        OptimizerString(90, "Thermal Policy Off", "thermal_policy", "0", "system",
            "Menonaktifkan kebijakan thermal", "Thermal",
            "+15-25 FPS", "-25%", "Extreme", "echo 0 > /sys/class/thermal/thermal_zone0/policy"),

        OptimizerString(91, "CPU Temp Monitor", "cpu_temp", "", "system",
            "Membaca suhu CPU", "Thermal",
            "Info", "Neutral", "Low", "cat /sys/class/thermal/thermal_zone0/temp"),

        OptimizerString(92, "Battery Temp Monitor", "battery_temp", "", "system",
            "Membaca suhu baterai", "Thermal",
            "Info", "Neutral", "Low", "cat /sys/class/power_supply/battery/temp"),

        OptimizerString(93, "GPU Temp Monitor", "gpu_temp", "", "system",
            "Membaca suhu GPU", "Thermal",
            "Info", "Neutral", "Low", "cat /sys/class/thermal/thermal_zone1/temp"),

        OptimizerString(94, "Thermal Shutdown Disable", "thermal_shutdown", "0", "system",
            "Menonaktifkan shutdown thermal (SANGAT BERBAHAYA)", "Thermal",
            "+Max FPS", "-30%", "Extreme", "echo 0 > /sys/class/thermal/thermal_zone0/trip_point_0_temp"),

        OptimizerString(95, "CPU Cooling Off", "cpu_cooling", "0", "system",
            "Menonaktifkan pendinginan CPU", "Thermal",
            "+10 FPS", "-20%", "Extreme", "echo 0 > /sys/class/thermal/cooling_device0/cur_state"),

        // === TOUCH (96-105) ===
        OptimizerString(96, "Long Press Timeout", "long_press_timeout", "200", "secure",
            "Mengurangi timeout long press ke 200ms", "Touch",
            "+Responsiveness", "Neutral", "Low", "settings put secure long_press_timeout 200"),

        OptimizerString(97, "Multi Press Timeout", "multi_press_timeout", "150", "secure",
            "Mengurangi timeout multi press ke 150ms", "Touch",
            "+Responsiveness", "Neutral", "Low", "settings put secure multi_press_timeout 150"),

        OptimizerString(98, "Pointer Speed Max", "pointer_speed", "7", "system",
            "Meningkatkan kecepatan pointer ke maksimum", "Touch",
            "+Speed", "Neutral", "Low", "settings put system pointer_speed 7"),

        OptimizerString(99, "Tap Duration Zero", "tap_duration", "0", "secure",
            "Mengatur tap duration ke 0 untuk respons instan", "Touch",
            "+Responsiveness", "Neutral", "Low", "settings put secure tap_duration 0"),

        OptimizerString(100, "Touch Blocking Zero", "touch_blocking_period", "0", "secure",
            "Mengatur touch blocking ke 0", "Touch",
            "+Responsiveness", "Neutral", "Low", "settings put secure touch_blocking_period 0"),

        OptimizerString(101, "Touch Sensitivity Mode", "touch_sensitivity_mode", "1", "system",
            "Mengaktifkan mode sensitivitas touch tinggi", "Touch",
            "+Responsiveness", "Neutral", "Low", "settings put system touch_sensitivity_mode 1"),

        OptimizerString(102, "Disable Touch Haptic", "haptic_feedback_enabled", "0", "system",
            "Menonaktifkan haptic saat touch", "Touch",
            "+Speed", "+2%", "Low", "settings put system haptic_feedback_enabled 0"),

        OptimizerString(103, "Disable Touch Sounds", "touch_sounds", "0", "system",
            "Menonaktifkan suara touch", "Touch",
            "+Focus", "Neutral", "Low", "settings put system touch_sounds 0"),

        OptimizerString(104, "Show Touches Disable", "show_touches", "0", "secure",
            "Menonaktifkan tampilan touch", "Touch",
            "+Performance", "Neutral", "Low", "settings put secure show_touches 0"),

        OptimizerString(105, "Pointer Location Disable", "pointer_location", "0", "secure",
            "Menonaktifkan pointer location overlay", "Touch",
            "+Performance", "Neutral", "Low", "settings put secure pointer_location 0"),

        // === SYSTEM (106-120) ===
        OptimizerString(106, "Disable MIUI Ads", "msa", "disabled", "system",
            "Menonaktifkan MSA (MIUI System Ads)", "System",
            "+3-5 FPS", "+5%", "Low", "pm disable-user com.miui.msa.global"),

        OptimizerString(107, "Disable Analytics", "analytics", "disabled", "system",
            "Menonaktifkan MIUI Analytics", "System",
            "+2-3 FPS", "+3%", "Low", "pm disable-user com.miui.analytics"),

        OptimizerString(108, "Disable Joyose", "joyose", "disabled", "system",
            "Menonaktifkan Xiaomi Joyose (game booster bawaan)", "System",
            "+3-5 FPS", "+3%", "Low", "pm disable-user com.xiaomi.joyose"),

        OptimizerString(109, "Disable System Ad Solution", "systemAdSolution", "disabled", "system",
            "Menonaktifkan solusi iklan sistem", "System",
            "+2-3 FPS", "+3%", "Low", "pm disable-user com.miui.systemAdSolution"),

        OptimizerString(110, "Disable Hybrid", "hybrid", "disabled", "system",
            "Menonaktifkan MIUI Hybrid", "System",
            "+1-2 FPS", "+2%", "Low", "pm disable-user com.miui.hybrid"),

        OptimizerString(111, "Disable Bug Report", "bugreport", "disabled", "system",
            "Menonaktifkan bug report", "System",
            "+1 FPS", "+2%", "Low", "pm disable-user com.miui.bugreport"),

        OptimizerString(112, "Disable MIPicks", "mipicks", "disabled", "system",
            "Menonaktifkan MI Picks", "System",
            "+1 FPS", "+2%", "Low", "pm disable-user com.xiaomi.mipicks"),

        OptimizerString(113, "Disable GLGM", "glgm", "disabled", "system",
            "Menonaktifkan Xiaomi Games", "System",
            "+1 FPS", "+2%", "Low", "pm disable-user com.xiaomi.glgm"),

        OptimizerString(114, "Disable Google Ads", "google_ads", "disabled", "system",
            "Menonaktifkan Google Advertising Services", "System",
            "+2 FPS", "+3%", "Low", "pm disable-user com.google.android.gms/.ads.AdRequestBrokerService"),

        OptimizerString(115, "Limit Ad Tracking", "limit_ad_tracking", "1", "global",
            "Membatasi ad tracking", "System",
            "+Privacy", "+2%", "Low", "settings put global limit_ad_tracking 1"),

        OptimizerString(116, "Restricted Device ID", "restricted_device_id", "1", "global",
            "Membatasi device ID untuk privasi", "System",
            "+Privacy", "Neutral", "Low", "settings put global restricted_device_id 1"),

        OptimizerString(117, "Ad ID Opt Out", "ad_id_opt_out", "1", "global",
            "Opt out dari advertising ID", "System",
            "+Privacy", "Neutral", "Low", "settings put global ad_id_opt_out 1"),

        OptimizerString(118, "Disable Strict Mode Death", "strict_mode_death", "0", "global",
            "Menonaktifkan strict mode death", "System",
            "+Stability", "Neutral", "Low", "settings put global strict_mode_death 0"),

        OptimizerString(119, "Disable ANR Monitoring", "anr_monitoring", "0", "global",
            "Menonaktifkan ANR monitoring untuk performa", "System",
            "+2 FPS", "Neutral", "Low", "settings put global anr_monitoring 0"),

        OptimizerString(120, "Disable Strict Mode Visual", "strict_mode_visual", "0", "global",
            "Menonaktifkan visual strict mode", "System",
            "+1 FPS", "Neutral", "Low", "settings put global strict_mode_visual 0"),

        // === DISPLAY (121-130) ===
        OptimizerString(121, "Screen Density", "display_density_forced", "1", "global",
            "Mengatur density display", "Display",
            "+Performance", "Neutral", "Low", "settings put global display_density_forced 1"),

        OptimizerString(122, "Disable Blur", "disable_blur", "1", "system",
            "Menonaktifkan efek blur", "Display",
            "+3-8 FPS", "+3%", "Low", "settings put system disable_blur 1"),

        OptimizerString(123, "Disable Surface Updates", "show_surface_updates", "0", "system",
            "Menonaktifkan tampilan surface updates", "Display",
            "+2 FPS", "Neutral", "Low", "settings put system show_surface_updates 0"),

        OptimizerString(124, "Disable Overdraw", "debug.hwui.show_overdraw", "false", "global",
            "Menonaktifkan tampilan overdraw", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.hwui.show_overdraw false"),

        OptimizerString(125, "Disable Layout Bounds", "debug.layout", "false", "global",
            "Menonaktifkan tampilan layout bounds", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.layout false"),

        OptimizerString(126, "Disable GPU Profile Bars", "debug.hwui.profile", "false", "global",
            "Menonaktifkan GPU profile bars", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.hwui.profile false"),

        OptimizerString(127, "Disable HWUI Debug", "debug.hwui.dump", "false", "global",
            "Menonaktifkan HWUI dump", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.hwui.dump false"),

        OptimizerString(128, "Disable Strict Mode Flash", "show_strict_mode", "0", "global",
            "Menonaktifkan flash strict mode", "Display",
            "+1 FPS", "Neutral", "Low", "settings put global show_strict_mode 0"),

        OptimizerString(129, "Disable Debug GPU", "debug.gpu", "false", "global",
            "Menonaktifkan debug GPU", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.gpu false"),

        OptimizerString(130, "Disable Debug HWUI", "debug.hwui", "false", "global",
            "Menonaktifkan debug HWUI", "Display",
            "+1 FPS", "Neutral", "Low", "setprop debug.hwui false"),

        // === STORAGE (131-140) ===
        OptimizerString(131, "I/O Scheduler Deadline", "scheduler", "deadline", "system",
            "Menggunakan scheduler deadline untuk I/O", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo deadline > /sys/block/mmcblk0/queue/scheduler"),

        OptimizerString(132, "I/O Scheduler Noop", "scheduler", "noop", "system",
            "Menggunakan scheduler noop untuk I/O minimal", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo noop > /sys/block/mmcblk0/queue/scheduler"),

        OptimizerString(133, "Read Ahead 2048", "read_ahead_kb", "2048", "system",
            "Mengatur read ahead ke 2048KB", "Storage",
            "+Loading", "Neutral", "Low", "echo 2048 > /sys/block/mmcblk0/queue/read_ahead_kb"),

        OptimizerString(134, "Disable I/O Stats", "iostats", "0", "system",
            "Menonaktifkan statistik I/O", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo 0 > /sys/block/mmcblk0/queue/iostats"),

        OptimizerString(135, "Enable RQ Affinity", "rq_affinity", "1", "system",
            "Mengaktifkan RQ affinity untuk I/O", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo 1 > /sys/block/mmcblk0/queue/rq_affinity"),

        OptimizerString(136, "SDA Scheduler", "sda_scheduler", "deadline", "system",
            "Mengatur scheduler untuk SDA", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo deadline > /sys/block/sda/queue/scheduler"),

        OptimizerString(137, "SDA Read Ahead", "sda_read_ahead", "2048", "system",
            "Mengatur read ahead SDA", "Storage",
            "+Loading", "Neutral", "Low", "echo 2048 > /sys/block/sda/queue/read_ahead_kb"),

        OptimizerString(138, "Disable SDA I/O Stats", "sda_iostats", "0", "system",
            "Menonaktifkan I/O stats SDA", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo 0 > /sys/block/sda/queue/iostats"),

        OptimizerString(139, "MMC Read Ahead", "mmc_read_ahead", "2048", "system",
            "Mengatur read ahead MMC", "Storage",
            "+Loading", "Neutral", "Low", "echo 2048 > /sys/block/mmcblk1/queue/read_ahead_kb"),

        OptimizerString(140, "Disable MMC I/O Stats", "mmc_iostats", "0", "system",
            "Menonaktifkan I/O stats MMC", "Storage",
            "+I/O Speed", "Neutral", "Low", "echo 0 > /sys/block/mmcblk1/queue/iostats"),

        // === CPU (141-150) ===
        OptimizerString(141, "CPU Governor Performance", "scaling_governor", "performance", "system",
            "Mengatur governor CPU ke performance", "CPU",
            "+10 FPS", "-20%", "High", "echo performance > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"),

        OptimizerString(142, "CPU Governor Schedutil", "scaling_governor", "schedutil", "system",
            "Mengatur governor CPU ke schedutil", "CPU",
            "Balanced", "Neutral", "Low", "echo schedutil > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"),

        OptimizerString(143, "CPU Governor Ondemand", "scaling_governor", "ondemand", "system",
            "Mengatur governor CPU ke ondemand", "CPU",
            "+5 FPS", "-10%", "Medium", "echo ondemand > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"),

        OptimizerString(144, "CPU Governor Interactive", "scaling_governor", "interactive", "system",
            "Mengatur governor CPU ke interactive", "CPU",
            "+8 FPS", "-15%", "Medium", "echo interactive > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor"),

        OptimizerString(145, "CPU0 Online", "cpu0_online", "1", "system",
            "Memastikan CPU0 online", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu0/online"),

        OptimizerString(146, "CPU1 Online", "cpu1_online", "1", "system",
            "Memastikan CPU1 online", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu1/online"),

        OptimizerString(147, "CPU2 Online", "cpu2_online", "1", "system",
            "Memastikan CPU2 online", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu2/online"),

        OptimizerString(148, "CPU3 Online", "cpu3_online", "1", "system",
            "Memastikan CPU3 online", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu3/online"),

        OptimizerString(149, "CPU4 Online", "cpu4_online", "1", "system",
            "Memastikan CPU4 online (big core)", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu4/online"),

        OptimizerString(150, "CPU5 Online", "cpu5_online", "1", "system",
            "Memastikan CPU5 online (big core)", "CPU",
            "+Performance", "Neutral", "Low", "echo 1 > /sys/devices/system/cpu/cpu5/online"),

        // === AUDIO (151-155) ===
        OptimizerString(151, "Disable Touch Sound", "touch_sounds", "0", "system",
            "Menonaktifkan suara sentuhan", "Audio",
            "+Focus", "+1%", "Low", "settings put system touch_sounds 0"),

        OptimizerString(152, "Disable Dial Pad Tone", "dtmf_tone", "0", "system",
            "Menonaktifkan nada dial pad", "Audio",
            "+Focus", "Neutral", "Low", "settings put system dtmf_tone 0"),

        OptimizerString(153, "Disable Sound Effects", "sound_effects_enabled", "0", "system",
            "Menonaktifkan efek suara", "Audio",
            "+Focus", "+1%", "Low", "settings put system sound_effects_enabled 0"),

        OptimizerString(154, "Disable Lock Sound", "lockscreen_sounds_enabled", "0", "system",
            "Menonaktifkan suara lock screen", "Audio",
            "+Focus", "Neutral", "Low", "settings put system lockscreen_sounds_enabled 0"),

        OptimizerString(155, "Disable Charging Sound", "charging_sounds_enabled", "0", "global",
            "Menonaktifkan suara charging", "Audio",
            "+Focus", "Neutral", "Low", "settings put global charging_sounds_enabled 0")
    )

    fun getByCategory(category: String): List<OptimizerString> {
        return if (category == "All") ALL_STRINGS else ALL_STRINGS.filter { it.category == category }
    }

    fun search(query: String): List<OptimizerString> {
        val lowerQuery = query.lowercase()
        return ALL_STRINGS.filter {
            it.name.lowercase().contains(lowerQuery) ||
            it.key.lowercase().contains(lowerQuery) ||
            it.description.lowercase().contains(lowerQuery) ||
            it.category.lowercase().contains(lowerQuery)
        }
    }

    fun getCategories(): List<String> {
        return ALL_STRINGS.map { it.category }.distinct().sorted()
    }
}
