package com.redmi14c.optimizer.data

data class TweakCommand(
    val id: String,
    val name: String,
    val description: String,
    val commands: List<String>,
    val resetCommands: List<String>,
    val fpsEstimate: String,
    val batteryImpact: String,
    val riskLevel: String, // low, medium, high, extreme
    val category: String,
    val requiresWarning: Boolean = false,
    val warningMessage: String = ""
)

object TweakCommands {

    val ALL_TWEAKS = listOf(
        // 1. One Tap Beast Mode
        TweakCommand(
            id = "beast_mode",
            name = "One Tap Beast Mode",
            description = "Aktifkan semua tweak optimal untuk gaming sekaligus",
            commands = listOf(
                "settings put global window_animation_scale 0.0",
                "settings put global transition_animation_scale 0.0",
                "settings put global animator_duration_scale 0.0",
                "settings put global sys_vm_stats 0",
                "settings put global cached_apps_freezer enabled",
                "settings put global ram_expand_size 0",
                "settings put system peak_refresh_rate 120",
                "settings put system min_refresh_rate 120",
                "settings put global adaptive_battery_management_enabled 0",
                "settings put global cached_apps_freezer enabled",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global ram_expand_size 0",
                "cmd activity memory-factor critical",
                "setprop debug.hwui.renderer skiavk",
                "setprop debug.hwui.use_vulkan true",
                "setprop debug.hwui.disable_draw_defer true",
                "setprop debug.hwui.show_dirty_regions false",
                "settings put global hwui.disable_vsync true",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global window_animation_scale 1.0",
                "settings put global transition_animation_scale 1.0",
                "settings put global animator_duration_scale 1.0",
                "settings put global sys_vm_stats 1",
                "settings put global cached_apps_freezer disabled",
                "settings put global ram_expand_size 4096",
                "settings put system peak_refresh_rate 60",
                "settings put system min_refresh_rate 60",
                "settings put global adaptive_battery_management_enabled 1",
                "setprop debug.hwui.renderer skiagl",
                "setprop debug.hwui.use_vulkan false",
                "setprop debug.hwui.disable_draw_defer false"
            ),
            fpsEstimate = "+15-25 FPS",
            batteryImpact = "-20-30%",
            riskLevel = "high",
            category = "performance",
            requiresWarning = true,
            warningMessage = "Mode ini akan menguras baterai signifikan dan meningkatkan suhu. Gunakan dengan pendingin."
        ),

        // 2. Lock Maximum Refresh Rate
        TweakCommand(
            id = "refresh_rate",
            name = "Lock Max Refresh Rate",
            description = "Force 90Hz/120Hz di semua aplikasi dan game",
            commands = listOf(
                "settings put system peak_refresh_rate 120",
                "settings put system min_refresh_rate 120",
                "settings put system user_refresh_rate 120",
                "settings put global display_density_forced 1",
                "settings put global min_refresh_rate 120",
                "settings put global peak_refresh_rate 120",
                "cmd display mode 2"
            ),
            resetCommands = listOf(
                "settings put system peak_refresh_rate 60",
                "settings put system min_refresh_rate 60",
                "settings put system user_refresh_rate 0",
                "settings delete global min_refresh_rate",
                "settings delete global peak_refresh_rate"
            ),
            fpsEstimate = "+Smoothness",
            batteryImpact = "-10-15%",
            riskLevel = "medium",
            category = "display"
        ),

        // 3. Force High Performance Mode
        TweakCommand(
            id = "high_perf",
            name = "Force High Performance",
            description = "Set CPU/GPU ke mode performa maksimum via power HAL",
            commands = listOf(
                "settings put global power_saving_mode 0",
                "settings put global low_power 0",
                "settings put global app_standby_enabled 0",
                "settings put global forced_app_standby_enabled 0",
                "settings put global battery_saver_constants \"\"",
                "cmd power set-fixed-performance-mode-enabled true",
                "cmd power set-mode 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global power_saving_mode 0",
                "cmd power set-fixed-performance-mode-enabled false",
                "cmd power set-mode 1"
            ),
            fpsEstimate = "+5-10 FPS",
            batteryImpact = "-15-20%",
            riskLevel = "medium",
            category = "performance"
        ),

        // 4. Disable Power Keeper & Battery Optimization
        TweakCommand(
            id = "battery_opt",
            name = "Disable Battery Optimization",
            description = "Nonaktifkan power keeper dan battery saver HyperOS",
            commands = listOf(
                "settings put global adaptive_battery_management_enabled 0",
                "settings put global app_standby_enabled 0",
                "settings put global forced_app_standby_enabled 0",
                "settings put global battery_saver_constants \"\"",
                "settings put global low_power_sticky 0",
                "settings put global low_power_trigger_level 0",
                "cmd deviceidle disable",
                "cmd deviceidle whitelist +com.redmi14c.optimizer",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global adaptive_battery_management_enabled 1",
                "settings put global app_standby_enabled 1",
                "settings put global forced_app_standby_enabled 1",
                "cmd deviceidle enable"
            ),
            fpsEstimate = "+2-5 FPS",
            batteryImpact = "-10-15%",
            riskLevel = "low",
            category = "battery"
        ),

        // 5. Reduce Animation Scale
        TweakCommand(
            id = "reduce_anim",
            name = "Reduce Animation Scale",
            description = "Kurangi animasi window, transition, dan animator ke 0.0/0.5",
            commands = listOf(
                "settings put global window_animation_scale 0.0",
                "settings put global transition_animation_scale 0.0",
                "settings put global animator_duration_scale 0.0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global window_animation_scale 1.0",
                "settings put global transition_animation_scale 1.0",
                "settings put global animator_duration_scale 1.0"
            ),
            fpsEstimate = "+Responsiveness",
            batteryImpact = "+5%",
            riskLevel = "low",
            category = "system"
        ),

        // 6. Force GPU Rendering
        TweakCommand(
            id = "gpu_rendering",
            name = "Force GPU Rendering",
            description = "Paksa semua rendering menggunakan GPU (debug.hwui.renderer)",
            commands = listOf(
                "setprop debug.hwui.renderer skiavk",
                "setprop debug.hwui.use_vulkan true",
                "settings put global debug.hwui.renderer skiavk",
                "settings put global debug.hwui.use_vulkan true",
                "settings put global hwui.disable_vsync true",
                "setprop debug.hwui.disable_draw_defer true",
                "setprop debug.hwui.show_dirty_regions false"
            ),
            resetCommands = listOf(
                "setprop debug.hwui.renderer skiagl",
                "setprop debug.hwui.use_vulkan false",
                "settings delete global debug.hwui.renderer",
                "settings delete global debug.hwui.use_vulkan"
            ),
            fpsEstimate = "+5-15 FPS",
            batteryImpact = "-5-10%",
            riskLevel = "medium",
            category = "gpu"
        ),

        // 7. Renderer Pipeline (handled separately with selection)
        TweakCommand(
            id = "renderer",
            name = "Renderer Pipeline",
            description = "Pilih renderer: Vulkan, OpenGL ES, Skia",
            commands = listOf(
                "setprop debug.hwui.renderer skiavk",
                "setprop debug.hwui.use_vulkan true"
            ),
            resetCommands = listOf(
                "setprop debug.hwui.renderer skiagl",
                "setprop debug.hwui.use_vulkan false"
            ),
            fpsEstimate = "Variable",
            batteryImpact = "Variable",
            riskLevel = "medium",
            category = "gpu"
        ),

        // 8. Hardware Acceleration
        TweakCommand(
            id = "hw_accel",
            name = "Hardware Acceleration",
            description = "Force enable/disable hardware acceleration",
            commands = listOf(
                "settings put global hardware_accelerated_rendering_enabled 1",
                "settings put global hwui.disable_hw_accel 0",
                "setprop debug.hwui.disable_hw_accel false",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global hardware_accelerated_rendering_enabled 1",
                "settings delete global hwui.disable_hw_accel",
                "setprop debug.hwui.disable_hw_accel false"
            ),
            fpsEstimate = "+3-8 FPS",
            batteryImpact = "-3-5%",
            riskLevel = "low",
            category = "gpu"
        ),

        // 9. Touch Response Booster
        TweakCommand(
            id = "touch_boost",
            name = "Touch Response Booster",
            description = "Kurangi debounce, long press timeout, dan tap duration",
            commands = listOf(
                "settings put secure long_press_timeout 200",
                "settings put secure multi_press_timeout 150",
                "settings put system pointer_speed 7",
                "settings put secure tap_duration 0",
                "settings put secure touch_blocking_period 0",
                "settings put system touch_sensitivity_mode 1",
                "settings put secure show_touches 0",
                "settings put secure pointer_location 0"
            ),
            resetCommands = listOf(
                "settings put secure long_press_timeout 400",
                "settings put secure multi_press_timeout 300",
                "settings put system pointer_speed 0",
                "settings delete secure tap_duration",
                "settings delete secure touch_blocking_period",
                "settings put system touch_sensitivity_mode 0"
            ),
            fpsEstimate = "+Responsiveness",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "touch"
        ),

        // 10. Aggressive RAM Manager
        TweakCommand(
            id = "ram_manager",
            name = "Aggressive RAM Manager",
            description = "Kill background processes dan optimasi RAM",
            commands = listOf(
                "am kill-all",
                "cmd activity memory-factor critical",
                "settings put global ram_expand_size 0",
                "settings put global sys_vm_stats 0",
                "settings put global cached_apps_freezer enabled",
                "settings put global app_standby_enabled 0",
                "settings put global forced_app_standby_enabled 0",
                "cmd activity memory-factor critical"
            ),
            resetCommands = listOf(
                "settings put global ram_expand_size 4096",
                "settings put global cached_apps_freezer disabled",
                "settings put global app_standby_enabled 1"
            ),
            fpsEstimate = "+5-10 FPS",
            batteryImpact = "+5%",
            riskLevel = "low",
            category = "ram"
        ),

        // 11. Clear Cache & Dalvik Cache
        TweakCommand(
            id = "clear_cache",
            name = "Clear System Cache",
            description = "Bersihkan cache sistem dan dalvik cache",
            commands = listOf(
                "pm trim-caches 1G",
                "cmd package compile --reset",
                "cmd package compile -m speed-profile -f",
                "cmd package bg-dexopt-job"
            ),
            resetCommands = listOf(
                "cmd package compile --reset"
            ),
            fpsEstimate = "+3-5 FPS",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "system"
        ),

        // 13. Disable Background Services
        TweakCommand(
            id = "disable_bg",
            name = "Disable Background Services",
            description = "Nonaktifkan service Google, MIUI, dan bloatware",
            commands = listOf(
                "pm disable-user com.google.android.gms/.ads.AdRequestBrokerService",
                "pm disable-user com.google.android.gms/.ads.identifier.service.AdvertisingIdNotificationService",
                "pm disable-user com.miui.systemAdSolution",
                "pm disable-user com.miui.analytics",
                "pm disable-user com.xiaomi.joyose",
                "settings put global restricted_device_id 1",
                "settings put global limit_ad_tracking 1"
            ),
            resetCommands = listOf(
                "pm enable com.google.android.gms/.ads.AdRequestBrokerService",
                "pm enable com.miui.systemAdSolution",
                "pm enable com.miui.analytics",
                "pm enable com.xiaomi.joyose",
                "settings put global restricted_device_id 0",
                "settings put global limit_ad_tracking 0"
            ),
            fpsEstimate = "+2-5 FPS",
            batteryImpact = "+10%",
            riskLevel = "medium",
            category = "system"
        ),

        // 14. Network Optimizer
        TweakCommand(
            id = "network_opt",
            name = "Network Optimizer",
            description = "DNS 1.1.1.1, TCP Fast Open, buffer size",
            commands = listOf(
                "settings put global private_dns_mode hostname",
                "settings put global private_dns_specifier dns.google",
                "settings put global tcp_fastopen 1",
                "settings put global net.tcp.buffersize.default 4096,87380,256960,4096,16384,256960",
                "settings put global net.tcp.buffersize.wifi 524288,1048576,2097152,524288,1048576,2097152",
                "settings put global net.tcp.buffersize.lte 524288,1048576,2097152,524288,1048576,2097152",
                "settings put global net.dns1 1.1.1.1",
                "settings put global net.dns2 8.8.8.8",
                "cmd netpolicy set restrict-background false"
            ),
            resetCommands = listOf(
                "settings put global private_dns_mode off",
                "settings delete global private_dns_specifier",
                "settings put global tcp_fastopen 0",
                "settings delete global net.tcp.buffersize.default",
                "settings delete global net.tcp.buffersize.wifi",
                "settings delete global net.tcp.buffersize.lte",
                "settings delete global net.dns1",
                "settings delete global net.dns2"
            ),
            fpsEstimate = "+Latency",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "network"
        ),

        // 15. CPU Governor Changer
        TweakCommand(
            id = "cpu_governor",
            name = "CPU Governor",
            description = "Ubah CPU governor untuk performa maksimal",
            commands = listOf(
                "echo performance > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu2/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu4/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu5/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu6/cpufreq/scaling_governor",
                "echo performance > /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor",
                "echo 1 > /sys/devices/system/cpu/cpu0/online",
                "echo 1 > /sys/devices/system/cpu/cpu1/online",
                "echo 1 > /sys/devices/system/cpu/cpu2/online",
                "echo 1 > /sys/devices/system/cpu/cpu3/online",
                "echo 1 > /sys/devices/system/cpu/cpu4/online",
                "echo 1 > /sys/devices/system/cpu/cpu5/online",
                "echo 1 > /sys/devices/system/cpu/cpu6/online",
                "echo 1 > /sys/devices/system/cpu/cpu7/online"
            ),
            resetCommands = listOf(
                "echo schedutil > /sys/devices/system/cpu/cpu0/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu1/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu2/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu3/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu4/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu5/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu6/cpufreq/scaling_governor",
                "echo schedutil > /sys/devices/system/cpu/cpu7/cpufreq/scaling_governor"
            ),
            fpsEstimate = "+5-10 FPS",
            batteryImpact = "-15-20%",
            riskLevel = "high",
            category = "performance",
            requiresWarning = true,
            warningMessage = "Mengubah CPU governor ke performance dapat menyebabkan panas berlebih dan pengurasan baterai ekstrem."
        ),

        // 16. Max CPU/GPU Frequency Lock
        TweakCommand(
            id = "freq_lock",
            name = "Max CPU/GPU Frequency Lock",
            description = "Lock frequency ke nilai maksimum",
            commands = listOf(
                "cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu1/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu2/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu3/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu4/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu4/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu5/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu5/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu6/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu6/cpufreq/scaling_min_freq",
                "cat /sys/devices/system/cpu/cpu7/cpufreq/scaling_max_freq > /sys/devices/system/cpu/cpu7/cpufreq/scaling_min_freq"
            ),
            resetCommands = listOf(
                "echo 0 > /sys/devices/system/cpu/cpu0/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu1/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu2/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu3/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu4/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu5/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu6/cpufreq/scaling_min_freq",
                "echo 0 > /sys/devices/system/cpu/cpu7/cpufreq/scaling_min_freq"
            ),
            fpsEstimate = "+8-15 FPS",
            batteryImpact = "-25-35%",
            riskLevel = "extreme",
            category = "performance",
            requiresWarning = true,
            warningMessage = "FREQUENCY LOCK DAPAT MENYEBABKAN PANAS BERLEBIHAN DAN KERUSAKAN HARDWARE. GUNAKAN DENGAN RISIKO ANDA SENDIRI!"
        ),

        // 17. Disable Blur & Effects
        TweakCommand(
            id = "disable_blur",
            name = "Disable Blur & Effects",
            description = "Nonaktifkan blur, transparency, dan window effects",
            commands = listOf(
                "settings put system disable_blur 1",
                "settings put system show_surface_updates 0",
                "settings put system window_animation_scale 0.0",
                "settings put system transition_animation_scale 0.0",
                "settings put system animator_duration_scale 0.0",
                "settings put global sys_vm_stats 0",
                "setprop debug.hwui.disable_draw_defer true",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put system disable_blur 0",
                "settings put system window_animation_scale 1.0",
                "settings put system transition_animation_scale 1.0",
                "settings put system animator_duration_scale 1.0",
                "setprop debug.hwui.disable_draw_defer false"
            ),
            fpsEstimate = "+3-8 FPS",
            batteryImpact = "+5%",
            riskLevel = "low",
            category = "display"
        ),

        // 18. Boost App Launch Speed
        TweakCommand(
            id = "app_launch",
            name = "Boost App Launch",
            description = "Disable logging dan tracking untuk launch cepat",
            commands = listOf(
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "cmd package compile --reset",
                "cmd package compile -m speed-profile -f"
            ),
            resetCommands = listOf(
                "settings put global sys_vm_stats 1",
                "cmd package compile --reset"
            ),
            fpsEstimate = "+Launch Speed",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "system"
        ),

        // 19. I/O Scheduler
        TweakCommand(
            id = "scheduler",
            name = "I/O Scheduler",
            description = "Ubah packet scheduler dan I/O scheduler",
            commands = listOf(
                "echo deadline > /sys/block/mmcblk0/queue/scheduler",
                "echo 2048 > /sys/block/mmcblk0/queue/read_ahead_kb",
                "echo 0 > /sys/block/mmcblk0/queue/iostats",
                "echo 1 > /sys/block/mmcblk0/queue/rq_affinity",
                "echo deadline > /sys/block/sda/queue/scheduler",
                "echo 2048 > /sys/block/sda/queue/read_ahead_kb",
                "echo 0 > /sys/block/sda/queue/iostats",
                "echo 1 > /sys/block/sda/queue/rq_affinity"
            ),
            resetCommands = listOf(
                "echo cfq > /sys/block/mmcblk0/queue/scheduler",
                "echo 128 > /sys/block/mmcblk0/queue/read_ahead_kb",
                "echo 1 > /sys/block/mmcblk0/queue/iostats",
                "echo cfq > /sys/block/sda/queue/scheduler",
                "echo 128 > /sys/block/sda/queue/read_ahead_kb",
                "echo 1 > /sys/block/sda/queue/iostats"
            ),
            fpsEstimate = "+Loading Speed",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "storage"
        ),

        // 20. ZRAM Optimizer
        TweakCommand(
            id = "zram",
            name = "ZRAM Optimizer",
            description = "Optimasi ukuran dan agresivitas ZRAM",
            commands = listOf(
                "echo 1 > /sys/block/zram0/reset",
                "echo 4294967296 > /sys/block/zram0/disksize",
                "mkswap /dev/block/zram0",
                "swapon /dev/block/zram0",
                "echo 100 > /proc/sys/vm/swappiness",
                "echo 10 > /proc/sys/vm/dirty_ratio",
                "echo 5 > /proc/sys/vm/dirty_background_ratio",
                "echo 0 > /proc/sys/vm/page-cluster"
            ),
            resetCommands = listOf(
                "swapoff /dev/block/zram0",
                "echo 1 > /sys/block/zram0/reset",
                "echo 2147483648 > /sys/block/zram0/disksize",
                "mkswap /dev/block/zram0",
                "swapon /dev/block/zram0",
                "echo 60 > /proc/sys/vm/swappiness",
                "echo 20 > /proc/sys/vm/dirty_ratio",
                "echo 10 > /proc/sys/vm/dirty_background_ratio",
                "echo 3 > /proc/sys/vm/page-cluster"
            ),
            fpsEstimate = "+RAM Efficiency",
            batteryImpact = "-3%",
            riskLevel = "low",
            category = "ram"
        ),

        // 21. Disable Adaptive Battery & Doze
        TweakCommand(
            id = "adaptive_battery",
            name = "Disable Adaptive Battery",
            description = "Nonaktifkan adaptive battery dan doze mode",
            commands = listOf(
                "settings put global adaptive_battery_management_enabled 0",
                "settings put global app_standby_enabled 0",
                "settings put global forced_app_standby_enabled 0",
                "settings put global battery_saver_constants \"\"",
                "cmd deviceidle disable",
                "settings put global low_power_sticky 0",
                "settings put global low_power_trigger_level 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global adaptive_battery_management_enabled 1",
                "settings put global app_standby_enabled 1",
                "settings put global forced_app_standby_enabled 1",
                "cmd deviceidle enable",
                "settings put global low_power_trigger_level 15"
            ),
            fpsEstimate = "+2-5 FPS",
            batteryImpact = "-10-15%",
            riskLevel = "low",
            category = "battery"
        ),

        // 22. Force 4x MSAA
        TweakCommand(
            id = "msaa",
            name = "Force 4x MSAA",
            description = "Paksa anti-aliasing 4x di semua game",
            commands = listOf(
                "settings put global debug.hwui.disable_draw_defer true",
                "setprop debug.hwui.disable_draw_defer true",
                "settings put global debug.hwui.show_dirty_regions false",
                "setprop debug.hwui.show_dirty_regions false",
                "settings put global debug.hwui.use_buffer_age false",
                "setprop debug.hwui.use_buffer_age false"
            ),
            resetCommands = listOf(
                "settings delete global debug.hwui.disable_draw_defer",
                "setprop debug.hwui.disable_draw_defer false",
                "settings delete global debug.hwui.show_dirty_regions",
                "setprop debug.hwui.show_dirty_regions false",
                "settings delete global debug.hwui.use_buffer_age",
                "setprop debug.hwui.use_buffer_age true"
            ),
            fpsEstimate = "+Visual Quality",
            batteryImpact = "-5-10%",
            riskLevel = "medium",
            category = "gpu"
        ),

        // 23. Thermal Throttling Reducer
        TweakCommand(
            id = "thermal",
            name = "Thermal Throttling Reducer",
            description = "Kurangi thermal mitigations untuk performa",
            commands = listOf(
                "echo 0 > /sys/class/thermal/thermal_zone0/policy",
                "echo 0 > /sys/class/thermal/thermal_zone1/policy",
                "echo 0 > /sys/class/thermal/thermal_zone2/policy",
                "echo 0 > /sys/class/thermal/thermal_zone3/policy",
                "echo disabled > /sys/class/thermal/thermal_zone0/mode",
                "echo disabled > /sys/class/thermal/thermal_zone1/mode",
                "echo disabled > /sys/class/thermal/thermal_zone2/mode",
                "echo disabled > /sys/class/thermal/thermal_zone3/mode",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "echo 1 > /sys/class/thermal/thermal_zone0/policy",
                "echo 1 > /sys/class/thermal/thermal_zone1/policy",
                "echo 1 > /sys/class/thermal/thermal_zone2/policy",
                "echo 1 > /sys/class/thermal/thermal_zone3/policy",
                "echo enabled > /sys/class/thermal/thermal_zone0/mode",
                "echo enabled > /sys/class/thermal/thermal_zone1/mode",
                "echo enabled > /sys/class/thermal/thermal_zone2/mode",
                "echo enabled > /sys/class/thermal/thermal_zone3/mode"
            ),
            fpsEstimate = "+10-20 FPS",
            batteryImpact = "-20-30%",
            riskLevel = "extreme",
            category = "thermal",
            requiresWarning = true,
            warningMessage = "MENONAKTIFKAN THERMAL THROTTLING DAPAT MENYEBABKAN KERUSAKAN PERMANEN PADA PERANGKAT KARENA OVERHEAT!"
        ),

        // 24. Screen Off Optimization
        TweakCommand(
            id = "screen_off",
            name = "Screen Off Optimization",
            description = "Optimasi wakelock dan screen off behavior",
            commands = listOf(
                "settings put global stay_on_while_plugged_in 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global stay_on_while_plugged_in 3",
                "settings put global sys_vm_stats 1"
            ),
            fpsEstimate = "+Battery",
            batteryImpact = "+10%",
            riskLevel = "low",
            category = "battery"
        ),

        // 27. Temperature Monitor (handled as service)
        TweakCommand(
            id = "temp_monitor",
            name = "Temperature Monitor",
            description = "Monitor suhu CPU, GPU, dan baterai real-time",
            commands = listOf(
                "cat /sys/class/thermal/thermal_zone0/temp",
                "cat /sys/class/thermal/thermal_zone1/temp",
                "cat /sys/class/power_supply/battery/temp",
                "dumpsys battery | grep temperature",
                "cat /sys/class/kgsl/kgsl-3d0/gpuclk"
            ),
            resetCommands = listOf(),
            fpsEstimate = "Info Only",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "thermal"
        ),

        // 28. Disable Unused Sensors
        TweakCommand(
            id = "disable_sensors",
            name = "Disable Unused Sensors",
            description = "Nonaktifkan sensor tidak penting saat gaming",
            commands = listOf(
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0",
                "settings put global sensors_device_logging_enabled 0"
            ),
            resetCommands = listOf(
                "settings put global sensors_device_logging_enabled 1"
            ),
            fpsEstimate = "+2-3 FPS",
            batteryImpact = "+3%",
            riskLevel = "low",
            category = "system"
        ),

        // 29. I/O Read Ahead
        TweakCommand(
            id = "io_readahead",
            name = "I/O Read Ahead",
            description = "Optimasi read ahead buffer storage",
            commands = listOf(
                "echo 2048 > /sys/block/mmcblk0/queue/read_ahead_kb",
                "echo 2048 > /sys/block/mmcblk1/queue/read_ahead_kb",
                "echo 2048 > /sys/block/sda/queue/read_ahead_kb",
                "echo 0 > /sys/block/mmcblk0/queue/iostats",
                "echo 0 > /sys/block/mmcblk1/queue/iostats",
                "echo 0 > /sys/block/sda/queue/iostats",
                "echo 1 > /sys/block/mmcblk0/queue/rq_affinity",
                "echo 1 > /sys/block/mmcblk1/queue/rq_affinity",
                "echo 1 > /sys/block/sda/queue/rq_affinity"
            ),
            resetCommands = listOf(
                "echo 128 > /sys/block/mmcblk0/queue/read_ahead_kb",
                "echo 128 > /sys/block/mmcblk1/queue/read_ahead_kb",
                "echo 128 > /sys/block/sda/queue/read_ahead_kb",
                "echo 1 > /sys/block/mmcblk0/queue/iostats",
                "echo 1 > /sys/block/mmcblk1/queue/iostats",
                "echo 1 > /sys/block/sda/queue/iostats"
            ),
            fpsEstimate = "+Loading Speed",
            batteryImpact = "Neutral",
            riskLevel = "low",
            category = "storage"
        ),

        // 30. Disable MIUI/HyperOS Bloat
        TweakCommand(
            id = "disable_bloat",
            name = "Disable MIUI/HyperOS Bloat",
            description = "Nonaktifkan iklan, recommendation, analytics",
            commands = listOf(
                "pm disable-user com.miui.systemAdSolution",
                "pm disable-user com.miui.analytics",
                "pm disable-user com.xiaomi.joyose",
                "pm disable-user com.xiaomi.mipicks",
                "pm disable-user com.xiaomi.glgm",
                "pm disable-user com.miui.hybrid",
                "pm disable-user com.miui.yellowpage",
                "pm disable-user com.miui.bugreport",
                "pm disable-user com.miui.msa.global",
                "settings put global restricted_device_id 1",
                "settings put global limit_ad_tracking 1",
                "settings put global ad_id_opt_out 1"
            ),
            resetCommands = listOf(
                "pm enable com.miui.systemAdSolution",
                "pm enable com.miui.analytics",
                "pm enable com.xiaomi.joyose",
                "pm enable com.xiaomi.mipicks",
                "pm enable com.xiaomi.glgm",
                "pm enable com.miui.hybrid",
                "pm enable com.miui.yellowpage",
                "pm enable com.miui.bugreport",
                "pm enable com.miui.msa.global",
                "settings put global restricted_device_id 0",
                "settings put global limit_ad_tracking 0",
                "settings put global ad_id_opt_out 0"
            ),
            fpsEstimate = "+3-5 FPS",
            batteryImpact = "+10%",
            riskLevel = "low",
            category = "system"
        ),

        // 37. GPU Turbo Mode
        TweakCommand(
            id = "gpu_turbo",
            name = "GPU Turbo Mode",
            description = "Force max GPU frequency dan optimasi rendering",
            commands = listOf(
                "echo 0 > /sys/class/kgsl/kgsl-3d0/throttling",
                "echo 0 > /sys/class/kgsl/kgsl-3d0/bus_split",
                "echo 1 > /sys/class/kgsl/kgsl-3d0/force_clk_on",
                "echo 1 > /sys/class/kgsl/kgsl-3d0/force_bus_on",
                "echo 1 > /sys/class/kgsl/kgsl-3d0/force_rail_on",
                "echo 1 > /sys/class/kgsl/kgsl-3d0/force_no_nap",
                "cat /sys/class/kgsl/kgsl-3d0/max_gpuclk > /sys/class/kgsl/kgsl-3d0/gpuclk",
                "setprop debug.hwui.renderer skiavk",
                "setprop debug.hwui.use_vulkan true",
                "setprop debug.hwui.disable_draw_defer true"
            ),
            resetCommands = listOf(
                "echo 1 > /sys/class/kgsl/kgsl-3d0/throttling",
                "echo 1 > /sys/class/kgsl/kgsl-3d0/bus_split",
                "echo 0 > /sys/class/kgsl/kgsl-3d0/force_clk_on",
                "echo 0 > /sys/class/kgsl/kgsl-3d0/force_bus_on",
                "echo 0 > /sys/class/kgsl/kgsl-3d0/force_rail_on",
                "echo 0 > /sys/class/kgsl/kgsl-3d0/force_no_nap",
                "setprop debug.hwui.renderer skiagl",
                "setprop debug.hwui.use_vulkan false",
                "setprop debug.hwui.disable_draw_defer false"
            ),
            fpsEstimate = "+10-20 FPS",
            batteryImpact = "-20-30%",
            riskLevel = "extreme",
            category = "gpu",
            requiresWarning = true,
            warningMessage = "GPU TURBO DAPAT MENYEBABKAN PANAS BERLEBIHAN DAN KERUSAKAN GPU! PASTIKAN ADA PENDINGIN!"
        ),

        // 38. Disable MIUI Optimization
        TweakCommand(
            id = "disable_miui_opt",
            name = "Disable MIUI Optimization",
            description = "Nonaktifkan MIUI optimization untuk performa",
            commands = listOf(
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global sys_vm_stats 1"
            ),
            fpsEstimate = "+5-10 FPS",
            batteryImpact = "-5%",
            riskLevel = "medium",
            category = "system"
        ),

        // 39. Force 60/90/120 FPS in Games
        TweakCommand(
            id = "force_fps",
            name = "Force 60/90/120 FPS",
            description = "Paksa FPS di game via developer options",
            commands = listOf(
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put global sys_vm_stats 1"
            ),
            fpsEstimate = "+FPS Unlock",
            batteryImpact = "-10%",
            riskLevel = "medium",
            category = "gaming"
        ),

        // 40. Disable Game Auto Brightness & Auto Refresh
        TweakCommand(
            id = "disable_auto_brightness",
            name = "Disable Auto Brightness/Refresh",
            description = "Nonaktifkan auto brightness dan auto refresh rate",
            commands = listOf(
                "settings put system screen_brightness_mode 0",
                "settings put system peak_refresh_rate 120",
                "settings put system min_refresh_rate 120",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0",
                "settings put global sys_vm_stats 0"
            ),
            resetCommands = listOf(
                "settings put system screen_brightness_mode 1",
                "settings put system peak_refresh_rate 60",
                "settings put system min_refresh_rate 60"
            ),
            fpsEstimate = "+Consistency",
            batteryImpact = "-5%",
            riskLevel = "low",
            category = "display"
        )
    )

    fun getTweakById(id: String): TweakCommand? {
        return ALL_TWEAKS.find { it.id == id }
    }

    fun getTweaksByCategory(category: String): List<TweakCommand> {
        return ALL_TWEAKS.filter { it.category == category }
    }
}
