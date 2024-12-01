package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.FreezeSpellStrategy;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;

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
        ITrap trap = currentRoom.getTrap();
        if (trap != null) {
            gameContext.getEventManager().dispatchEvent(new EscapeEvent(trap, new FreezeSpellStrategy()));
        } else {
            UIManager.getInstance().displayMessage("* You tried to cast a freeze spell, but it had no effect.");
        }
    }
}