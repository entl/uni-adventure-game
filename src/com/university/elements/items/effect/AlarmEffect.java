package com.university.elements.items.effect;

import com.university.core.GameContext;
import com.university.player.Player;
import com.university.utils.ui.UIManager;

/**
 * The {@code AlarmEffect} class represents an effect that wakes up the player
 * from sleep when triggered, simulating the sound of an alarm in the game.
 * This effect ensures that the player is no longer in a sleep state.
 */
public class AlarmEffect implements IEffect {

    /**
     * Applies the alarm effect to the game, waking up the player by setting
     * their sleep state to false and outputting a message.
     *
     * @param gameContext the context of the game, which contains the player and other game states
     */
    @Override
    public void apply(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.setAsleep(false);
        UIManager.getInstance().displayMessage("* The alarm goes off, making a loud noise that echoes through the dungeon.");
    }
}