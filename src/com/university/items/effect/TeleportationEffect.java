package com.university.items.effect;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;

import java.util.ArrayList;
import java.util.List;

public class TeleportationEffect implements IEffect {
    @Override
    public void apply(GameContext gameContext) {
        List<List<Room>> rooms = gameContext.getCurrentDungeon().getRooms();
        Room currentRoom = gameContext.getPlayer().getCurrentRoom();
        List<Room> flattenedRooms = flattenRoomsAndRemoveWalls(rooms);

        // get any random room that is not the current room
        Room destination = getRandomRoom(flattenedRooms);
        while (destination.equals(currentRoom)) {
            destination = getRandomRoom(flattenedRooms);
        }

        teleportPlayer(gameContext, destination);
    }

    @Override
    public void remove(GameContext gameContext) {

    }

    private void teleportPlayer(GameContext gameContext, Room destination) {
        gameContext.getPlayer().setCurrentRoom(destination);
        gameContext.setCurrentRoom(destination);
    }

    private List<Room> flattenRoomsAndRemoveWalls(List<List<Room>> rooms) {
        List<Room> flattenedRooms = new ArrayList<>();
        for (List<Room> roomList : rooms) {
            for (Room room : roomList) {
                if (!room.isWall()) {
                    flattenedRooms.add(room);
                }
            }
        }
        return flattenedRooms;
    }

    private Room getRandomRoom(List<Room> rooms) {
        int randomIndex = (int) (Math.random() * rooms.size());
        return rooms.get(randomIndex);
    }
}
