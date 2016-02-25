package com.athifx.application.log;

import java.io.IOException;
import java.util.logging.*;

/**
 * Created by Athi
 */
public class Log {

    private final static String LOGGER_NAME = "APPLICATION_LOGGER";
    private final static Logger logger = Logger.getLogger(LOGGER_NAME);

    private final Class<?> logClass;

    private Log(Class<?> logClass) {
        this.logClass = logClass;
        try {
            Handler handler = new FileHandler("application.log", true);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Log getLogger(Class<?> logClass) {
        return new Log(logClass);
    }

    public void error(String message, Throwable throwable) {
        logger.logp(AthiLoggingLevel.ERROR, logClass.getName(), "", message, throwable);
    }

    public void warning(String message, Throwable throwable) {
        logger.logp(AthiLoggingLevel.WARRNING, logClass.getName(), "", message, throwable);
    }

    public void info(String message) {
        logger.logp(AthiLoggingLevel.INFO, logClass.getName(), "", message);
    }

    public static class AthiLoggingLevel extends Level {

        public static final String ERROR_NAME = "ERROR";
        public static final String WARNING_NAME = "WARNING";
        public static final String INFO_NAME = "INFO";

        public static final int ERROR_VALUE = 1300;
        public static final int WARNING_VALUE = 1200;
        public static final int INFO_VALUE = 1100;

        public static final Level ERROR = new AthiLoggingLevel(ERROR_NAME, ERROR_VALUE);
        public static final Level WARRNING = new AthiLoggingLevel(WARNING_NAME, WARNING_VALUE);
        public static final Level INFO = new AthiLoggingLevel(INFO_NAME, INFO_VALUE);

        public AthiLoggingLevel(String name, int value) {
            super(name, value);
        }
    }

}
