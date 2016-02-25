package com.athifx.application.log;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Athi
 */
public class Log {

    private final static String LOGGER_NAME = "APPLICATION_LOGGER";
    private final static Logger logger = Logger.getLogger(LOGGER_NAME);

    private static final String DEFAULT_LOG_NAME = "application.log";

    private final Class<?> logClass;

    private Log(Class<?> logClass, String fileName) {
        this.logClass = logClass;
        try {
            Handler handler = new FileHandler(fileName, true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Log getLogger(Class<?> logClass) {
        return new Log(logClass, DEFAULT_LOG_NAME);
    }

    public static <FILE extends Enum & LogFile> Log getLogger(Class<?> logClass, FILE logFile) {
        return new Log(logClass, logFile.getFileName());
    }

    public void error(String message, Throwable throwable) {
        logger.logp(AthiLoggingLevel.ERROR, logClass.getName(), "", message, throwable);
    }

    public void warning(String message, Throwable throwable) {
        logger.logp(AthiLoggingLevel.WARNING, logClass.getName(), "", message, throwable);
    }

    public void info(String message) {
        logger.logp(AthiLoggingLevel.INFO, logClass.getName(), "", message);
    }

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
