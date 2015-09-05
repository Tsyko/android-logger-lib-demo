package com.nix.chronicler;

import java.util.List;

/**
 * Interface for event storage.
 */
interface EventStorage {

    boolean isEmpty();
    void addFirst(Event event);
    void addLast(Event event);
    List<Event> removeAll();
}
