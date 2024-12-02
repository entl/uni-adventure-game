package com.university.utils.ui;

import com.university.dungeon.room.Room;
import com.university.elements.items.IItem;
import com.university.player.Player;
import com.university.player.inventory.InventoryManager;
import com.university.utils.commands.Command;
import com.university.utils.commands.Direction;

import java.util.Map;
import java.util.Random;

public class GameNarrator {

    private static final Random RANDOM = new Random();
    private static final int separatorLength = 30;


    // Movement-related messages
    private static final String[] MOVEMENT_SUCCESS = {
            "* You moved to the %s room!",
            "* You head to the %s room.",
            "* The %s room is your next destination."
    };
    private static final String[] MOVEMENT_FAIL = {
            "* There is no room to the %s.",
            "* You cannot move %s; there's a wall.",
            "* Movement to the %s direction is blocked."
    };

    // Look around messages
    private static final String[] LOOK_AROUND = {
            "* Looking through darkness...",
            "* Observing your surroundings...",
            "* The faint glow of light reveals the room..."
    };
    private static final String[] ROOM_EMPTY = {
            "* The room appears to be empty.",
            "* There's nothing of interest in this room.",
            "* This room contains no items"
    };

    // Inventory-related messages
    private static final String[] INVENTORY_FULL = {
            "* Your inventory is full. Drop an item before picking up another.",
            "* You cannot pick up the %s; no space in your inventory.",
            "* Inventory limit reached. Consider dropping something."
    };

    private static final String[] INVENTORY_EMPTY = {
            "* Your inventory is empty.",
            "* You are not carrying any items.",
            "* The inventory is devoid of items.",
            "* You can't see any items in your backpack."
    };

    private static final String[] SHOW_INVENTORY = {
            "* Your inventory contains the following items:",
            "* You are carrying the following items:",
            "* The items in your inventory are as follows:"
    };

    // Item interaction messages
    private static final String[] ITEM_PICKED_UP = {
            "* You picked up the %s.",
            "* The %s is now in your inventory.",
            "* You grab the %s."
    };
    private static final String[] ITEM_PICKUP_FAIL = {
            "* Your inventory is full. Drop an item before picking up another.",
            "* You cannot pick up the %s; no space in your inventory.",
            "* Inventory limit reached. Consider dropping something."
    };
    private static final String[] ITEM_DROP = {
            "* You dropped the %s.",
            "* The %s has been left in the room.",
            "* You put the %s down."
    };

    private static final String[] ITEM_NOT_FOUND = {
            "* %s not found!",
            "* The %s is not here."
    };

    private static final String[] ROOM_ITEMS = {
            "* You can barely see the following items in the room:\n",
            "* You see the following items in the room:\n"
    };

    // Trap-related messages
    private static final String[] TRAP_ACTIVATED = {
            "* You are caught in a trap and cannot move!",
            "* A trap immobilizes you. Escape is required.",
            "* You step into a trap and become stuck."
    };
    private static final String[] TRAP_ESCAPE_SUCCESS = {
            "* You successfully escaped the trap.",
            "* The trap is broken, and you are free!",
            "* You manage to get out of the trap.",
            "* You catch your breath; it seems you've managed to escape..."
    };
    private static final String[] TRAP_ESCAPE_FAIL = {
            "* You couldn't escape the trap.",
            "* The trap remains active. Try again.",
            "* Your attempt to escape the trap failed."
    };

    private static final String[] TRAP_NOT_FOUND = {
            "* There is no trap in this room.",
            "* You don't see any traps here."
    };

    // Chest-related messages
    private static final String[] CHEST_IN_ROOM = {
            "* You see a chest in the room.",
            "* A chest is visible in the room.",
            "* A chest is placed in the room."
    };

    private static final String[] CHEST_ALREADY_OPEN = {
            "* The chest is already open.",
            "* The chest has already been opened.",
            "* The chest is empty. It has already been opened."
    };

    private static final String[] CHEST_NOT_FOUND = {
            "* There is no chest in this room.",
            "* You don't see any chests here."
    };

    // Under sleep effect messages
    private static final String[] SLEEP_EFFECT = {
            "* You are asleep and cannot move!",
            "* You are in a deep sleep. No movement is possible.",
            "* You are dreaming and cannot move."
    };

    private static final String[] WAKE_UP = {
            "* You wake up from your deep slumber. You lose %d power points.",
            "* You are no longer asleep. You lose %d power points.",
            "* You open your eyes and wake up. You lose %d power points.",
            "* You wake up to the sound of a loud noise, but you can't find the source of it. You lose %d power points."
    };

    private static final String[] ALREADY_AWAKE = {
            "* You are already awake.",
            "* You are not asleep.",
    };

