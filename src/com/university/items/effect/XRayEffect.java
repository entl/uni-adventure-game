package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;
import com.university.gameElements.traps.ITrap;
import com.university.player.Player;
import com.university.utils.commands.Direction;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class XRayEffect implements IEffect {

    @Override
    public void apply(GameContext gameContext) {
        Player player = gameContext.getPlayer();
        HashMap<Direction, Room> adjacentRooms = player.getCurrentRoom().getAdjacentRooms();

        Random random = new Random();
        List<Direction> directionsFromCurrentRoom = List.copyOf(adjacentRooms.keySet());
        Direction randomDirection = directionsFromCurrentRoom.get(random.nextInt(directionsFromCurrentRoom.size()));
        Room randomRoom = adjacentRooms.get(randomDirection);

        System.out.println("* You have used X-Ray vision and can look around the next room.");
        System.out.println("* In the " + randomDirection + " direction you see:");
        randomRoom.describe();

        ITrap trap = randomRoom.getTrap();
        if (trap != null) {
            System.out.printf("* You have found a %s in the next room!\n", trap.getName());
        }
    }
}
