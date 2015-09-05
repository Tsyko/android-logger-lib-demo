package com.nix.chronicler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by andriy on 05 September 2015.
 */
class JsonCreator {

    String toJson(List<Event> events) { // TODO: test
        final JSONObject json = new JSONObject();

        JSONArray array = new JSONArray();

        for (Event event : events) {
            array.put(toJson(event));
        }

        try {
            json.put("events", array);
            return json.toString();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    JSONObject toJson(Event event) { // TODO: test
        JSONObject object = new JSONObject();
        try {
            object.put("type", event.getType());
            object.put("message", event.getMessage());
            object.put("latitude", event.getLatitude());
            object.put("longitude", event.getLatitude());
            object.put("time", event.getTime());
            return object;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
