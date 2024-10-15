package com.university.items;

import com.university.items.effect.EatSandwichEffect;

public class Sandwich extends Food {
    public Sandwich() {
        super("sandwich", "A delicious sandwich that will give you 10 powerpoints.", new EatSandwichEffect());
    }
}
