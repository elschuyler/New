package com.vibeforge.launcher.core

import android.content.Context
import com.vibeforge.launcher.data.AppDatabase
import com.vibeforge.launcher.data.LockedApp
import androidx.room.Room

class AppLockManager(context: Context) {
    private val db = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java, "vibeforge-db"
    ).build()

    private val dao = db.appLockDao()

    suspend fun isLocked(packageName: String): Boolean {
        return dao.isAppLocked(packageName)
    }

    suspend fun setLocked(packageName: String, locked: Boolean) {
        if (locked) {
            dao.lockApp(LockedApp(packageName))
        } else {
            dao.unlockApp(LockedApp(packageName))
        }
    }
}
