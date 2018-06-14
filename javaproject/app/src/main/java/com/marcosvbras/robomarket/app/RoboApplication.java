package com.marcosvbras.robomarket.app;

import android.app.Application;

public class RoboApplication extends Application {

    private static RoboApplication instance;

    public static RoboApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
