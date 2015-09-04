package com.nix.chronicler;

import android.content.Context;

/**
 * Created by andriy on 03 September 2015.
 */
public class LoggerFactory {

    private static Logger logger;

    private LoggerFactory() {
    }

    public static synchronized void init(final Context context) {
        if (logger != null) {
            throw new IllegalStateException("Already inited.");
        }

        logger = new LoggerImpl(context);
    }

    public static synchronized Logger getLogger() {
        if (logger == null) {
            throw new IllegalStateException("Not inited.");
        }

        return logger;
    }
}
