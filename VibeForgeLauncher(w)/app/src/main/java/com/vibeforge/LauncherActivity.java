package com.elschuyler.vibeforge;

import android.app.WallpaperManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set wallpaper as background
        getWindow().setBackgroundDrawable(WallpaperManager.getInstance(this).getDrawable());
        // Load launcher grid
        setContentView(R.layout.activity_launcher);
    }
}
