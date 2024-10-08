package com.university.item;

public class Spell implements IItem {
    private String name;
    private String description;

    public Spell(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void use() {
        System.out.println("Spell used!");
    }

    public String describe() {
        return "Spell name: " + name + "\nSpell description: " + description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
