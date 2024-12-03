package com.university.utils.logger;

import java.util.logging.ConsoleHandler;

/**
 * Custom console handler for the logger.
 */
public class CustomConsoleHandler extends ConsoleHandler {
    public CustomConsoleHandler() {
        setFormatter(new CustomLogFormatter()); // Use your custom formatter
    }
}
