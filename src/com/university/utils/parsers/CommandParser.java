package com.university.utils.parsers;


import com.university.items.ItemFactory;
import com.university.utils.commands.*;

import java.util.*;


public class CommandParser {

    private Set<String> stopwords;
    private ItemFactory itemFactory;

    public CommandParser() {
        itemFactory = new ItemFactory();
        initializeStopwords();
    }

    public ICommand parse(String userInput) {
        String[] processedTokens = preprocessInput(userInput);

        Command command = extractCommand(processedTokens);

        String itemName = itemFactory.findBestMatchingItemName(processedTokens);
        Direction direction = parseDirection(processedTokens);

        switch (command) {
            case MOVE:
                if (direction != null) {
                    return new MoveCommand(direction);
                }
                else {
                    System.out.println("* Specify direction");
                    return null;
                }
            case LOOK_AROUND:
                return new LookAroundCommand();
            case USE:
                if (itemName != null) {
                    return new UseCommand(itemName);
                }
                else {
                    System.out.println("* Specify item to use");
                    return null;
                }
            case PICK_UP:
                if (itemName != null) {
                    return new PickUpCommand(itemName);
                }
                else {
                    System.out.println("* Specify item to pick up");
                    return null;
                }
            case DROP:
                if (itemName != null) {
                    return new DropCommand(itemName);
                }
                else {
                    System.out.println("* Specify item to drop");
                    return null;
                }
            case SHOW_INVENTORY:
                return new ShowInventoryCommand();
            case HELP:
                return new HelpCommand();
            case ESCAPE:
                return new EscapeCommand();
            case QUIT:
                return new QuitCommand();
            case SHOW_STATS:
                return new ShowStatsCommand();
            case SHOW_MAP:
                return new ShowMapCommand();
            default:
                System.out.println("* I can't understand your input.\n* Enter `help` to see commands");
                return null;
        }
    }



    private void initializeStopwords() {
        stopwords = new HashSet<>(Arrays.asList(
                "a", "an", "the", "in", "on", "at", "with",
                "from", "and", "but", "or", "of", "to", "for",
                "is", "are", "was", "were", "be", "been", "being",
                "do", "does", "did", "doing", "have", "has", "had",
                "having", "i", "you", "he", "she", "it", "they",
                "we", "me", "him", "her", "them", "us", "my", "your",
                "his", "hers", "their", "our", "mine", "yours",
                "theirs", "ours", "this", "that", "these", "those",
                "can", "could", "would", "should", "will", "shall",
                "may", "might", "must", "if", "then", "else",
                "because", "so", "as", "until", "while", "during",
                "although", "though", "since"
        ));
    }


    private String[] preprocessInput(String userInput) {
        String[] tokens = userInput.trim().toLowerCase().split(" ");

        List<String> processedTokens = new ArrayList<>();

        for (String token: tokens) {
            if (!stopwords.contains(token)) {
                processedTokens.add(token);
            }
        }

        return processedTokens.toArray(new String[0]);
    }

    private Command extractCommand(String[] words) {
        for (String word : words) {
            Command command = Command.fromAlias(word);
            if (command != Command.UNKNOWN) {
                return command;
            }
        }
        return Command.UNKNOWN;
    }

    private Direction parseDirection(String[] words) {
        for (String word : words) {
            switch (word) {
                case "north":
                case "forward":
                    return Direction.NORTH;
                case "south":
                case "back":
                    return Direction.SOUTH;
                case "east":
                case "right":
                    return Direction.EAST;
                case "west":
                case "left":
                    return Direction.WEST;
            }
        }
        return null;
    }
}
