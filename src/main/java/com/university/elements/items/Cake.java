package com.university.elements.items;

import com.university.elements.items.effect.EatCakeEffect;

public class Cake extends Food {
    public Cake() {
        super("cake", "A delicious cake that will give you 3 powerpoints.", new EatCakeEffect());
    }
}
