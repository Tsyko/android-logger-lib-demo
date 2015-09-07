package com.nix.chronicler;

import android.content.Context;
import android.content.Intent;

/**
 * Implementation of logger interface. Processes events asynchronously.
 */
class LoggerImpl implements Logger {

    private final Context context;

    LoggerImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void log(final int type, final String comment) {
        final Intent intent = new Intent(context, LoggerService.class);
        intent.putExtra(LoggerService.EXTRA_TYPE, type);
        intent.putExtra(LoggerService.EXTRA_COMMENT, comment);
        context.startService(intent);
    }
}
