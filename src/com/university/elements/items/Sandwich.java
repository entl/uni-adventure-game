package com.university.elements.items;

import com.university.elements.items.effect.EatSandwichEffect;

public class Sandwich extends Food {
    public Sandwich() {
        super("sandwich", "A delicious sandwich that will give you 10 powerpoints.", new EatSandwichEffect());
    }
}
