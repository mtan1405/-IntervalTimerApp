package com.example.exercise;

import android.content.Context;

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }
    private static Application application ;
    public static Context getInstance() {
        return application;
    }
}
