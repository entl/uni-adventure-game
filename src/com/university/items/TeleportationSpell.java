package com.university.items;

import com.university.items.effect.TeleportationEffect;

public class TeleportationSpell extends Spell {
    public TeleportationSpell() {
        super("teleportation spell", "a spell that teleports the user to a random location", new TeleportationEffect());
    }
}
