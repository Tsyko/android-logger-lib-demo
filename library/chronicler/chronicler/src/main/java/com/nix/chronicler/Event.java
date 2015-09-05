package com.nix.chronicler;

import android.location.Location;

/**
 * Immutable model of event.
 */
class Event {

    private final int type;
    private final String message;
    private final double latitude;
    private final double longitude;
    private final long time;

    public Event(final int type, final String message, final Location location, final long time) {
        this(type, message, location == null ? 0 : location.getLatitude(),
                location == null ? 0 : location.getLongitude(), time);
    }

    public Event(final int type, final String message, final double latitude,
            final double longitude, final long time) {
        this.type = type;
        this.message = message;
        this.latitude = latitude;
        this.longitude = longitude;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event event = (Event) o;

        if (type != event.type) {
            return false;
        }
        if (Double.compare(event.latitude, latitude) != 0) {
            return false;
        }
        if (Double.compare(event.longitude, longitude) != 0) {
            return false;
        }
        if (time != event.time) {
            return false;
        }
        return !(message != null ? !message.equals(event.message) : event.message != null);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = type;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        temp = Double.doubleToLongBits(latitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (time ^ (time >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Event{" + "type=" + type + ", message='" + message + "', latitude=" + latitude
                + ", longitude=" + longitude + ", time=" + time + '}';
    }
}
