package com.nix.chronicler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * In-memory implementation of event storage.
 */
class InMemoryEventStorage implements EventStorage {

    private final LinkedList<Event> list = new LinkedList<>();

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void addFirst(final Event event) {
        list.addFirst(event);
    }

    @Override
    public void addLast(final Event event) {
        list.addLast(event);
    }

    @Override
    public List<Event> removeAll() {
        final List<Event> removedEvents = Collections.unmodifiableList(new ArrayList<>(list));
        list.clear();
        return removedEvents;
    }
}
