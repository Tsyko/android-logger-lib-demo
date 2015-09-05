package com.nix.chronicler;

import android.location.Location;

/**
 * Created by andriy on 05 September 2015.
 */
class Event {

    private final int type;
    private final String message;
    private final double latitude;
    private final double longitude;
    private final long time;

    public Event(final int type, final String message, final Location location, final long time) {
        this.type = type;
        this.message = message;
        this.latitude = location == null ? 0 : location.getLatitude();
        this.longitude = location == null ? 0 : location.getLongitude();
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public long getTime() {
        return time;
    }
}
