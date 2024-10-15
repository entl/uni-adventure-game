package com.university.items;

import com.university.items.effect.FreezeSpellEffect;

public class FreezeSpell extends Spell {
    public FreezeSpell() {
        super("freeze spell", "A spell that will let you escape from certain traps", new FreezeSpellEffect());
    }
}
