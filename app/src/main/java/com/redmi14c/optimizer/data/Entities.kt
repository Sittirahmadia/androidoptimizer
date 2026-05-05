package com.redmi14c.optimizer.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "custom_strings")
data class CustomStringEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val key: String,
    val value: String,
    val type: String, // global, system, secure
    val description: String,
    val category: String,
    val fpsEstimate: String,
    val batteryImpact: String,
    val riskLevel: String,
    val isUserAdded: Boolean = true,
    val timestamp: Long = System.currentTimeMillis()
) {
    val command: String
        get() = when (type) {
            "global" -> "settings put global $key $value"
            "system" -> "settings put system $key $value"
            "secure" -> "settings put secure $key $value"
            else -> "settings put global $key $value"
        }
}

@Entity(tableName = "game_profiles")
data class GameProfile(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val packageName: String,
    val gameName: String,
    val dpi: Int = 320,
    val cpuGovernor: String = "performance",
    val gpuFreq: String = "max",
    val refreshRate: Int = 120,
    val disableNotifications: Boolean = true,
    val autoClearRam: Boolean = true,
    val touchBoost: Boolean = true,
    val networkPriority: Boolean = true,
    val thermalProfile: String = "gaming",
    val isCustom: Boolean = false
)

@Entity(tableName = "backups")
data class BackupEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String,
    val settingsJson: String,
    val timestamp: Long = System.currentTimeMillis()
)

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun toStringList(list: List<String>): String {
        return gson.toJson(list)
    }
}
