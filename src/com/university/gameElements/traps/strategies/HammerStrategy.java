package com.university.gameElements.traps.strategies;

public class HammerStrategy implements IEscapeStrategy {
    private final String item = "Hammer";
    @Override
    public void escape() {
        System.out.println("* You used " + item + " to escape the trap!");
    }
}
