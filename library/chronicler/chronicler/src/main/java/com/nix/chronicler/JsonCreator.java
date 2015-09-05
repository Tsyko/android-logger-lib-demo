package com.nix.chronicler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Converts events to json.
 */
class JsonCreator {

    private static final String TYPE = "type";
    private static final String MESSAGE = "message";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String TIME = "time";
    static final String EVENTS = "events";

    JSONObject toJson(List<Event> events) {
        final JSONObject json = new JSONObject();

        JSONArray array = new JSONArray();

        for (Event event : events) {
            array.put(toJson(event));
        }

        try {
            json.put(EVENTS, array);
            return json;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject toJson(Event event) {
        JSONObject object = new JSONObject();
        try {
            object.put(TYPE, event.getType());
            object.put(MESSAGE, event.getMessage());
            object.put(LATITUDE, event.getLatitude());
            object.put(LONGITUDE, event.getLongitude());
            object.put(TIME, event.getTime());
            return object;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    Event fromJson(JSONObject json) {
        try {
            return new Event(json.getInt(TYPE), json.getString(MESSAGE), json.getDouble(LATITUDE),
                    json.getDouble(LONGITUDE), json.getLong(TIME));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
