package com.redmi14c.optimizer.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomStringDao {
    @Query("SELECT * FROM custom_strings ORDER BY category, name")
    fun getAll(): Flow<List<CustomStringEntry>>

    @Query("SELECT * FROM custom_strings WHERE category = :category ORDER BY name")
    fun getByCategory(category: String): Flow<List<CustomStringEntry>>

    @Query("SELECT * FROM custom_strings WHERE name LIKE '%' || :query || '%' OR key LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'")
    fun search(query: String): Flow<List<CustomStringEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: CustomStringEntry)

    @Delete
    suspend fun delete(entry: CustomStringEntry)

    @Query("DELETE FROM custom_strings WHERE isUserAdded = 1")
    suspend fun deleteAllUserAdded()

    @Query("SELECT COUNT(*) FROM custom_strings")
    suspend fun count(): Int
}

@Dao
interface GameProfileDao {
    @Query("SELECT * FROM game_profiles ORDER BY gameName")
    fun getAll(): Flow<List<GameProfile>>

    @Query("SELECT * FROM game_profiles WHERE packageName = :packageName LIMIT 1")
    suspend fun getByPackage(packageName: String): GameProfile?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(profile: GameProfile)

    @Delete
    suspend fun delete(profile: GameProfile)
}

@Dao
interface BackupDao {
    @Query("SELECT * FROM backups ORDER BY timestamp DESC")
    fun getAll(): Flow<List<BackupEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(backup: BackupEntry)

    @Delete
    suspend fun delete(backup: BackupEntry)

    @Query("DELETE FROM backups")
    suspend fun deleteAll()
}
