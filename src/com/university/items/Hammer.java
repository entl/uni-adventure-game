package com.university.items;

import com.university.items.effect.HammerEffect;

public class Hammer extends Tool {
    public Hammer() {
        super("hammer", "A hammer that will let you escape from certain traps", new HammerEffect());
    }
}
