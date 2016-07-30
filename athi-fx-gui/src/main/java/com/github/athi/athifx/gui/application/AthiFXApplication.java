package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.github.athi.athifx.gui.configuration.ApplicationConfigurationException;
import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.notification.Notification;
import com.github.athi.athifx.gui.security.Security;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by Athi
 */
public class AthiFXApplication extends Application {

    private static final Log LOGGER = Log.getLogger(AthiFXApplication.class);

    @Inject
    private AthiFXApplicationProperties applicationProperties;

    @Inject
    private Navigator navigator;

    private LoadingScreen loadingScreen;

    @Inject
    private LoginScreen loginScreen;

    @Inject
    private MainScreen mainScreen;

    private static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;

        initDefaultApplicationConfiguration();
        initUncaughtExceptionHandler();

        loadingScreen = new LoadingScreen();
        loadingScreen.show();

        new Thread(() -> {
            try {
                AthiFXInjector.createInjector(this, ApplicationConfiguration.INJECTOR_CONFIGURATION);
                showApplication();
            } catch (Exception e) {
                Platform.runLater(() -> {
                    LOGGER.error(e.getMessage(), e);
                    loadingScreen.setError(ErrorParser.parse(e));
                });
            }
        }).start();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    private void showApplication() {
        Set<Class<? extends Security>> subTypesOf = AthiFXInjector.getReflections().getSubTypesOf(Security.class);

        if (subTypesOf.isEmpty()) {
            withoutSecurity();
        } else {
            withSecurity();
        }
    }

    private void withoutSecurity() {
        Platform.runLater(() -> {
            loadingScreen.close();
            showMainScreen();
        });
    }

    private void withSecurity() {
        Platform.runLater(() -> {
            loadingScreen.close();
            loginScreen.show(this::showMainScreen);
        });
    }

    private void showMainScreen() {
        mainScreen.show(primaryStage);
        navigator.navigateTo(applicationProperties.getItems().get(0));
    }

    private void initUncaughtExceptionHandler() {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            LOGGER.error(throwable.getMessage(), throwable);
            Notification.error(ApplicationConfiguration.UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE, throwable.getMessage());
        });
    }

    private void initDefaultApplicationConfiguration() {
        try {
            Reflections reflections = AthiFXInjector.getReflections();
            Set<Class<? extends ApplicationConfiguration>> configuration = reflections.getSubTypesOf(ApplicationConfiguration.class);
            if (configuration.size() == 1) {
                configuration.iterator().next().newInstance().init();
            } else if (configuration.isEmpty()) {
                throw new ApplicationConfigurationException("To many application configuration implementations!!");
            } else {
                throw new ApplicationConfigurationException("No application configuration implementations!!");
            }
        } catch (Exception e) {
            throw new ApplicationConfigurationException("Application configuration exception: " + e.getMessage(), e);
        }
    }
}
