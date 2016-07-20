package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.google.inject.CreationException;

/**
 * Created by Athi
 */
class ErrorParser {

    private static final String NAMED_STRING = "@com.google.inject.name.Named(value=";

    private static final String INI_NAME = "ini";

    // TODO
    static String parse(Exception e) {
        String error = "";

        if (CreationException.class.isAssignableFrom(e.getClass())) {
            String message = e.getMessage();
            if (message.contains(NAMED_STRING)) {
                error = namedExceptionMessage(message);
            }
        } else {
            error = e.getMessage();
        }

        return ApplicationConfiguration.ERROR_CAPTION + error;
    }

    private static String namedExceptionMessage(String message) {
        String beginNamedMessageSubstring = message.substring(message.indexOf(NAMED_STRING));
        String fullNamedMessageSubstring = beginNamedMessageSubstring.substring(NAMED_STRING.length(), beginNamedMessageSubstring.indexOf(")"));

        if (fullNamedMessageSubstring.contains(INI_NAME)) {
            if (fullNamedMessageSubstring.endsWith(INI_NAME)) {
                return "Ini file: \"" + fullNamedMessageSubstring + "\" not found";
            } else {
                String[] iniParamValues = fullNamedMessageSubstring.split(":");
                if (iniParamValues.length == 3) {
                    return "Ini parameter: \"" + iniParamValues[2] + "\" in section \"" + iniParamValues[1] + "\" not found.";
                } else {
                    return "Wrong ini parameter injection definition, should be (ini:SECTION:NAME)";
                }
            }
        } else {
            return "Property: \"" + fullNamedMessageSubstring + "\" not found.";
        }
    }

}
