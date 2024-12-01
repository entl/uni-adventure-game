package com.university.utils.logger;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger implements ILogger {
    private final Logger logger;

    public CustomLogger(Class<?> clazz, Handler handler) {
        logger = Logger.getLogger(clazz.getName());
        logger.setLevel(Level.ALL);

        // Disable propagation to parent loggers
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
