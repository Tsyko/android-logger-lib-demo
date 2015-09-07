package com.nix.chroniclerdemo;

import com.nix.chronicler.LoggerFactory;

import android.app.Application;

/**
 * Application.
 */
public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerFactory.init(BuildConfig.SERVER_URL, this);
    }
}
