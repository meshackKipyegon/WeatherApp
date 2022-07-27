package com.dvtest.weatherapp;

import android.app.Application;
import android.content.Context;

public class MainApp extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
    }
}
