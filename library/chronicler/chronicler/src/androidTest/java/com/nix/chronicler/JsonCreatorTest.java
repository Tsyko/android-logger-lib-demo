package com.nix.chronicler;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by andriy on 05 September 2015.
 */
public class JsonCreatorTest extends TestCase {

    private Event event1;
    private Event event2;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        event1 = new Event(0, "message 1", 21.8308291, 20.0050833, 1441468345842L);
        event2 = new Event(1, "message 2", 35.8308285, 15.0050845, 1441468360893L);
    }

    public void testEventSerialization() {
        JsonCreator creator = new JsonCreator();
        assertEquals(event1, creator.fromJson(creator.toJson(event1)));
        assertEquals(event2, creator.fromJson(creator.toJson(event2)));
    }

    public void testFullSerialization() throws JSONException {
        JsonCreator creator = new JsonCreator();
        List<Event> events = new ArrayList<>();
        events.add(event1);
        events.add(event2);

        final JSONObject json = creator.toJson(events);
        final JSONArray array = json.getJSONArray(JsonCreator.EVENTS);

        assertEquals(events.size(), array.length());
        assertEquals(event1, creator.fromJson((JSONObject) array.get(0)));
        assertEquals(event2, creator.fromJson((JSONObject) array.get(1)));
    }
}
