package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.ITrap;
import com.university.gameElements.traps.strategies.FreezeSpellStrategy;
import com.university.gameElements.traps.strategies.SausageStrategy;
import com.university.utils.UI.UIManager;
import com.university.utils.events.EscapeEvent;

public class SausageEffect implements IEffect{
    @Override
    public void apply(GameContext gameContext) {
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        ITrap trap = currentRoom.getTrap();
        if (trap != null) {
            gameContext.getEventManager().dispatchEvent(new EscapeEvent(trap, new SausageStrategy()));
        } else {
            UIManager.getInstance().displayMessage("* You tried to eat sausage, but decided to keep it for later.");
        }
    }
}
