package com.university.item;

public class Item implements IItem {
    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void use() {
        System.out.println("Item used!");
    }

    public String information() {
        return "Item name: " + name + "\nItem description: " + description;
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
