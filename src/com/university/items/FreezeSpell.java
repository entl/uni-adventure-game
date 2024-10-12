package com.university.items;

public class FreezeSpell extends Spell {
    public FreezeSpell() {
        super("freeze Spell", "a spell that will freeze the enemy for 3 turns.", new FreezeEffect());
    }
}
