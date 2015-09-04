package com.nix.chronicler;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by andriy on 04 September 2015.
 */
public class LoggerService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ZZZ", "LoggerService.onCreate() ");
        // TODO: create location manager, storage and network manager
        // TODO: create looper
    }

    @Override
    public int onStartCommand(final Intent intent, final int flags, final int startId) {
        Log.d("ZZZ", "LoggerService.onStartCommand() ");
        // TODO: add data to storage
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(final Intent intent) {
        return null; // binding not supported
    }
}
