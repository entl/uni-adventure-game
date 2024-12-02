package com.university.elements.items;

import com.university.elements.items.effect.SausageEffect;

public class Sausage extends Food {

    public Sausage() {
        super("sausage", "A meaty sausage.", new SausageEffect());
    }
}
