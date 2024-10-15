package com.university.items.effect;

import com.university.game.GameContext;
import com.university.player.Player;

public class AlarmEffect implements IEffect{
    @Override
    public void apply(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        player.setAsleep(false);
        System.out.println("* The alarm goes off, making a loud noise that echoes through the dungeon.");
    }
}
