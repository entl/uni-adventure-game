package com.university.utils.logger;

public class LoggerFactory {
    public static ILogger getLogger(Class<?> clazz) {
        return new CustomLogger(clazz, new CustomConsoleHandler());
    }
}
