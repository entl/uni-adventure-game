package com.university.utils.parsers;

import com.university.elements.items.ItemFactory;
import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;
import com.university.utils.commands.*;

import java.util.*;

/**
 * The {@code CommandParser} class is responsible for parsing user input and
 * determining the appropriate game command to execute. It supports interpreting
 * commands, directions, and item names from the player's input while ignoring
 * common stopwords.
 */
public class CommandParser {

    private static final ILogger logger = LoggerFactory.getLogger(CommandParser.class);

    private Set<String> stopwords;
    private ItemFactory itemFactory;
    private CommandFactory commandFactory;

    public CommandParser(ItemFactory itemFactory, CommandFactory commandFactory) {
        this.itemFactory = itemFactory;
        this.commandFactory = commandFactory;
        initializeStopwords();
        logger.info("CommandParser initialized with ItemFactory and stopwords.");
    }

    /**
     * Parses the user's input and returns the corresponding {@code ICommand} based on
     * the command and additional context such as direction or item name.
     *
     * @param userInput the raw input string from the user
     * @return the corresponding {@code ICommand} or null if no valid command is found
     */
    public ICommand parse(String userInput) {
        logger.info("Parsing user input: " + userInput);

        String[] processedTokens = preprocessInput(userInput);
        logger.debug("Processed tokens: " + Arrays.toString(processedTokens));

        Command command = extractCommand(processedTokens);
        logger.debug("Extracted command: " + command);

        String itemName = itemFactory.findBestMatchingItemName(processedTokens);
        logger.debug("Matched item name: " + itemName);

        Direction direction = extractDirection(processedTokens);
        logger.debug("Parsed direction: " + direction);

        CommandContext context = new CommandContext(itemName, direction);

        return commandFactory.createCommand(command, context);
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
        logger.info("Stopwords initialized with " + stopwords.size() + " entries.");
    }

    /**
     * Preprocesses the user's input by removing stopwords and splitting the input
     * into individual tokens.
     *
     * @param userInput the raw input string from the user
     * @return an array of tokens after preprocessing
     */
    private String[] preprocessInput(String userInput) {
        logger.debug("Preprocessing input: " + userInput);
        String[] tokens = userInput.trim().toLowerCase().split(" ");
        List<String> processedTokens = new ArrayList<>();

        for (String token : tokens) {
            if (!stopwords.contains(token)) {
                processedTokens.add(token);
            }
        }

        logger.debug("Tokens after removing stopwords: " + processedTokens);
        return processedTokens.toArray(new String[0]);
    }

    /**
     * Extracts the {@code Command} from the array of words based on recognized command aliases.
     *
     * @param words the preprocessed array of words from the user's input
     * @return the corresponding {@code Command} or {@code Command.UNKNOWN} if no valid command is found
     */
    private Command extractCommand(String[] words) {
        logger.debug("Extracting command from words: " + Arrays.toString(words));
        for (String word : words) {
            Command command = Command.fromAlias(word);
            if (command != Command.UNKNOWN) {
                logger.debug("Command found: " + command);
                return command;
            }
        }
        logger.warning("No valid command found in input: " + Arrays.toString(words));
        return Command.UNKNOWN;
    }

    /**
     * Parses the direction from the array of words, checking for common directional commands.
     *
     * @param words the preprocessed array of words from the user's input
     * @return the {@code Direction} if found, or null if no direction is specified
     */
    private Direction extractDirection(String[] words) {
        logger.debug("Parsing direction from words: " + Arrays.toString(words));
        for (String word : words) {
            Direction direction = Direction.fromAlias(word);
            if (direction != null) {
                logger.debug("Direction found: " + direction);
                return direction;
            }
        }
        logger.debug("No direction found in input: " + Arrays.toString(words));
        return null;
    }
}