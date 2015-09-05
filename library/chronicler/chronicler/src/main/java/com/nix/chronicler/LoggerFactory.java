package com.nix.chronicler;

import android.content.Context;

/**
 * Factory class for configuring and obtaining logger.
 */
public final class LoggerFactory {

    private static Logger logger;

    private static String serverUrl;

    private LoggerFactory() {
        // this class is not intended for instantiation
    }

    /**
     * Initiates the factory.
     * @param serverUrl url to send logs to
     * @param context android context
     */
    public static synchronized void init(final String serverUrl, final Context context) {
        if (logger != null) {
            throw new IllegalStateException("Already inited.");
        }

        LoggerFactory.serverUrl = serverUrl;
        logger = new LoggerImpl(context);
    }

    public static synchronized Logger getLogger() {
        if (logger == null) {
            throw new IllegalStateException("Not inited.");
        }

        return logger;
    }

    /**
     * Returns logger. You must call {@link #init(String, Context)} prior to calling this method.
     * @return logger
     */
    static String getServerUrl() {
        return serverUrl;
    }
}
