package com.nix.chroniclerdemo;

import com.nix.chronicler.LoggerFactory;

import android.app.Application;
import android.support.annotation.IntDef;

/**
 * Created by andriy on 04 September 2015.
 */
public class DemoApp extends Application {

    private RandomEventGenerator generator;

    @Override
    public void onCreate() {
        super.onCreate();
        LoggerFactory.init(BuildConfig.SERVER_URL, this);
        generator = new RandomEventGenerator();
    }
}
