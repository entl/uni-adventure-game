package com.university.utils.commands;

import com.university.dungeon.room.Room;
import com.university.game.GameContext;

import java.util.*;


public class ShowMapCommand implements ICommand {

    @Override
    public void execute(GameContext context) {
        System.out.println("* You have great memory! Here are the places you have visited:\n");

        List<List<Room>> rooms = GameContext.getInstance().getCurrentDungeon().getRooms();
        Room currentRoom = context.getPlayer().getCurrentRoom();

        char[][] mapGrid = mapGrid(rooms, currentRoom);
        displayMap(mapGrid);
    }

    private char[][] mapGrid(List<List<Room>> rooms, Room currentRoom) {
        char[][] mapGrid = new char[rooms.size()][rooms.getFirst().size()];

        for (int row = 0; row < rooms.size(); row++) {
            for (int col = 0; col < rooms.get(row).size(); col++) {
                Room room = rooms.get(row).get(col);
                if (room.equals(currentRoom)) {
                    mapGrid[row][col] = 'P';
                }
                else if (room.isEntrance() && room.isVisited()) {
                    mapGrid[row][col] = 'E';
                }
                else if (room.isExit() && room.isVisited()) {
                    mapGrid[row][col] = 'X';
                }
                else if (room.isVisited()) {
                    mapGrid[row][col] = '.';
                } else {
                    mapGrid[row][col] = '#';
                }
            }
        }

        return mapGrid;
    }

    private void displayMap(char[][] mapGrid) {
        System.out.println("==============================");
        System.out.println("         Dungeon Map");
        System.out.println("==============================\n");
        for (char[] row : mapGrid) {
            System.out.print("\t\t");
            for (char cell : row) {
                System.out.printf("%-3s", cell);
            }
            System.out.println();
        }

        System.out.println("\n==============================");
        System.out.println("Legend: P - You | E - Entrance | . - Route | # - Unexplored");
        System.out.println("==============================");
    }

}
