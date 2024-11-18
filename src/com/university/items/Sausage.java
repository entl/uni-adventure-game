package com.university.items;

import com.university.items.effect.SausageEffect;

public class Sausage extends Food {

    public Sausage() {
        super("sausage", "A meaty sausage.", new SausageEffect());
    }
}
