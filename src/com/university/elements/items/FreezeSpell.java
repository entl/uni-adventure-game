package com.university.elements.items;

import com.university.elements.items.effect.FreezeSpellEffect;

public class FreezeSpell extends Spell {
    public FreezeSpell() {
        super("freeze spell", "A spell that will let you escape from certain traps", new FreezeSpellEffect());
    }
}
