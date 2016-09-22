package com.github.athi.athifx.gui.menu.exception;

/**
 * Created by mp2
 */
public class AMenuConfigurationException extends RuntimeException {

    public AMenuConfigurationException() {
    }

    public AMenuConfigurationException(String message) {
        super(message);
    }

    public AMenuConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
