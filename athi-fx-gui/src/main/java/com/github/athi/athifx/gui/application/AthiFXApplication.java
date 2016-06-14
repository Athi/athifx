package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.github.athi.athifx.gui.notification.Notification;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Created by Athi
 */
public class AthiFXApplication extends Application {

    private static final Log LOGGER = Log.getLogger(AthiFXApplication.class);

    @Inject
    private AthiFXApplicationProperties applicationConfiguration;

    @Inject
    private NavigationPane navigationPane;

    @Inject
    private MainScreen mainScreen;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Thread.currentThread().setUncaughtExceptionHandler((thread, throwable) -> {
            LOGGER.error(throwable.getMessage(), throwable);
            Notification.error("An unexpected error occurred ...", throwable.getMessage());
        });

        LoadingScreen.show();

        new Thread(() -> {
            AthiFXInjector.createInjector(this);
            Platform.runLater(() -> {
                LoadingScreen.close();
                mainScreen.show(primaryStage, navigationPane);
                navigationPane.setViewAsContent(applicationConfiguration.getViews().get(1L));
            });
        }).start();
    }
}
