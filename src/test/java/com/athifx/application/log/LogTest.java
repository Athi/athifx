package com.athifx.application.log;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class LogTest {

    private final static String ERROR_TYPE_PREFIX = "ERROR: ";
    private final static String WARNING_TYPE_PREFIX = "WARNING: ";
    private final static String INFO_TYPE_PREFIX = "INFO: ";
    private final static String MESSAGE_1 = "MESSAGE_1";
    private final static String MESSAGE_2 = "MESSAGE_2";
    private final static String MESSAGE_3 = "MESSAGE_3";
    private final static String IN_ERROR_1 = "MESSAGE_1";
    private final static String IN_ERROR_2 = "MESSAGE_2";

    @Test
    public void logingTest() throws IOException {
        Log LOGGER_1 = Log.getLogger(LogTest.class);
        LOGGER_1.error(MESSAGE_1, new Error(IN_ERROR_1));
        LOGGER_1.warning(MESSAGE_2, new Error(IN_ERROR_2));
        LOGGER_1.info(MESSAGE_3);

        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        file.deleteOnExit();

        byte[] bytes = Files.readAllBytes(file.toPath());
        String logFileContent = new String(bytes);

        System.out.println(logFileContent);

        assertEquals(true, logFileContent.contains(MESSAGE_1));
        assertEquals(true, logFileContent.contains(MESSAGE_2));
        assertEquals(true, logFileContent.contains(MESSAGE_3));

        assertEquals(true, logFileContent.contains(ERROR_TYPE_PREFIX + MESSAGE_1));
        assertEquals(true, logFileContent.contains(WARNING_TYPE_PREFIX + MESSAGE_2));
        assertEquals(true, logFileContent.contains(INFO_TYPE_PREFIX + MESSAGE_3));

        assertEquals(true, logFileContent.contains(IN_ERROR_1));
        assertEquals(true, logFileContent.contains(IN_ERROR_2));

        assertEquals(true, logFileContent.contains(this.getClass().getCanonicalName()));
    }

    @Test
    public void logingTestCustomFile() throws IOException {
        Log LOGGER_2 = Log.getLogger(LogTest.class, LogFileMock.TEST_LOG_FILE);
        LOGGER_2.error(MESSAGE_1, new Error(IN_ERROR_1));
        LOGGER_2.warning(MESSAGE_2, new Error(IN_ERROR_2));
        LOGGER_2.info(MESSAGE_3);

        File file = new File(LogFileMock.TEST_LOG_FILE.getFileName());
        file.deleteOnExit();

        byte[] bytes = Files.readAllBytes(file.toPath());
        String logFileContent = new String(bytes);

        System.out.println(logFileContent);

        assertEquals(true, logFileContent.contains(MESSAGE_1));
        assertEquals(true, logFileContent.contains(MESSAGE_2));
        assertEquals(true, logFileContent.contains(MESSAGE_3));

        assertEquals(true, logFileContent.contains(ERROR_TYPE_PREFIX + MESSAGE_1));
        assertEquals(true, logFileContent.contains(WARNING_TYPE_PREFIX + MESSAGE_2));
        assertEquals(true, logFileContent.contains(INFO_TYPE_PREFIX + MESSAGE_3));

        assertEquals(true, logFileContent.contains(IN_ERROR_1));
        assertEquals(true, logFileContent.contains(IN_ERROR_2));

        assertEquals(true, logFileContent.contains(this.getClass().getCanonicalName()));
    }

    enum LogFileMock implements Log.LogFile {
        TEST_LOG_FILE("test", "log");

        private final String name;
        private final String suffix;

        LogFileMock(String name, String suffix) {
            this.name = name;
            this.suffix = suffix;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getSuffix() {
            return suffix;
        }
    }

}
