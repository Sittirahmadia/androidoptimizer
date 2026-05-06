package com.redmi14c.optimizer.data

/**
 * Non-root optimizer strings that work on any Android device
 * These use settings, secure database, and other non-root methods
 */
object NonRootOptimizerStrings {
    val strings = listOf(
        // Display Settings (No Root Required)
        OptimizerString(
            name = "Reduce Animation Duration",
            key = "window_animation_scale",
            value = "0.5",
            type = "setting",
            description = "Reduce window animation scale in Developer Options (Settings)",
            category = "Display Optimization",
            fpsBoost = 5,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Transition Animation",
            key = "transition_animation_scale",
            value = "0.5",
            type = "setting",
            description = "Reduce transition animation in Developer Options",
            category = "Display Optimization",
            fpsBoost = 3,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Animator Duration",
            key = "animator_duration_scale",
            value = "0.5",
            type = "setting",
            description = "Reduce animator duration in Developer Options",
            category = "Display Optimization",
            fpsBoost = 4,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Haptic Feedback",
            key = "haptic_feedback_enabled",
            value = "0",
            type = "setting",
            description = "Turn off vibration feedback (Settings > Sound > Vibration)",
            category = "Display Optimization",
            fpsBoost = 1,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Auto Brightness",
            key = "screen_brightness_mode",
            value = "1",
            type = "setting",
            description = "Use adaptive brightness to save battery",
            category = "Display Optimization",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Screen Brightness",
            key = "screen_brightness",
            value = "80",
            type = "setting",
            description = "Reduce screen brightness to 80% (value: 0-255)",
            category = "Display Optimization",
            fpsBoost = 0,
            batteryImpact = -30,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Predictive Back",
            key = "enable_predictive_back_gesture",
            value = "0",
            type = "setting",
            description = "Disable predictive back gesture animation",
            category = "Display Optimization",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),

        // Performance Settings (No Root)
        OptimizerString(
            name = "Limit Background Apps",
            key = "background_processes_limit",
            value = "4",
            type = "setting",
            description = "Limit number of background processes (Developer Options)",
            category = "Performance Optimization",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Performance Mode",
            key = "perf_mode",
            value = "1",
            type = "setting",
            description = "Enable performance mode (if available on your device)",
            category = "Performance Optimization",
            fpsBoost = 15,
            batteryImpact = 10,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable Live Wallpaper",
            key = "live_wallpaper_enabled",
            value = "0",
            type = "setting",
            description = "Disable animated wallpapers (use static wallpaper)",
            category = "Performance Optimization",
            fpsBoost = 5,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Clear App Cache",
            key = "clear_cache",
            value = "1",
            type = "command",
            description = "Clear app cache regularly to free space",
            category = "Performance Optimization",
            fpsBoost = 3,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Automatic Updates",
            key = "auto_update",
            value = "0",
            type = "setting",
            description = "Disable automatic app updates (Settings > Apps)",
            category = "Performance Optimization",
            fpsBoost = 2,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable USB Debugging",
            key = "adb_enabled",
            value = "1",
            type = "setting",
            description = "Enable USB debugging for optimization tools",
            category = "Performance Optimization",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Unused Services",
            key = "disable_services",
            value = "1",
            type = "setting",
            description = "Disable unused services in App settings",
            category = "Performance Optimization",
            fpsBoost = 5,
            batteryImpact = -15,
            riskLevel = "Medium"
        ),

        // Power & Battery (No Root)
        OptimizerString(
            name = "Enable Battery Saver",
            key = "battery_saver_mode",
            value = "1",
            type = "setting",
            description = "Enable battery saver mode (Settings > Battery)",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -40,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Screen Timeout",
            key = "screen_off_timeout",
            value = "60000",
            type = "setting",
            description = "Set screen timeout to 1 minute (60000ms)",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable WiFi Auto-ON",
            key = "wifi_stay_on",
            value = "0",
            type = "setting",
            description = "Turn off WiFi when device sleeps",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Bluetooth",
            key = "bluetooth_enabled",
            value = "0",
            type = "setting",
            description = "Disable Bluetooth when not in use",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Location Services",
            key = "location_mode",
            value = "0",
            type = "setting",
            description = "Disable GPS/location when not needed",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -30,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Mobile Data When WiFi Connected",
            key = "mobile_data_always_on",
            value = "0",
            type = "setting",
            description = "Turn off mobile data when WiFi is active",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Auto-Sync",
            key = "auto_sync_enabled",
            value = "0",
            type = "setting",
            description = "Disable automatic app sync (Settings > Accounts)",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Vibration Time",
            key = "vibration_duration",
            value = "10",
            type = "setting",
            description = "Reduce vibration duration (milliseconds)",
            category = "Battery Optimization",
            fpsBoost = 0,
            batteryImpact = -5,
            riskLevel = "Low"
        ),

        // Network Settings (No Root)
        OptimizerString(
            name = "Use WiFi Only Mode",
            key = "preferred_network",
            value = "wifi",
            type = "setting",
            description = "Prefer WiFi over mobile data",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Mobile Hotspot",
            key = "hotspot_enabled",
            value = "0",
            type = "setting",
            description = "Disable mobile hotspot when not in use",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable WiFi Sleep Policy",
            key = "wifi_sleep_policy",
            value = "1",
            type = "setting",
            description = "Disable WiFi when screen is off",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),

        // App Management (No Root)
        OptimizerString(
            name = "Uninstall Bloatware",
            key = "uninstall_bloatware",
            value = "1",
            type = "command",
            description = "Remove pre-installed apps you don't use",
            category = "App Management",
            fpsBoost = 5,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable App Notifications",
            key = "disable_notifications",
            value = "1",
            type = "setting",
            description = "Disable unnecessary app notifications",
            category = "App Management",
            fpsBoost = 2,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Clear App Data",
            key = "clear_app_data",
            value = "1",
            type = "command",
            description = "Clear data of unused apps",
            category = "App Management",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable App Start on Boot",
            key = "disable_boot_apps",
            value = "1",
            type = "setting",
            description = "Prevent apps from starting automatically",
            category = "App Management",
            fpsBoost = 3,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Use Lite Versions of Apps",
            key = "use_lite_apps",
            value = "1",
            type = "command",
            description = "Install lite versions of heavy apps (Facebook Lite, etc)",
            category = "App Management",
            fpsBoost = 8,
            batteryImpact = -20,
            riskLevel = "Low"
        ),

        // System Tweaks (No Root)
        OptimizerString(
            name = "Enable Dark Mode",
            key = "ui_dark_mode",
            value = "1",
            type = "setting",
            description = "Enable dark mode system-wide",
            category = "System Tweaks",
            fpsBoost = 3,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Accessibility Services",
            key = "accessibility_enabled",
            value = "0",
            type = "setting",
            description = "Disable unused accessibility services",
            category = "System Tweaks",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Use Minimal Font Size",
            key = "font_scale",
            value = "0.8",
            type = "setting",
            description = "Reduce font size to 80% (Settings > Display > Font size)",
            category = "System Tweaks",
            fpsBoost = 1,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable System Animations",
            key = "animation_scale",
            value = "0",
            type = "setting",
            description = "Disable all system animations",
            category = "System Tweaks",
            fpsBoost = 8,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Resolution",
            key = "screen_resolution",
            value = "720x1520",
            type = "setting",
            description = "Lower screen resolution to save battery",
            category = "System Tweaks",
            fpsBoost = 5,
            batteryImpact = -20,
            riskLevel = "Medium"
        ),

        // Developer Options (No Root)
        OptimizerString(
            name = "Disable Show CPU Usage",
            key = "show_cpu",
            value = "0",
            type = "setting",
            description = "Hide CPU usage overlay",
            category = "Developer Options",
            fpsBoost = 1,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Show Memory Usage",
            key = "show_memory",
            value = "0",
            type = "setting",
            description = "Hide memory usage overlay",
            category = "Developer Options",
            fpsBoost = 1,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable GPU Rendering",
            key = "gpu_rendering",
            value = "1",
            type = "setting",
            description = "Enable GPU rendering (Developer Options)",
            category = "Developer Options",
            fpsBoost = 10,
            batteryImpact = 5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Strict Mode",
            key = "strict_mode",
            value = "0",
            type = "setting",
            description = "Disable strict mode for better performance",
            category = "Developer Options",
            fpsBoost = 3,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Force 4x MSAA",
            key = "force_msaa",
            value = "1",
            type = "setting",
            description = "Force 4x MSAA for smoother graphics (Developer Options)",
            category = "Developer Options",
            fpsBoost = 8,
            batteryImpact = 10,
            riskLevel = "Medium"
        ),

        // Storage & Cache (No Root)
        OptimizerString(
            name = "Clear System Cache",
            key = "clear_cache_partition",
            value = "1",
            type = "command",
            description = "Clear system cache partition",
            category = "Storage Optimization",
            fpsBoost = 3,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Delete Duplicate Files",
            key = "delete_duplicates",
            value = "1",
            type = "command",
            description = "Remove duplicate files from storage",
            category = "Storage Optimization",
            fpsBoost = 2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Move Apps to SD Card",
            key = "move_to_sd",
            value = "1",
            type = "command",
            description = "Move heavy apps to microSD card (if available)",
            category = "Storage Optimization",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),

        // Security & Privacy (No Root)
        OptimizerString(
            name = "Enable App Permissions Review",
            key = "app_permissions_review",
            value = "1",
            type = "setting",
            description = "Regularly review app permissions",
            category = "Security",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Screen Lock",
            key = "screen_lock_enabled",
            value = "1",
            type = "setting",
            description = "Enable pattern/PIN lock",
            category = "Security",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Unknown Sources",
            key = "unknown_sources",
            value = "0",
            type = "setting",
            description = "Disable installation from unknown sources",
            category = "Security",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Google Play Protect",
            key = "google_play_protect",
            value = "1",
            type = "setting",
            description = "Enable Google Play Protect for malware scanning",
            category = "Security",
            fpsBoost = 0,
            batteryImpact = 2,
            riskLevel = "Low"
        ),

        // Gaming Optimization (No Root)
        OptimizerString(
            name = "Close Background Apps Before Gaming",
            key = "close_bg_apps",
            value = "1",
            type = "command",
            description = "Close all background apps for gaming",
            category = "Gaming",
            fpsBoost = 20,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Do Not Disturb Mode",
            key = "do_not_disturb",
            value = "1",
            type = "setting",
            description = "Enable DND mode before gaming",
            category = "Gaming",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable In-App Ads",
            key = "disable_ads",
            value = "1",
            type = "setting",
            description = "Use adblocker app or game settings to disable ads",
            category = "Gaming",
            fpsBoost = 5,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Game Graphics",
            key = "game_graphics",
            value = "low",
            type = "setting",
            description = "Lower game graphics settings in-game",
            category = "Gaming",
            fpsBoost = 25,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Game Predictions",
            key = "game_predict",
            value = "0",
            type = "setting",
            description = "Disable game mode predictions",
            category = "Gaming",
            fpsBoost = 3,
            batteryImpact = -2,
            riskLevel = "Low"
        ),

        // Quick Tips (No Root)
        OptimizerString(
            name = "Restart Device Regularly",
            key = "restart_device",
            value = "1",
            type = "command",
            description = "Restart device weekly to clear memory",
            category = "Tips & Tricks",
            fpsBoost = 5,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Update All Apps",
            key = "update_apps",
            value = "1",
            type = "command",
            description = "Keep all apps updated for bug fixes",
            category = "Tips & Tricks",
            fpsBoost = 3,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Remove Old Files",
            key = "remove_old_files",
            value = "1",
            type = "command",
            description = "Delete old downloads and unnecessary files",
            category = "Tips & Tricks",
            fpsBoost = 2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Use Lightweight Launcher",
            key = "lightweight_launcher",
            value = "1",
            type = "command",
            description = "Switch to lightweight launcher like Nova or Action Launcher",
            category = "Tips & Tricks",
            fpsBoost = 8,
            batteryImpact = -10,
            riskLevel = "Low"
        )
    )

    // Methods for filtering
    val ALL_STRINGS: List<OptimizerString> get() = strings

    fun getCategories(): List<String> {
        return strings.map { it.category }.distinct().sorted()
    }

    fun search(query: String): List<OptimizerString> {
        val lowerQuery = query.lowercase()
        return strings.filter { string ->
            string.name.lowercase().contains(lowerQuery) ||
            string.description.lowercase().contains(lowerQuery) ||
            string.category.lowercase().contains(lowerQuery)
        }
    }

    fun getByCategory(category: String): List<OptimizerString> {
        return strings.filter { it.category == category }
    }
}
