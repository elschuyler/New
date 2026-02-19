package com.vibeforge.launcher.ui

import android.app.ActivityManager
import android.content.Context
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vibeforge.launcher.R

class PluginsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plugins)

        recyclerView = findViewById(R.id.pluginsList)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmptyPluginAdapter()

        val memText = findViewById<TextView>(R.id.systemMemoryText)
        val actManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        actManager.getMemoryInfo(memInfo)
        memText.text = "Available System RAM: ${memInfo.availMem / (1024 * 1024)} MB"
    }
}

class EmptyPluginAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: android.view.ViewGroup, viewType: Int) =
        object : androidx.recyclerview.widget.RecyclerView.ViewHolder(
            android.widget.TextView(parent.context).apply {
                text = "No plugins installed yet."
                setPadding(32, 32, 32, 32)
                setTextColor(android.graphics.Color.WHITE)
            }
        ) {}
    override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {}
    override fun getItemCount() = 1
}
