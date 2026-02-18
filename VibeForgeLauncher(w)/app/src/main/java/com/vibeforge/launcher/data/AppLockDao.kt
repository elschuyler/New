package com.vibeforge.launcher.data

import androidx.room.*

@Entity(tableName = "locked_apps")
data class LockedApp(
    @PrimaryKey val packageName: String
)

@Dao
interface AppLockDao {
    @Query("SELECT * FROM locked_apps")
    suspend fun getAllLockedApps(): List<LockedApp>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun lockApp(app: LockedApp)

    @Delete
    suspend fun unlockApp(app: LockedApp)

    @Query("SELECT EXISTS(SELECT 1 FROM locked_apps WHERE packageName = :packageName)")
    suspend fun isAppLocked(packageName: String): Boolean
}

@Database(entities = [LockedApp::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appLockDao(): AppLockDao
}
