package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.google.inject.CreationException;

/**
 * Created by Athi
 */
public class ErrorParser {

    // TODO
    static String parse(Exception e) {
        String error = "";

        if (CreationException.class.isAssignableFrom(e.getClass())) {
            String message = e.getMessage();
            if (message.contains("Named(value=ini")) {
                String valueIniSubstring = message.substring(message.indexOf("value=ini"));
                String iniDefinition = valueIniSubstring.substring(0, valueIniSubstring.indexOf(')'));
                String[] defs = iniDefinition.split(":");
                error = "Ini with header: " + defs[1] + " and value: " + defs[2] + " not found";
            }
        } else {
            error = e.getMessage();
        }

        return ApplicationConfiguration.ERROR_CAPTION + error;
    }

}
