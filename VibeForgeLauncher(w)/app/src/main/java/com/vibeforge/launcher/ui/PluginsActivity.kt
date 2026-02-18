package com.vibeforge.launcher.ui

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vibeforge.launcher.IVibeforgePlugin
import com.vibeforge.launcher.R
import com.vibeforge.launcher.core.PluginManager

class PluginsActivity : AppCompatActivity() {

    private lateinit var pluginManager: PluginManager
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugins)

        pluginManager = PluginManager(this)
        pluginManager.discoverAndBindPlugins()

        recyclerView = findViewById(R.id.pluginsList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerView.postDelayed({
            recyclerView.adapter = PluginAdapter(pluginManager.getActivePlugins())
        }, 1000)

        val memText = findViewById<TextView>(R.id.systemMemoryText)
        val actManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        memText.text = "Available System RAM: ${memInfo.availMem / (1024 * 1024)} MB"
    }
}

class PluginViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.pluginName)
    val stats: TextView = view.findViewById(R.id.pluginStats)
}

class PluginAdapter(private val plugins: List<IVibeforgePlugin>) : RecyclerView.Adapter<PluginViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PluginViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_plugin, parent, false)
        return PluginViewHolder(view)
    }

    override fun onBindViewHolder(holder: PluginViewHolder, position: Int) {
        val plugin = plugins[position]
        try {
            holder.name.text = plugin.pluginName
            holder.stats.text = "Version: ${plugin.pluginVersion} | RAM: ${plugin.memoryUsage / 1024} KB"
        } catch (e: Exception) {
            holder.name.text = "Link Broken"
        }
    }

    override fun getItemCount() = plugins.size
}
