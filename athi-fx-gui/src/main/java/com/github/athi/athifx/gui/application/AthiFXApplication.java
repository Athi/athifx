package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.github.athi.athifx.gui.configuration.ApplicationConfigurationException;
import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.github.athi.athifx.gui.notification.Notification;
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
    private NavigationPane navigationPane;

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

        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            LOGGER.error(throwable.getMessage(), throwable);
            Notification.error(ApplicationConfiguration.UNCAUGHT_EXCEPTION_HANDLER_NOTIFICATION_MESSAGE, throwable.getMessage());
        });

        LoadingScreen.show();
        new Thread(() -> {
            try {
                AthiFXInjector.createInjector(this, ApplicationConfiguration.INJECTOR_CONFIGURATION);
                Platform.runLater(() -> {
                    LoadingScreen.close();
                    mainScreen.show(primaryStage, navigationPane);
                    navigationPane.setViewAsContent(applicationProperties.getViews().get(1L));
                });
            } catch (Exception e) {
                Platform.runLater(() -> {
                    LOGGER.error(e.getMessage(), e);
                    LoadingScreen.setError(ErrorParser.parse(e));
                });
            }
        }).start();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
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
