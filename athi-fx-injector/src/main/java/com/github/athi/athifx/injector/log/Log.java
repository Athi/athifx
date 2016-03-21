package com.github.athi.athifx.injector.log;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Athi
 */
public class Log {

    public static final String DEFAULT_LOGGER_NAME = "APPLICATION_LOGGER";
    public static final String DEFAULT_LOG_FILE_NAME = "application.log";

    private static final Logger LOGGER = Logger.getLogger(DEFAULT_LOGGER_NAME);
    private Handler handler;

    private final Class<?> logClass;
    private final String fileName;

    private Log(Class<?> logClass, String fileName) {
        this.logClass = logClass;
        this.fileName = fileName;
    }

    /**
     * Creates LOGGER instance for given class
     * @param logClass - class to log
     * @return - LOGGER instance
     */
    public static Log getLogger(Class<?> logClass) {
        return new Log(logClass, DEFAULT_LOG_FILE_NAME);
    }

    /**
     * Creates LOGGER instance for given class and custom file to log.
     * @param logClass - class to log
     * @param logFile - file definition in witch the log output will be written.
     * @param <FILE> - Enum implementing the LogFile interface
     * @return - LOGGER instance
     */
    public static <FILE extends Enum & LogFile> Log getLogger(Class<?> logClass, FILE logFile) {
        return new Log(logClass, logFile.getFileName());
    }

    /**
     * Error logging
     * @param message - message to be written
     * @param throwable -  Throwable to get the stacktrace
     */
    public void error(String message, Throwable throwable) {
        initHandler();
        LOGGER.logp(AthiLoggingLevel.ERROR, logClass.getName(), "", message, throwable);
        closeHandler();
    }

    /**
     * Warning logging
     * @param message - message to be written
     * @param throwable -  Throwable to get the stacktrace
     */
    public void warning(String message, Throwable throwable) {
        initHandler();
        LOGGER.logp(AthiLoggingLevel.WARNING, logClass.getName(), "", message, throwable);
        closeHandler();
    }

    /**
     * Info loggin
     * @param message - message to be written
     */
    public void info(String message) {
        initHandler();
        LOGGER.logp(AthiLoggingLevel.INFO, logClass.getName(), "", message);
        closeHandler();
    }

    private void initHandler() {
        try {
            handler = new FileHandler(fileName, true);
            handler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(handler);
            LOGGER.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeHandler() {
        handler.close();
    }

    /**
     * Interface for the enum implementations defining the file to log.
     */
    public interface LogFile {

        String getName();

        String getSuffix();

        default String getFileName() {
            return getName().concat(".").concat(getSuffix());
        }
    }

    private static class AthiLoggingLevel extends Level {

        private static final String ERROR_NAME = "ERROR";
        private static final String WARNING_NAME = "WARNING";
        private static final String INFO_NAME = "INFO";

        private static final int ERROR_VALUE = 1300;
        private static final int WARNING_VALUE = 1200;
        private static final int INFO_VALUE = 1100;

        private static final Level ERROR = new AthiLoggingLevel(ERROR_NAME, ERROR_VALUE);
        private static final Level WARNING = new AthiLoggingLevel(WARNING_NAME, WARNING_VALUE);
        private static final Level INFO = new AthiLoggingLevel(INFO_NAME, INFO_VALUE);

        public AthiLoggingLevel(String name, int value) {
            super(name, value);
        }
    }

}
