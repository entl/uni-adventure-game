package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.chests.IChest;

/**
 * The {@code SpannerEffect} class represents an effect that allows the player to interact with chests in the room.
 * When applied, this effect opens a closed chest in the current room. If the chest is already open or if no chest
 * is present, appropriate messages are displayed.
 */
public class SpannerEffect implements IEffect {

    /**
     * Applies the spanner effect, allowing the player to open a chest in the current room.
     * If the chest is already opened, a message is shown. If no chest is found, the player is notified.
     *
     * @param gameContext the context of the game, which includes the player and the current game state.
     */
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();

        IChest chest = currentRoom.getChest();
        if (chest != null && !chest.isOpened()) {
            chest.open(gameContext);
        } else if (chest != null && chest.isOpened()) {
            System.out.println("* The chest is already opened.");
        } else {
            System.out.println("* There is no chest in this room.");
        }
    }
}