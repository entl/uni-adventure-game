package com.university.item;

public class TeleportationSpell extends Spell {
    private String destination;

    public TeleportationSpell(String name, String description, String destination) {
        super(name, description);
        this.destination = destination;
    }

    public void use() {
        System.out.println("Teleportation spell used! You are now in " + destination);
    }

    public String describe() {
        return super.describe() + "\nDestination: " + destination;
    }
}
