package com.university.item;

public class Tool implements IItem {
    private String name;
    private String description;

    public Tool(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void use() {
        System.out.println("Tool used!");
    }

    public String describe() {
        return "Tool name: " + name + "\nTool description: " + description;
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
