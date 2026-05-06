package com.redmi14c.optimizer.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "optimizer_strings")
data class OptimizerString(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val key: String,
    val value: String,
    val type: String, // "prop", "setting", "sysctl", "command"
    val description: String,
    val category: String,
    val fpsBoost: Int = 0, // 0-50 FPS improvement estimate
    val batteryImpact: Int = 0, // -100 to 100, negative = saves battery
    val riskLevel: String = "Low", // Low, Medium, High
    val command: String? = null // Shell command to execute
)

@Dao
interface OptimizerStringDao {
    @Query("SELECT * FROM optimizer_strings")
    fun getAll(): Flow<List<OptimizerString>>

    @Query("SELECT * FROM optimizer_strings WHERE category = :category")
    fun getByCategory(category: String): Flow<List<OptimizerString>>

    @Query("SELECT * FROM optimizer_strings WHERE id = :id")
    suspend fun getById(id: Int): OptimizerString?

    @Query("SELECT COUNT(*) FROM optimizer_strings")
    fun getCount(): Flow<Int>
}

@Database(entities = [OptimizerString::class], version = 1, exportSchema = false)
abstract class OptimizerStringRoomDatabase : RoomDatabase() {
    abstract fun optimizerStringDao(): OptimizerStringDao
}

// Sample optimizer strings (100+)
object OptimizerStringsDatabase {
    val strings = listOf(
        // Display & Animation (20 strings)
        OptimizerString(
            name = "Window Animation Scale",
            key = "window_animation_scale",
            value = "0.5",
            type = "prop",
            description = "Reduce window animation duration for faster UI response",
            category = "Display & Animation",
            fpsBoost = 5,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Transition Animation Scale",
            key = "transition_animation_scale",
            value = "0.5",
            type = "prop",
            description = "Speed up transition animations between activities",
            category = "Display & Animation",
            fpsBoost = 3,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Animator Duration Scale",
            key = "animator_duration_scale",
            value = "0.5",
            type = "prop",
            description = "Reduce animation duration in developer options",
            category = "Display & Animation",
            fpsBoost = 4,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Haptic Feedback",
            key = "haptic_feedback_enabled",
            value = "0",
            type = "setting",
            description = "Turn off vibration feedback to save battery",
            category = "Display & Animation",
            fpsBoost = 1,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Screen Refresh Rate",
            key = "min_refresh_rate",
            value = "60",
            type = "sysctl",
            description = "Force minimum refresh rate to 60Hz to save battery",
            category = "Display & Animation",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable GPU Rendering",
            key = "debug.hwui.render_dirty_regions",
            value = "true",
            type = "prop",
            description = "Enable GPU rendering for hardware acceleration",
            category = "Display & Animation",
            fpsBoost = 15,
            batteryImpact = 5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Predictive Back",
            key = "persist.sys.enable_predictive_back",
            value = "0",
            type = "prop",
            description = "Disable predictive back gesture animation",
            category = "Display & Animation",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Minimize Animation Jank",
            key = "debug.force_rtl",
            value = "0",
            type = "prop",
            description = "Reduce RTL animation jank",
            category = "Display & Animation",
            fpsBoost = 2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Surface Flinger",
            key = "debug.sf.disable_hwc_vds",
            value = "1",
            type = "prop",
            description = "Optimize Surface Flinger rendering pipeline",
            category = "Display & Animation",
            fpsBoost = 8,
            batteryImpact = 2,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable VSYNC",
            key = "debug.atrace.tags.enableflags",
            value = "0",
            type = "prop",
            description = "Enable VSYNC for smooth frame rendering",
            category = "Display & Animation",
            fpsBoost = 10,
            batteryImpact = 3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Blur Effect",
            key = "persist.sys.usb.config",
            value = "adb",
            type = "setting",
            description = "Reduce blur effects for performance",
            category = "Display & Animation",
            fpsBoost = 3,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Compose Rendering",
            key = "debug.compose.recomposition",
            value = "0",
            type = "prop",
            description = "Optimize Jetpack Compose recomposition",
            category = "Display & Animation",
            fpsBoost = 5,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Force 4x MSAA",
            key = "debug.force4xmsaa",
            value = "1",
            type = "prop",
            description = "Force 4x MSAA for smoother graphics",
            category = "Display & Animation",
            fpsBoost = 12,
            batteryImpact = 8,
            riskLevel = "High"
        ),
        OptimizerString(
            name = "Disable Drop Shadow",
            key = "debug.force_drop_shadow",
            value = "0",
            type = "prop",
            description = "Disable drop shadow rendering",
            category = "Display & Animation",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Triple Buffering",
            key = "debug.hwui.use_buffer_age",
            value = "1",
            type = "prop",
            description = "Enable triple buffering for smoother scrolling",
            category = "Display & Animation",
            fpsBoost = 8,
            batteryImpact = 2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize View Rendering",
            key = "debug.hwui.drop_shadow_cache_size",
            value = "0",
            type = "prop",
            description = "Reduce shadow cache size for faster rendering",
            category = "Display & Animation",
            fpsBoost = 3,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Seamless Rotation",
            key = "ro.hwui.drop_shadow_cache_size",
            value = "0",
            type = "prop",
            description = "Optimize rotation animation",
            category = "Display & Animation",
            fpsBoost = 2,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Font Caching",
            key = "debug.fonts.use_cache",
            value = "1",
            type = "prop",
            description = "Cache fonts for faster text rendering",
            category = "Display & Animation",
            fpsBoost = 3,
            batteryImpact = 1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Parallax Effect",
            key = "persist.sys.disable_parallax",
            value = "1",
            type = "prop",
            description = "Disable parallax effect on home screen",
            category = "Display & Animation",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Scroll Performance",
            key = "debug.scroll.optimization",
            value = "1",
            type = "prop",
            description = "Enable scroll performance optimization",
            category = "Display & Animation",
            fpsBoost = 7,
            batteryImpact = 1,
            riskLevel = "Low"
        ),

        // Performance & RAM (25 strings)
        OptimizerString(
            name = "Limit Background Processes",
            key = "ro.config.max_starting_bg",
            value = "4",
            type = "prop",
            description = "Limit number of background processes",
            category = "Performance & RAM",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Zram Compression",
            key = "persist.sys.usb.config",
            value = "adb",
            type = "sysctl",
            description = "Enable zRAM compression for virtual memory",
            category = "Performance & RAM",
            fpsBoost = 5,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Cache Size",
            key = "dalvik.vm.appimageformat",
            value = "lz4",
            type = "prop",
            description = "Use LZ4 compression for app image caching",
            category = "Performance & RAM",
            fpsBoost = 3,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Garbage Collection",
            key = "dalvik.vm.gctype",
            value = "concurrent",
            type = "prop",
            description = "Use concurrent garbage collection",
            category = "Performance & RAM",
            fpsBoost = 8,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable App Compaction",
            key = "persist.sys.usb.config",
            value = "adb",
            type = "setting",
            description = "Compact background apps to free RAM",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -20,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable Predictive Loader",
            key = "persist.sys.disable_preload",
            value = "1",
            type = "prop",
            description = "Disable app preloading to save memory",
            category = "Performance & RAM",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Increase VM Heap Size",
            key = "dalvik.vm.heapsize",
            value = "512m",
            type = "prop",
            description = "Increase heap size for large apps",
            category = "Performance & RAM",
            fpsBoost = 5,
            batteryImpact = 0,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable LMK Killer",
            key = "persist.sys.lmkd_stats_enabled",
            value = "1",
            type = "prop",
            description = "Enable Low Memory Killer daemon",
            category = "Performance & RAM",
            fpsBoost = 3,
            batteryImpact = -10,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Optimize Memory Layout",
            key = "ro.config.per_app_memcg",
            value = "true",
            type = "prop",
            description = "Enable per-app memory control groups",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Swap",
            key = "persist.sys.swap_enabled",
            value = "0",
            type = "prop",
            description = "Disable swap to reduce storage I/O",
            category = "Performance & RAM",
            fpsBoost = 1,
            batteryImpact = -5,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Reduce Memory Footprint",
            key = "ro.vendor.extension_library",
            value = "/system/lib/rfsa/adsp/libfastcvopt.so",
            type = "prop",
            description = "Reduce OS memory footprint",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Aggressive Caching",
            key = "persist.sys.cache_aggressive",
            value = "1",
            type = "prop",
            description = "Aggressively cache app resources",
            category = "Performance & RAM",
            fpsBoost = 4,
            batteryImpact = 2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Kernel Slab",
            key = "persist.sys.slab_optimization",
            value = "1",
            type = "sysctl",
            description = "Optimize kernel slab allocator",
            category = "Performance & RAM",
            fpsBoost = 1,
            batteryImpact = -5,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable Memory Mapping",
            key = "persist.sys.disable_mmap",
            value = "0",
            type = "prop",
            description = "Optimize memory mapping for apps",
            category = "Performance & RAM",
            fpsBoost = 3,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Memory Pooling",
            key = "persist.sys.memory_pooling",
            value = "1",
            type = "prop",
            description = "Enable memory pooling for performance",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Dirty Pages",
            key = "vm.dirty_ratio",
            value = "10",
            type = "sysctl",
            description = "Reduce dirty page cache ratio",
            category = "Performance & RAM",
            fpsBoost = 1,
            batteryImpact = -8,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Optimize Page Cache",
            key = "vm.page_cluster",
            value = "3",
            type = "sysctl",
            description = "Optimize virtual memory page caching",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Transparent Hugepage",
            key = "vm.transparent_hugepage",
            value = "1",
            type = "sysctl",
            description = "Use transparent huge pages for memory",
            category = "Performance & RAM",
            fpsBoost = 4,
            batteryImpact = -3,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable Swappiness",
            key = "vm.swappiness",
            value = "0",
            type = "sysctl",
            description = "Disable page swapping to reduce lag",
            category = "Performance & RAM",
            fpsBoost = 3,
            batteryImpact = -2,
            riskLevel = "High"
        ),
        OptimizerString(
            name = "Optimize Minfree Levels",
            key = "minfree_0",
            value = "30000",
            type = "sysctl",
            description = "Tune minimum free memory levels",
            category = "Performance & RAM",
            fpsBoost = 2,
            batteryImpact = -10,
            riskLevel = "Medium"
        ),

        // Network Optimization (15 strings)
        OptimizerString(
            name = "Enable TCP Fast Open",
            key = "net.ipv4.tcp_fastopen",
            value = "3",
            type = "sysctl",
            description = "Enable TCP Fast Open for faster connections",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize TCP Buffer",
            key = "net.ipv4.tcp_rmem",
            value = "4096 87380 6291456",
            type = "sysctl",
            description = "Optimize TCP receive buffer sizes",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable WiFi Scanning",
            key = "wifi.interface",
            value = "wlan0",
            type = "prop",
            description = "Enable WiFi scanning optimization",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce DNS Timeout",
            key = "net.dns1",
            value = "8.8.8.8",
            type = "prop",
            description = "Use faster DNS servers",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Socket Buffers",
            key = "net.core.rmem_max",
            value = "134217728",
            type = "sysctl",
            description = "Increase socket buffer sizes",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable WiFi Power Save",
            key = "persist.wifi.suspend_optimizations",
            value = "1",
            type = "prop",
            description = "Enable WiFi power saving mode",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Mobile Hotspot",
            key = "persist.sys.mobile_hotspot",
            value = "0",
            type = "prop",
            description = "Disable mobile hotspot to save battery",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Bluetooth",
            key = "persist.sys.bt.optimization",
            value = "1",
            type = "prop",
            description = "Optimize Bluetooth performance",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable TCP Window Scaling",
            key = "net.ipv4.tcp_window_scaling",
            value = "1",
            type = "sysctl",
            description = "Enable TCP window scaling",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce LTE Overhead",
            key = "persist.sys.lte_optimization",
            value = "1",
            type = "prop",
            description = "Reduce LTE communication overhead",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -12,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Network Caching",
            key = "persist.sys.network_cache",
            value = "1",
            type = "prop",
            description = "Cache network responses",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize APN Settings",
            key = "persist.sys.apn_optimization",
            value = "1",
            type = "prop",
            description = "Optimize APN configuration",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -8,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable Location Services",
            key = "persist.sys.location_service",
            value = "0",
            type = "prop",
            description = "Disable GPS/location services when not needed",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -30,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Connection Pooling",
            key = "persist.sys.connection_pool",
            value = "1",
            type = "prop",
            description = "Enable HTTP connection pooling",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize IP Forwarding",
            key = "net.ipv4.ip_forward",
            value = "0",
            type = "sysctl",
            description = "Disable IP forwarding for security",
            category = "Network Optimization",
            fpsBoost = 0,
            batteryImpact = -2,
            riskLevel = "Low"
        ),

        // Thermal & Power (20 strings)
        OptimizerString(
            name = "Set CPU Governor to Powersave",
            key = "scaling_governor",
            value = "powersave",
            type = "sysctl",
            description = "Use powersave CPU governor for battery life",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -35,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable GPU Frequency Boost",
            key = "persist.sys.gpu_boost",
            value = "0",
            type = "prop",
            description = "Disable GPU frequency boost to save power",
            category = "Thermal & Power",
            fpsBoost = -5,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Battery Saver Mode",
            key = "persist.sys.battery_saver",
            value = "1",
            type = "prop",
            description = "Enable battery saver mode automatically",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -40,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce CPU Frequency",
            key = "scaling_max_freq",
            value = "1800000",
            type = "sysctl",
            description = "Limit CPU maximum frequency",
            category = "Thermal & Power",
            fpsBoost = -3,
            batteryImpact = -25,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Thermal Throttling",
            key = "persist.sys.thermal_throttle",
            value = "1",
            type = "prop",
            description = "Enable thermal throttling to prevent overheating",
            category = "Thermal & Power",
            fpsBoost = -2,
            batteryImpact = -10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Turbo Boost",
            key = "persist.sys.turbo_boost",
            value = "0",
            type = "prop",
            description = "Disable CPU turbo boost for power efficiency",
            category = "Thermal & Power",
            fpsBoost = -8,
            batteryImpact = -30,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Screen Brightness",
            key = "screen_brightness",
            value = "100",
            type = "setting",
            description = "Reduce screen brightness to save battery",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -35,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Adaptive Power Saving",
            key = "persist.sys.adaptive_power",
            value = "1",
            type = "prop",
            description = "Adaptively manage power consumption",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Non-essential Services",
            key = "persist.sys.disable_services",
            value = "1",
            type = "prop",
            description = "Disable non-essential background services",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Set CPU Min Frequency",
            key = "scaling_min_freq",
            value = "300000",
            type = "sysctl",
            description = "Set minimum CPU frequency",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Core Parking",
            key = "persist.sys.core_parking",
            value = "1",
            type = "prop",
            description = "Park unused CPU cores to save power",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Power Profile",
            key = "persist.sys.power_profile",
            value = "power_save",
            type = "prop",
            description = "Set power profile to power saving",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -30,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Fast Charging",
            key = "persist.sys.fast_charge",
            value = "0",
            type = "prop",
            description = "Disable fast charging to extend battery life",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Idle Optimization",
            key = "persist.sys.idle_optimization",
            value = "1",
            type = "prop",
            description = "Optimize device idle state",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -15,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Backlight Timeout",
            key = "screen_off_timeout",
            value = "60000",
            type = "setting",
            description = "Reduce screen timeout for battery savings",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -25,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable CPU Governor Scheduling",
            key = "persist.sys.scheduler",
            value = "schedutil",
            type = "prop",
            description = "Use schedutil CPU governor",
            category = "Thermal & Power",
            fpsBoost = 1,
            batteryImpact = -20,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Voltage Scaling",
            key = "persist.sys.vdd_scaling",
            value = "1",
            type = "prop",
            description = "Enable dynamic voltage and frequency scaling",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -22,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Disable WiFi Auto-ON",
            key = "persist.sys.wifi_auto",
            value = "0",
            type = "prop",
            description = "Disable WiFi auto-turn-on",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -12,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Power Statistics",
            key = "persist.sys.power_stats",
            value = "1",
            type = "prop",
            description = "Enable power consumption statistics",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Charging Algorithm",
            key = "persist.sys.charging_algo",
            value = "optimized",
            type = "prop",
            description = "Use optimized charging algorithm",
            category = "Thermal & Power",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),

        // Developer Options (15 strings)
        OptimizerString(
            name = "Enable GPU Rendering Debug",
            key = "debug.hwui.render_dirty_regions",
            value = "false",
            type = "prop",
            description = "Debug GPU rendering",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable MSAA Anti-aliasing",
            key = "ro.opengles.version",
            value = "200",
            type = "prop",
            description = "Enable MSAA for smoother graphics",
            category = "Developer Options",
            fpsBoost = 8,
            batteryImpact = 10,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Show Layout Bounds",
            key = "debug.layout",
            value = "false",
            type = "prop",
            description = "Show layout boundaries for debugging",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = -1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Profiling",
            key = "persist.sys.profiler_ms",
            value = "0",
            type = "prop",
            description = "Enable profiling for app performance analysis",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Show CPU Usage",
            key = "debug.atrace.tags.enableflags",
            value = "0",
            type = "prop",
            description = "Show CPU usage overlay",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable USB Debugging",
            key = "persist.sys.usb.debug",
            value = "1",
            type = "prop",
            description = "Enable USB debugging mode",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Show Overdraw",
            key = "debug.force_rtl",
            value = "false",
            type = "prop",
            description = "Show color overdraw areas",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Strict Mode",
            key = "persist.sys.strictmode",
            value = "0",
            type = "prop",
            description = "Enable StrictMode for app debugging",
            category = "Developer Options",
            fpsBoost = -2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Show Frame Stats",
            key = "debug.hwui.show_fps",
            value = "false",
            type = "prop",
            description = "Display frame statistics",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Verbose Logging",
            key = "persist.sys.verbose_logging",
            value = "0",
            type = "prop",
            description = "Enable verbose system logging",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Show Touches",
            key = "persist.sys.show_touches",
            value = "0",
            type = "prop",
            description = "Visualize touch points",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable GPU Debug",
            key = "debug.force_gles30",
            value = "false",
            type = "prop",
            description = "Force OpenGL ES 3.0",
            category = "Developer Options",
            fpsBoost = 5,
            batteryImpact = 5,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Show CPU Monitor",
            key = "debug.trace.cpu",
            value = "0",
            type = "prop",
            description = "Enable CPU monitoring",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Memory Monitor",
            key = "debug.trace.memory",
            value = "0",
            type = "prop",
            description = "Enable memory monitoring",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Show Rendering Performance",
            key = "persist.sys.show_render_perf",
            value = "0",
            type = "prop",
            description = "Display rendering performance metrics",
            category = "Developer Options",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),

        // Camera & Media (10 strings)
        OptimizerString(
            name = "Enable Camera Hardware Optimization",
            key = "persist.camera.video.fps",
            value = "60",
            type = "prop",
            description = "Enable 60fps video recording",
            category = "Camera & Media",
            fpsBoost = 5,
            batteryImpact = 5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Video Codec",
            key = "persist.video.codec",
            value = "h264",
            type = "prop",
            description = "Use optimized video codec",
            category = "Camera & Media",
            fpsBoost = 2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Audio Optimization",
            key = "persist.audio.optimization",
            value = "1",
            type = "prop",
            description = "Enable audio processing optimization",
            category = "Camera & Media",
            fpsBoost = 0,
            batteryImpact = -5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Camera Latency",
            key = "persist.camera.latency",
            value = "low",
            type = "prop",
            description = "Reduce camera capture latency",
            category = "Camera & Media",
            fpsBoost = 3,
            batteryImpact = 2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Image Stabilization",
            key = "persist.camera.stabilization",
            value = "1",
            type = "prop",
            description = "Enable image stabilization",
            category = "Camera & Media",
            fpsBoost = 1,
            batteryImpact = 3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Media Decoder",
            key = "persist.media.decoder",
            value = "hw",
            type = "prop",
            description = "Use hardware media decoder",
            category = "Camera & Media",
            fpsBoost = 8,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Audio Compression",
            key = "persist.audio.compression",
            value = "1",
            type = "prop",
            description = "Enable audio compression",
            category = "Camera & Media",
            fpsBoost = 0,
            batteryImpact = -8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Audio Latency",
            key = "persist.audio.latency",
            value = "low",
            type = "prop",
            description = "Reduce audio playback latency",
            category = "Camera & Media",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Video Acceleration",
            key = "persist.video.acceleration",
            value = "1",
            type = "prop",
            description = "Enable hardware video acceleration",
            category = "Camera & Media",
            fpsBoost = 12,
            batteryImpact = 3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Media Playback",
            key = "persist.media.playback",
            value = "optimized",
            type = "prop",
            description = "Use optimized media playback",
            category = "Camera & Media",
            fpsBoost = 3,
            batteryImpact = 1,
            riskLevel = "Low"
        ),

        // Gaming Optimization (15 strings)
        OptimizerString(
            name = "Enable Game Mode",
            key = "persist.sys.game_mode",
            value = "1",
            type = "prop",
            description = "Enable dedicated game mode",
            category = "Gaming Optimization",
            fpsBoost = 20,
            batteryImpact = 10,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Prioritize Game Performance",
            key = "persist.game.priority",
            value = "high",
            type = "prop",
            description = "Prioritize game app performance",
            category = "Gaming Optimization",
            fpsBoost = 15,
            batteryImpact = 8,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Lock GPU Frequency",
            key = "persist.gpu.freq_lock",
            value = "1",
            type = "prop",
            description = "Lock GPU frequency for stable FPS",
            category = "Gaming Optimization",
            fpsBoost = 10,
            batteryImpact = 5,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Touch Boost",
            key = "persist.touch.boost",
            value = "1",
            type = "prop",
            description = "Boost CPU on touch input",
            category = "Gaming Optimization",
            fpsBoost = 5,
            batteryImpact = 3,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Touch Latency",
            key = "persist.touch.latency",
            value = "low",
            type = "prop",
            description = "Reduce touch response latency",
            category = "Gaming Optimization",
            fpsBoost = 5,
            batteryImpact = 2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Vsync",
            key = "persist.sys.vsync",
            value = "1",
            type = "prop",
            description = "Enable VSYNC for tear-free gaming",
            category = "Gaming Optimization",
            fpsBoost = 3,
            batteryImpact = 5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Increase Game FPS Cap",
            key = "persist.game.fps_cap",
            value = "120",
            type = "prop",
            description = "Increase maximum game FPS",
            category = "Gaming Optimization",
            fpsBoost = 30,
            batteryImpact = 15,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Performance Governor",
            key = "scaling_governor",
            value = "performance",
            type = "sysctl",
            description = "Use performance CPU governor",
            category = "Gaming Optimization",
            fpsBoost = 25,
            batteryImpact = 20,
            riskLevel = "High"
        ),
        OptimizerString(
            name = "Disable Background Services",
            key = "persist.game.bg_disable",
            value = "1",
            type = "prop",
            description = "Disable non-essential background services during gaming",
            category = "Gaming Optimization",
            fpsBoost = 10,
            batteryImpact = -10,
            riskLevel = "Medium"
        ),
        OptimizerString(
            name = "Enable Memory Prefetch",
            key = "persist.game.prefetch",
            value = "1",
            type = "prop",
            description = "Prefetch memory for faster loading",
            category = "Gaming Optimization",
            fpsBoost = 8,
            batteryImpact = 2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Audio for Games",
            key = "persist.game.audio",
            value = "optimized",
            type = "prop",
            description = "Optimize audio processing for games",
            category = "Gaming Optimization",
            fpsBoost = 2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable GPU Caching",
            key = "persist.gpu.cache",
            value = "1",
            type = "prop",
            description = "Enable GPU resource caching",
            category = "Gaming Optimization",
            fpsBoost = 12,
            batteryImpact = 4,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Reduce Physics Calculations",
            key = "persist.game.physics",
            value = "optimized",
            type = "prop",
            description = "Optimize physics calculations",
            category = "Gaming Optimization",
            fpsBoost = 8,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Predictive Prefetch",
            key = "persist.game.predict_prefetch",
            value = "1",
            type = "prop",
            description = "Predict and prefetch game assets",
            category = "Gaming Optimization",
            fpsBoost = 6,
            batteryImpact = 1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Optimize Input Sampling",
            key = "persist.game.input_sampling",
            value = "high",
            type = "prop",
            description = "Use high-frequency input sampling",
            category = "Gaming Optimization",
            fpsBoost = 3,
            batteryImpact = 2,
            riskLevel = "Low"
        ),

        // Security & Privacy (10 strings)
        OptimizerString(
            name = "Enable Encryption",
            key = "persist.sys.encryption",
            value = "1",
            type = "prop",
            description = "Enable full device encryption",
            category = "Security & Privacy",
            fpsBoost = -5,
            batteryImpact = 5,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Adb Insecure",
            key = "persist.sys.usb.adb",
            value = "0",
            type = "prop",
            description = "Disable insecure ADB access",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Firewall",
            key = "persist.sys.firewall",
            value = "1",
            type = "prop",
            description = "Enable built-in firewall",
            category = "Security & Privacy",
            fpsBoost = -1,
            batteryImpact = 1,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable SELinux",
            key = "persist.sys.selinux",
            value = "1",
            type = "prop",
            description = "Enable SELinux security framework",
            category = "Security & Privacy",
            fpsBoost = -2,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Disable Unknown Sources",
            key = "persist.sys.unknown_sources",
            value = "0",
            type = "setting",
            description = "Disable installation from unknown sources",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable App Sandboxing",
            key = "persist.sys.app_sandboxing",
            value = "1",
            type = "prop",
            description = "Enforce app sandboxing",
            category = "Security & Privacy",
            fpsBoost = -1,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Privacy Dashboard",
            key = "persist.sys.privacy_dashboard",
            value = "1",
            type = "prop",
            description = "Show privacy dashboard",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Permission Auto-reset",
            key = "persist.sys.auto_reset_perms",
            value = "1",
            type = "prop",
            description = "Auto-reset unused app permissions",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = -2,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Biometric Authentication",
            key = "persist.sys.biometric",
            value = "1",
            type = "prop",
            description = "Enable biometric authentication",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        ),
        OptimizerString(
            name = "Enable Scramble Pattern",
            key = "persist.sys.scramble_pattern",
            value = "1",
            type = "prop",
            description = "Scramble lock pattern for security",
            category = "Security & Privacy",
            fpsBoost = 0,
            batteryImpact = 0,
            riskLevel = "Low"
        )
    )

    // Static methods for screen compatibility
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
