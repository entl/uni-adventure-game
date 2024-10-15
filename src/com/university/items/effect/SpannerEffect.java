package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.chests.IChest;

public class SpannerEffect implements IEffect {
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
