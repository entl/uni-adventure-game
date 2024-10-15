package com.university.items;

import com.university.items.effect.SpannerEffect;

public class Spanner extends Tool {
    public Spanner() {
        super("spanner", "Use spanner to open rusty chest locks", new SpannerEffect());
    }
}
