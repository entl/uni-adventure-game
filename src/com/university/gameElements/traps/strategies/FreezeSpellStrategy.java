package com.university.gameElements.traps.strategies;

public class FreezeSpellStrategy implements IEscapeStrategy {
    private final String item = "Freeze Spell";
    @Override
    public void escape() {
        System.out.println("* You used " + item + " to escape the trap!");
    }
}
