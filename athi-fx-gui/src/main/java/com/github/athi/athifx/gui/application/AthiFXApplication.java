package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Athi
 */
public class AthiFXApplication extends Application {

    @Inject
    private AthiFXApplicationProperties applicationConfiguration;

    @Inject
    private NavigationPane navigationPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoadingScreen.show(primaryStage);

        new Thread(() -> {
            AthiFXInjector.createInjector(this);
            Platform.runLater(() -> {
                closeScreenIfShowing(primaryStage);
                MainScreen.show(primaryStage, navigationPane);
                navigationPane.setViewAsContent(applicationConfiguration.getViews().get(1L));
            });
        }).start();
    }

    private void closeScreenIfShowing(Stage primaryStage) {
        if (primaryStage.isShowing()) {
            primaryStage.close();
        }
    }
}
