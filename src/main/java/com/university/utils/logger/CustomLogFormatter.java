package com.university.utils.logger;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * A custom log formatter for formatting log messages.
 * Formats log records with the following structure:
 * <pre>
 * [date time] [logger name] [log level]: message
 * </pre>
 * Example:
 * <pre>
 * 2024-12-03 12:34:56 [MyLogger] INFO: This is a log message
 * </pre>
 */
public class CustomLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return String.format(
                "%1$tF %1$tT [%2$s] %3$s: %4$s %n",
                record.getMillis(),
                record.getLoggerName(),
                record.getLevel().getName(),
                formatMessage(record)
        );
    }
}
