package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.github.athi.athifx.injector.configuration.InjectorConfiguration;

/**
 * Created by Athi
 */
public class TestConfig extends ApplicationConfiguration {

    @Override
    public void init() {
        ICON_RESOURCE_PATH = "styles/icon.jpg";
        LOGO_RESOURCE_PATH = "styles/logo.jpg";

        INJECTOR_CONFIGURATION = new InjectorConfiguration();

        APPLICATION_TITLE = "TestApplicationName";
        UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE = "Wystąpił nieoczekiwany błąd...";
        LOADING_APPLICATION_MESSAGE = "Uruchamianie aplikacji...";

        VIEW_DOES_NOT_EXIST_MESSAGE = "Widok nie istnieje!";
        CANT_FIND_VIEW_MESSAGE = "Nie można znaleść widoku: ";
    }
}
