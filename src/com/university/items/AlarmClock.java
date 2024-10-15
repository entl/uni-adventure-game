package com.university.items;

import com.university.items.effect.AlarmEffect;

public class AlarmClock extends Tool {
    public AlarmClock() {
        super("alarm clock", "A loud alarm that will keep you awake", new AlarmEffect());
    }
}
