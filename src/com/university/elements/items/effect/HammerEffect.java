package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.HammerStrategy;
import com.university.utils.ui.UIManager;
import com.university.utils.events.EscapeEvent;

/**
 * The {@code HammerEffect} class represents the effect of using a hammer.
 * When applied, it allows the player to escape from traps of type {@code Trap}
 * using the {@code HammerStrategy}.
 *
 * <p><strong>Note:</strong> This implementation is specifically designed to work with traps
 * of the {@code Trap} type. Other trap types will not be affected by this effect unless explicitly handled.</p>
 */
public class HammerEffect implements IEffect {

    /**
     * Applies the hammer effect. If the player is in a room containing a {@code Trap},
     * the trap's escape method is invoked using the {@code HammerStrategy}.
     *
     * @param gameContext the context of the game, which includes the player and the current room state.
     */
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        ITrap trap = currentRoom.getTrap();
        if (trap != null) {
            gameContext.getEventManager().dispatchEvent(new EscapeEvent(trap, new HammerStrategy()));
        } else {
            UIManager.getInstance().displayMessage("* You tried to use the hammer, but it had no effect.");
        }
    }
}