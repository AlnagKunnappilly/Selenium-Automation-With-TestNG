package com.bankingproject.utility;

import org.apache.log4j.Logger;

public class Log {

    // Create a logger instance for this class
    private static final Logger logger = Logger.getLogger(Log.class);

    // Info-level logging
    public static void info(String message) {
        if (logger.isInfoEnabled()) {  // Check if INFO level is enabled
            logger.info(message);      // Log the message at INFO level
        }
    }

    // Debug-level logging
    public static void debug(String message) {
        if (logger.isDebugEnabled()) {  // Check if DEBUG level is enabled
            logger.debug(message);      // Log the message at DEBUG level
        }
    }

    // Error-level logging
    public static void error(String message, Throwable throwable) {
        if (logger.isEnabledFor(org.apache.log4j.Level.ERROR)) {
            logger.error(message, throwable);  // Log the message at ERROR level
        }
    }

    // Warn-level logging
    public static void warn(String message) {
        if (logger.isEnabledFor(org.apache.log4j.Level.WARN)) {
            logger.warn(message);  // Log the message at WARN level
        }
    }

    // Log the start of a test case
    public static void startTestCase(String testCaseName) {
        logger.info("********** STARTING TEST CASE: " + testCaseName + " **********");
    }

    // Log the end of a test case
    public static void endTestCase(String testCaseName) {
        logger.info("********** ENDING TEST CASE: " + testCaseName + " **********");
    }
}
