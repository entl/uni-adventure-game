package com.university.utils.commands;


import com.university.utils.logger.ILogger;
import com.university.utils.logger.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CommandFactory {
    private final ILogger logger = LoggerFactory.getLogger(CommandFactory.class);
    // store command and lambda function to create command
    private final Map<Command, Function<CommandContext, ICommand>> commandRegistry = new HashMap<>();

    public CommandFactory() {
        logger.info("Initializing CommandFactory and registering commands.");
        registerCommand( Command.MOVE, context -> new MoveCommand(context.getDirection()));
        registerCommand(Command.LOOK_AROUND, context -> new LookAroundCommand());
        registerCommand(Command.USE, context -> new UseCommand(context.getItemName()));
        registerCommand(Command.PICK_UP, context -> new PickUpCommand(context.getItemName()));
        registerCommand(Command.DROP, context -> new DropCommand(context.getItemName()));
        registerCommand(Command.SHOW_INVENTORY, context -> new ShowInventoryCommand());
        registerCommand(Command.HELP, context -> new HelpCommand());
        registerCommand(Command.ESCAPE, context -> new EscapeCommand());
        registerCommand(Command.QUIT, context -> new QuitCommand());
        registerCommand(Command.SHOW_STATS, context -> new ShowStatsCommand());
        registerCommand(Command.SHOW_MAP, context -> new ShowMapCommand());
        registerCommand(Command.OPEN, context -> new OpenChestCommand(context.getItemName()));
    }

    /**
     * Registers a command with its associated creator function.
     *
     * @param command     the command
     * @param creator     a function that takes a CommandContext and returns an ICommand
     */
    public void registerCommand(Command command, Function<CommandContext, ICommand> creator) {
        commandRegistry.put(command, creator);
    }

    /**
     * Creates a command based on the name and context.
     *
     * @param command    the command
     * @param commandContext the context for the command
     * @return the created command
     */
    public ICommand createCommand(Command command, CommandContext commandContext) {
        logger.debug("Attempting to create command: " + command);
        Function<CommandContext, ICommand> creator = commandRegistry.get(command);
        if (creator != null) {
            // create command using the lambda function
            return creator.apply(commandContext);
        } else {
            return new UnknownCommand();
        }
    }
}