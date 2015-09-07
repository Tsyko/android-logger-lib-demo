package com.nix.chronicler;

/**
 * Interface for logger.
 */
public interface Logger {

    /**
     * Logs the event.
     * @param type type of the event
     * @param comment comment to the event
     */
    void log(int type, String comment);
}
