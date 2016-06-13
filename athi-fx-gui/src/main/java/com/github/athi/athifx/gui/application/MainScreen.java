package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.menu.Menu;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Athi
 */
class MainScreen extends AbstractScreen {

    static void show(Stage primaryStage, NavigationPane navigationPane) {
        primaryStage.setMaximized(true);
        AnchorPane root = prepareRoot(primaryStage);
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        Menu menu = new Menu();
        root.getChildren().addAll(menu, navigationPane);

        setAnchors(menu, 2.0, 2.0, null, 2.0);
        setAnchors(navigationPane, 160.0, 2.0, 2.0, 2.0);

        primaryStage.show();
    }
}
