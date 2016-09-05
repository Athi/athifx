package com.github.athi.athifx.gui.configuration;

import com.github.athi.athifx.injector.configuration.InjectorConfiguration;

/**
 * Created by Athi
 */
public abstract class ApplicationConfiguration {

    public abstract void init();

    /**
     * GLOBAL PROPERTIES
     */
    public static String ICON_RESOURCE_PATH;
    public static String LOGO_RESOURCE_PATH;

    public static InjectorConfiguration INJECTOR_CONFIGURATION = new InjectorConfiguration();

    public static int FONT_SIZE = 16;

    /**
     * LOGIN SECURED WINDOW PROPERTIES
     **/
    public static String LOGIN_TITLE = "Login";
    public static String LOGIN_FIELD_CAPTION = "Login";
    public static String PASSWORD_FIELD_CAPTION = "Password";

    public static String DEFAULT_LOGIN_WINDOW_MESSAGE = "Login to continue";

    public static String LOGIN_BUTTON_CAPTION = "Login";
    public static String CLOSE_BUTTON_CAPTION = "Close";

    public static String EMPTY_LOGIN_MESSAGE = "Login field can't be empty.";
    public static String EMPTY_PASSWORD_MESSAGE = "Password field can't be empty.";

    /**
     * MAIN APPLICATION WINDOW PROPERTIES
     **/
    public static String APPLICATION_TITLE = "Application";
    public static String UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE = "An unexpected error occurred ...";
    public static String LOADING_APPLICATION_MESSAGE = "Loading application...";
    public static String WAITING_FOR_COMPLETION_MESSAGE = "Please wait action in progress...";

    public static String VIEW_DOES_NOT_EXIST_MESSAGE = "View does not exist!";
    public static String NO_PERMISSION_FOR_VIEW_WITH_ID = "No permission for view with id: ";
    public static String CANT_FIND_VIEW_MESSAGE = "Cant find view: ";

    public static String ERROR_CAPTION = "Error: ";

}
