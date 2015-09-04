package com.nix.chronicler;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by andriy on 03 September 2015.
 */
class LoggerImpl implements Logger {

    private final Context context;

    LoggerImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void log(final int type, final String comment) {

        Log.d("ZZZ", "LoggerImpl.log() " + type + " " + comment);

        final Intent intent = new Intent(context, LoggerService.class);
        // TODO: add params
        context.startService(intent);
    }
}
