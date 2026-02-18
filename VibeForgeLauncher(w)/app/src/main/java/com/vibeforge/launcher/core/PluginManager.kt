package com.vibeforge.launcher.core

import android.content.*
import android.os.IBinder
import android.util.Log
import com.vibeforge.launcher.IVibeforgePlugin

class PluginManager(private val context: Context) {
    private val activePlugins = mutableMapOf<String, IVibeforgePlugin>()

    fun discoverAndBindPlugins() {
        val intent = Intent("com.vibeforge.launcher.PLUGIN")
        val resolveInfos = context.packageManager.queryIntentServices(intent, 0)

        for (info in resolveInfos) {
            val serviceIntent = Intent().apply {
                component = ComponentName(info.serviceInfo.packageName, info.serviceInfo.name)
            }
            context.bindService(serviceIntent, object : ServiceConnection {
                override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                    val plugin = IVibeforgePlugin.Stub.asInterface(service)
                    activePlugins[name?.packageName ?: "unknown"] = plugin
                    Log.d("PluginManager", "Connected to: ${plugin.pluginName}")
                }

                override fun onServiceDisconnected(name: ComponentName?) {
                    activePlugins.remove(name?.packageName)
                }
            }, Context.BIND_AUTO_CREATE)
        }
    }

    fun getActivePlugins(): List<IVibeforgePlugin> = activePlugins.values.toList()
}
