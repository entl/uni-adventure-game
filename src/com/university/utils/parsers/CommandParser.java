package com.university.utils.parsers;


import com.university.utils.commands.Command;
import com.university.utils.commands.Direction;
import com.university.utils.commands.ICommand;
import com.university.utils.commands.MoveCommand;

import java.util.*;

public class CommandParser {

    private Map<String, Command> commandMap;
    private Set<String> stopwords;

    public CommandParser() {
        commandMap = new HashMap<>();
        initializeCommands();
    }

    public ICommand parse(String userInput) {
        String[] processedTokens = preprocessInput(userInput);

        Command command = extractCommand(processedTokens);

        switch (command) {
            case MOVE:
                Direction direction = parseDirection(processedTokens);
                if (direction != null) {
                    return new MoveCommand(direction);
                }
                else {
                    System.out.println("> Specify direction");
                    return null;
                }
            default:
                System.out.println("> I can't understand your input. \n > Enter `help` to see commands");
                return null;
        }
    }


    private void initializeCommands() {
        // Movement Keywords
        commandMap.put("move", Command.MOVE);
        commandMap.put("go", Command.MOVE);
        commandMap.put("walk", Command.MOVE);
        commandMap.put("run", Command.MOVE);

        // Pick Up keywords
        commandMap.put("pick", Command.PICK_UP);
        commandMap.put("pickup", Command.PICK_UP);
        commandMap.put("get", Command.PICK_UP);
        commandMap.put("grab", Command.PICK_UP);

        // Drop keywords
        commandMap.put("drop", Command.DROP);
        commandMap.put("discard", Command.DROP);
        commandMap.put("leave", Command.DROP);

        // Use keywords
        commandMap.put("use", Command.USE);
        commandMap.put("apply", Command.USE);
        commandMap.put("activate", Command.USE);

        // Eat keywords
        commandMap.put("eat", Command.EAT);
        commandMap.put("consume", Command.EAT);

        // Drink keywords
        commandMap.put("drink", Command.DRINK);

        // Look Around keywords (see iteams in the room and get information where to move)
        commandMap.put("look", Command.LOOK_AROUND);
        commandMap.put("examine", Command.LOOK_AROUND);
        commandMap.put("inspect", Command.LOOK_AROUND);
        commandMap.put("search", Command.LOOK_AROUND);

        // Inventory keywords
        commandMap.put("inventory", Command.SHOW_INVENTORY);
        commandMap.put("items", Command.SHOW_INVENTORY);

        // Statistics keywords
        commandMap.put("stats", Command.SHOW_STATS);

        // Help keywords (help user to determine which action words can be used)
        commandMap.put("help", Command.HELP);
        commandMap.put("commands", Command.HELP);

        // Quit keywords
        commandMap.put("quit", Command.QUIT);
        commandMap.put("exit", Command.QUIT);
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
            if (commandMap.containsKey(word)) {
                return commandMap.get(word);
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
