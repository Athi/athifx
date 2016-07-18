package com.github.athi.athifx.gui.configuration;

import com.github.athi.athifx.injector.configuration.InjectorConfiguration;

/**
 * Created by Athi
 */
public abstract class ApplicationConfiguration {

    public abstract void init();

    public static String ICON_RESOURCE_PATH;
    public static String LOGO_RESOURCE_PATH;

    public static InjectorConfiguration INJECTOR_CONFIGURATION = new InjectorConfiguration();

    public static String APPLICATION_TITLE = "Application";
    public static String UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE = "An unexpected error occurred ...";
    public static String LOADING_APPLICATION_MESSAGE = "Loading application...";

    public static String VIEW_DOES_NOT_EXIST_MESSAGE = "View does not exist!";
    public static String CANT_FIND_VIEW_MESSAGE = "Cant find view: ";

    public static String ERROR_CAPTION = "Error: ";

}