    // Points Lost
    private static final String[] POINTS_LOST = {
            "* You lose %d power points.",
            "* Your power points decrease by %d.",
            "* You feel weaker. You lose %d power points."
    };

    // Win/Lose messages
    private static final String[] WIN_MESSAGE = {
            "* Congratulations! You've found the treasure and won the game!",
            "* You have found the cure and saved your wife!",
            "* You successfully completed your quest. Well done!"
    };
    private static final String[] LOSE_MESSAGE = {
            "* Your power points have dropped to zero. You lost the game.",
            "* Defeat! You couldn't complete the quest. Better luck next time!",
            "* The adventure ends here. Better luck next time!",
            "* You have run out of power points and died in the dungeon. Better luck next time!"
    };

    private static final String[] QUIT_MESSAGE = {
            "* You have quit the game. Goodbye!",
            "* You have exited the game. Goodbye!",
    };

    private static final String[] PROCEED_NEXT_LEVEL = {
            "* You have successfully completed the current level. Proceeding to the %d level...",
            "* Congratulations! You have completed the current level. Moving on to the %d level...",
    };


    public static String movementSuccess(Direction direction) {
        return getRandomMessage(MOVEMENT_SUCCESS, direction);
    }

    public static String movementFail(Direction direction) {
        return getRandomMessage(MOVEMENT_FAIL, direction);
    }

    public static String lookAround(Room currentRoom) {
        return generateLookAround(currentRoom);
    }

    public static String roomEmpty() {
        return getRandomMessage(ROOM_EMPTY);
    }

    public static String inventoryFull(String itemName) {
        return getRandomMessage(INVENTORY_FULL, itemName);
    }

    public static String showInventory(InventoryManager inventory) {
        return generateInventoryList(inventory);
    }

    public static String showStats(Player player) {
        return generateStats(player);
    }

    public static String showMap(char[][] mapGrid) {
        return generateMap(mapGrid);
    }

    public static String itemPickedUp(String itemName) {
        return getRandomMessage(ITEM_PICKED_UP, itemName);
    }

    public static String itemPickupFail(String itemName) {
        return getRandomMessage(ITEM_PICKUP_FAIL, itemName);
    }

    public static String itemDropped(String itemName) {
        return getRandomMessage(ITEM_DROP, itemName);
    }

    public static String itemNotFound(String itemName) {
        return getRandomMessage(ITEM_NOT_FOUND, itemName);
    }

    public static String roomItems(Room currentRoom) {
        return generateRoomItemsList(currentRoom);
    }

    public static String chestInRoom() {
        return getRandomMessage(CHEST_IN_ROOM);
    }

    public static String chestAlreadyOpen() {
        return getRandomMessage(CHEST_ALREADY_OPEN);
    }

    public static String chestNotFound() {
        return getRandomMessage(CHEST_NOT_FOUND);
    }

    public static String trapActivated() {
        return getRandomMessage(TRAP_ACTIVATED);
    }

    public static String trapEscapeSuccess() {
        return getRandomMessage(TRAP_ESCAPE_SUCCESS);
    }

    public static String trapEscapeFail() {
        return getRandomMessage(TRAP_ESCAPE_FAIL);
    }

    public static String trapNotFound() {
        return getRandomMessage(TRAP_NOT_FOUND);
    }

    public static String sleepEffect() {
        return getRandomMessage(SLEEP_EFFECT);
    }

    public static String wakeUp(int powerPointsLost) {
        return getRandomMessage(WAKE_UP, powerPointsLost);
    }

    public static String alreadyAwake() {
        return getRandomMessage(ALREADY_AWAKE);
    }

    public static String helpList() {
        return generateHelpList();
    }

    public static String pointsLost(int points) {
        return getRandomMessage(POINTS_LOST, points);
    }

    public static String winMessage() {
        return getRandomMessage(WIN_MESSAGE);
    }

    public static String loseMessage() {
        return getRandomMessage(LOSE_MESSAGE);
    }

    public static String quitMessage() {
        return getRandomMessage(QUIT_MESSAGE);
    }

    public static String proceedNextLevel(int level) {
        return getRandomMessage(PROCEED_NEXT_LEVEL, level);
    }

    /**
     * Gets a random message from a predefined array and formats it with optional arguments.
     *
     * @param messages The array of possible messages.
     * @param args     The arguments to format the message.
     * @return A formatted string with a randomly selected output.
     */
    private static String getRandomMessage(String[] messages, Object... args) {
        String randomMessage = messages[RANDOM.nextInt(messages.length)];
        return String.format(randomMessage, args);
    }

