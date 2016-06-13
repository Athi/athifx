package com.github.athi.athifx.gui;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.menu.Menu;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

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
        createApplicationLoadingScreen(primaryStage);
        Thread thread = new Thread(() -> {
            AthiFXInjector.createInjector(this);
            Platform.runLater(() -> {
                primaryStage.close();
                createMainApplicationScreen(primaryStage);
            });
        });
        thread.start();
    }

    private void createMainApplicationScreen(Stage primaryStage) {
        Font.loadFont(Thread.currentThread().getContextClassLoader().getResource("fonts/fontawesome-webfont.ttf").toExternalForm(), 12); // FIXME -- write it safer
        primaryStage.setMaximized(true);

        AnchorPane applicationContent = new AnchorPane();
        applicationContent.setPrefWidth(800);
        applicationContent.setPrefHeight(600);

        Scene value = new Scene(applicationContent);
        value.getStylesheets().add(Thread.currentThread().getContextClassLoader().getResource("css/fontawesome-webfont.css").toExternalForm()); // FIXME -- write it safer

        primaryStage.setScene(value);

        Menu menu = new Menu();
        applicationContent.getChildren().addAll(menu, navigationPane);

        AnchorPane.setLeftAnchor(menu, 2.0);
        AnchorPane.setTopAnchor(menu, 2.0);
        AnchorPane.setBottomAnchor(menu, 2.0);

        AnchorPane.setLeftAnchor(navigationPane, 160.0);
        AnchorPane.setTopAnchor(navigationPane, 2.0);
        AnchorPane.setBottomAnchor(navigationPane, 2.0);
        AnchorPane.setRightAnchor(navigationPane, 2.0);

        // ITEMS, GROUPS and VIEWS
        navigationPane.setViewAsContent(applicationConfiguration.getViews().get(1L));
        primaryStage.show();
    }

    private void createApplicationLoadingScreen(Stage primaryStage) {
        AnchorPane root = new AnchorPane();
        root.setPrefHeight(100);
        root.setPrefWidth(400);
        primaryStage.setScene(new Scene(root));

        Label label = new Label("Loading application ...."); // A PROGRESSBAR ETC ETC
        root.getChildren().add(label);
        AnchorPane.setLeftAnchor(label, 2.0);
        AnchorPane.setTopAnchor(label, 2.0);
        AnchorPane.setBottomAnchor(label, 2.0);
        AnchorPane.setRightAnchor(label, 2.0);
        primaryStage.show();
    }

}
