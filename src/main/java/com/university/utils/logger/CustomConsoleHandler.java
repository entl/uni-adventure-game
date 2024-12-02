package com.university.utils.logger;

import java.util.logging.ConsoleHandler;

public class CustomConsoleHandler extends ConsoleHandler {
    public CustomConsoleHandler() {
        setFormatter(new CustomLogFormatter()); // Use your custom formatter
    }
}
