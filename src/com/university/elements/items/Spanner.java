package com.university.elements.items;

import com.university.elements.items.effect.SpannerEffect;

public class Spanner extends Tool {
    public Spanner() {
        super("spanner", "Use spanner to open rusty chest locks", new SpannerEffect());
    }
}
