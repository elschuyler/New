package com.vibeforge.launcher.core

import android.content.Context

class AppLockManager(context: Context) {
    private val lockedApps = mutableSetOf<String>()

    fun isLocked(packageName: String): Boolean {
        return lockedApps.contains(packageName)
    }

    fun setLocked(packageName: String, locked: Boolean) {
        if (locked) lockedApps.add(packageName)
        else lockedApps.remove(packageName)
    }
}
