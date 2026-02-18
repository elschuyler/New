package com.vibeforge.launcher;

interface IVibeforgePlugin {
    String getPluginName();
    String getPluginVersion();
    void onPluginAction(String action, in Bundle extras);
    long getMemoryUsage();
}
