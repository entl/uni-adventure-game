package com.university.utils.parsers;

import com.university.items.ItemFactory;
import com.university.utils.commands.*;

import java.util.*;

/**
 * The {@code CommandParser} class is responsible for parsing user input and
 * determining the appropriate game command to execute. It supports interpreting
 * commands, directions, and item names from the player's input while ignoring
 * common stopwords.
 */
public class CommandParser {

    private Set<String> stopwords;
    private ItemFactory itemFactory;

    public CommandParser() {
        itemFactory = new ItemFactory();
        initializeStopwords();
    }

    /**
     * Parses the user's input and returns the corresponding {@code ICommand} based on
     * the command and additional context such as direction or item name.
     *
     * @param userInput the raw input string from the user
     * @return the corresponding {@code ICommand} or null if no valid command is found
     */
    public ICommand parse(String userInput) {
        String[] processedTokens = preprocessInput(userInput);

        Command command = extractCommand(processedTokens);
        String itemName = itemFactory.findBestMatchingItemName(processedTokens);
        Direction direction = parseDirection(processedTokens);

        switch (command) {
            case MOVE:
                if (direction != null) {
                    return new MoveCommand(direction);
                } else {
                    System.out.println("* Specify direction");
                    return null;
                }
            case LOOK_AROUND:
                return new LookAroundCommand();
            case USE:
                if (itemName != null) {
                    return new UseCommand(itemName);
                } else {
                    System.out.println("* Specify item to use");
                    return null;
                }
            case PICK_UP:
                if (itemName != null) {
                    return new PickUpCommand(itemName);
                } else {
                    System.out.println("* Specify item to pick up");
                    return null;
                }
            case DROP:
                if (itemName != null) {
                    return new DropCommand(itemName);
                } else {
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

    /**
     * Initializes the set of common stopwords that will be ignored during input parsing.
     */
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

    /**
     * Preprocesses the user's input by removing stopwords and splitting the input
     * into individual tokens.
     *
     * @param userInput the raw input string from the user
     * @return an array of tokens after preprocessing
     */
    private String[] preprocessInput(String userInput) {
        String[] tokens = userInput.trim().toLowerCase().split(" ");

        List<String> processedTokens = new ArrayList<>();

        for (String token : tokens) {
            if (!stopwords.contains(token)) {
                processedTokens.add(token);
            }
        }

        return processedTokens.toArray(new String[0]);
    }

    /**
     * Extracts the {@code Command} from the array of words based on recognized command aliases.
     *
     * @param words the preprocessed array of words from the user's input
     * @return the corresponding {@code Command} or {@code Command.UNKNOWN} if no valid command is found
     */
    private Command extractCommand(String[] words) {
        for (String word : words) {
            Command command = Command.fromAlias(word);
            if (command != Command.UNKNOWN) {
                return command;
            }
        }
        return Command.UNKNOWN;
    }

    /**
     * Parses the direction from the array of words, checking for common directional commands.
     *
     * @param words the preprocessed array of words from the user's input
     * @return the {@code Direction} if found, or null if no direction is specified
     */
    private Direction parseDirection(String[] words) {
        for (String word : words) {
            Direction direction = Direction.fromAlias(word);
            if (direction != null) {
                return direction;
            }
        }
        return null;
    }
}