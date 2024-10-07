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

    public String information() {
        return "Spell name: " + name + "\nSpell description: " + description;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getDescription() {
        return "";
    }
}
