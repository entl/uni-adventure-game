package com.university.elements.items;

import com.university.elements.items.effect.TeleportationEffect;

public class TeleportationSpell extends Spell {
    public TeleportationSpell() {
        super("teleportation spell", "A spell that teleports the user to a random location", new TeleportationEffect());
    }
}
