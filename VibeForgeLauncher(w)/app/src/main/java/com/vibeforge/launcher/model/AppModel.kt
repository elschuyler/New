package com.vibeforge.launcher.model

import android.graphics.drawable.Drawable

data class AppModel(
    val label: String,
    val packageName: String,
    val icon: Drawable,
    var isLocked: Boolean = false
)
