package com.university.utils.logger;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * A custom logger implementation using Java's built-in logging framework.
 * Provides methods for logging messages at different levels, including debug, info, warning, and error.
 */
public class CustomLogger implements ILogger {
    private final Logger logger;

    /**
     * Constructs a CustomLogger instance for the given class and logging handler
     *
     * @param clazz   the class for which the logger is created
     * @param handler the logging handler to use for output
     */
    public CustomLogger(Class<?> clazz, Handler handler) {
        logger = Logger.getLogger(clazz.getName());
        logger.setLevel(Level.ALL);

        // Disable parent loggers
        logger.setUseParentHandlers(false);

        handler.setLevel(Level.INFO);
        logger.addHandler(handler);
    }

    @Override
    public void debug(String message) {
        logger.fine(message);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warning(String message) {
        logger.warning(message);
    }

    @Override
    public void error(String message) {
        logger.severe(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.log(Level.SEVERE, message, throwable);
    }
}
