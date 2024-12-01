package com.university.elements.items.effect;

import com.university.dungeon.room.Room;
import com.university.core.GameContext;
import com.university.elements.traps.ITrap;
import com.university.elements.traps.strategies.SausageStrategy;
import com.university.utils.ui.UIManager;
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
