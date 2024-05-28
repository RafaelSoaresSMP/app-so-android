package com.example.myapplication;

import android.graphics.drawable.Drawable;

public class AppInfo {
    String name;
    String packageName;
    String version;
    Drawable icon;

    AppInfo(String name, Drawable icon, String packageName, String version) {
        this.name = name;
        this.icon = icon;
        this.version = version;
        this.packageName = packageName;
    }
}