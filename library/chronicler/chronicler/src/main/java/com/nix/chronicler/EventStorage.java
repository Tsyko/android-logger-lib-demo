package com.nix.chronicler;

import java.util.List;

/**
 * Created by andriy on 05 September 2015.
 */
interface EventStorage {

    boolean isEmpty();
    void addLast(Event event);
    List<Event> removeAll();
}
