package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.MadScientists;
import com.university.gameElements.traps.Trap;
import com.university.gameElements.traps.strategies.FreezeSpellStrategy;

/**
 * The {@code FreezeSpellEffect} class represents the effect of using a freeze spell.
 * When applied, it allows the player to escape from certain types of traps,
 * specifically {@code MadScientists} and {@code Trap}, using the {@code FreezeSpellStrategy}.
 *
 * <p><strong>Note:</strong> This implementation is tightly coupled with the {@code MadScientists} and {@code Trap}
 * classes, meaning it will only work for these specific types of traps. Future traps would need to
 * either extend these classes or be manually added to this logic.</p>
 */
public class FreezeSpellEffect implements IEffect {

    /**
     * Applies the freeze spell effect. If the player is in a room containing a trap of type {@code MadScientists}
     * or {@code Trap}, the trap's escape method is called using the {@code FreezeSpellStrategy}.
     *
     * @param gameContext the context of the game, which contains the player and the current room state.
     */
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        if (currentRoom.getTrap() instanceof MadScientists || currentRoom.getTrap() instanceof Trap) {
            currentRoom.getTrap().escape(gameContext, new FreezeSpellStrategy());
        } else {
            System.out.println("* You tried to cast a freeze spell, but it had no effect.");
        }
    }
}