    /**
     * Generates a dynamic "look around" message based on the current room's state.
     *
     * @param currentRoom The room the player is currently in.
     * @return The detailed look-around message.
     */
    private static String generateLookAround(Room currentRoom) {
        StringBuilder message = new StringBuilder();

        message.append(getRandomMessage(LOOK_AROUND)).append("\n");
        message.append("* The glimmer of light reveals **blood-stained signs** etched into the walls.\n");
        message.append("* You are in the **Room %s**\n\n".formatted(currentRoom.getLabel()));

        if (currentRoom.getItems().isEmpty() && currentRoom.getChest() == null) {
            message.append(getRandomMessage(ROOM_EMPTY)).append("\n\n");
        } else {
            if (!currentRoom.getItems().isEmpty()) {
                message.append("* You can barely see the following items in the room:\n");
                for (IItem item : currentRoom.getItems()) {
                    message.append(String.format("- %s\n", item.getDisplayName()));
                }
                message.append("\n");
            }

            if (currentRoom.getChest() != null) {
                if (currentRoom.getChest().isOpened()) {
                    message.append("* The chest in the room is already opened.\n\n");
                } else {
                    message.append("* You see a closed chest in the room.\n\n");
                }
            }
        }

        message.append("* You manage to see the following rooms:\n");
        for (Map.Entry<Direction, Room> entry : currentRoom.getAdjacentRooms().entrySet()) {
            message.append(String.format("- %s\n", entry.getKey()));
        }

        return message.toString();
    }

    private static String generateInventoryList(InventoryManager inventory) {
        StringBuilder message = new StringBuilder();
        message.append(generateTitle("Inventory")).append("\n");

        if (inventory.getInventory().isEmpty()) {
            message.append(getRandomMessage(INVENTORY_EMPTY)).append("\n");
            return message.toString();
        }

        // Print column headers
        message.append(String.format("%-25s %s%n", "Name", "Description"));
        message.append(getDashSeparator()).append("\n");

        // Iterate through inventory items and display them with formatting
        for (IItem item : inventory.getInventory()) {
            // Truncate descriptions if they are too long for better readability
            String description = item.getDescription();
            if (description.length() > 60) {
                description = description.substring(0, 57) + "...";
            }
            message.append(String.format("%-25s %s%n", item.getDisplayName(), description));
        }

        return message.toString();
    }

    private static String generateStats(Player player) {
        StringBuilder message = new StringBuilder();
        message.append(generateTitle("Your Stats")).append("\n");

        // Print column headers
        message.append(String.format("%-20s %s%n", "Attribute", "Value"));
        message.append(getDashSeparator()).append("\n");

        // Print power points and current room
        message.append(String.format("%-20s %s%n", "Player Name", player.getName()));
        message.append(String.format("%-20s %d%n", "Power Points", player.getPowerPoints()));
        message.append(String.format("%-20s %s%n", "Current Room", player.getCurrentRoom().getLabel()));

        return message.toString();
    }

    private static String generateMap(char[][] mapGrid) {
        StringBuilder message = new StringBuilder();
        message.append(generateTitle("Dungeon Map")).append("\n");

        for (char[] row : mapGrid) {
            message.append("\t\t");
            for (char cell : row) {
                message.append(String.format("%-3s", cell));
            }
            message.append("\n");
        }

        message.append(getDashSeparator()).append("\n");
        message.append("Legend: P - You | E - Entrance | . - Route | # - Unexplored\n");
        message.append(getDashSeparator());

        return message.toString();
    }

    /**
     * Executes the help command by printing a list of all available commands (except unknown commands).
     * The output includes the main alias of each command (formatted for alignment) and the command's description.
     *
     * @return a formatted string containing the list of available commands and their descriptions
     */
    private static String generateHelpList() {
        StringBuilder message = new StringBuilder();
        message.append(generateTitle("Help")).append("\n");

        for (Command command: Command.values()) {
            if (command != Command.UNKNOWN) {
                message.append(String.format("- %-10s : %s\n", command.getAliases().getFirst(), command.getDescription()));
            }
        }

        return message.toString();
    }

    private static String generateRoomItemsList(Room currentRoom) {
        StringBuilder message = new StringBuilder();
        message.append(getRandomMessage(ROOM_ITEMS)).append("\n");

        for (IItem item : currentRoom.getItems()) {
            message.append(String.format("- %s\n", item.getDisplayName()));
        }

        return message.toString();
    }

    private static String generateTitle(String title) {
        int paddingSize = (separatorLength - title.length()) / 2; // calculate padding size for both sides
        String padding = " ".repeat(paddingSize); // print blank spaces for padding
        return getEqualsSeparator() + "\n" + padding + title + padding + "\n" + getEqualsSeparator();
    }

    private static String getDashSeparator() {
        return "-".repeat(separatorLength);
    }

    private static String getEqualsSeparator() {
        return "=".repeat(separatorLength);
    }

    private static String getHashSeparator() {
        return "#".repeat(separatorLength);
    }

}
