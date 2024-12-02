package com.university.elements.items;

import com.university.elements.items.effect.AlarmEffect;

public class AlarmClock extends Tool {
    public AlarmClock() {
        super("alarm clock", "A loud alarm that will keep you awake", new AlarmEffect());
    }
}
