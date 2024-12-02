package com.university.utils.logger;

public interface ILogger {
    void debug(String message);
    void info(String message);
    void warning(String message);
    void error(String message);
    void error(String message, Throwable throwable);
}
