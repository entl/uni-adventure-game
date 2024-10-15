package com.university.items;

import com.university.items.effect.EatCakeEffect;

public class Cake extends Food {
    public Cake() {
        super("cake", "A delicious cake that will give you 3 powerpoints.", new EatCakeEffect());
    }
}
