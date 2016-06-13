package com.github.athi.athifx.gui.application;

import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Athi
 */
class LoadingScreen extends AbstractScreen {

    static void show(Stage primaryStage) {
        AnchorPane root = prepareRoot(primaryStage);
        root.setPrefWidth(400);
        root.setPrefHeight(100);

        Label label = new Label("Loading application ...."); // A PROGRESSBAR ETC ETC
        root.getChildren().add(label);

        setAnchors(label, 2.0);
        primaryStage.show();
    }

}
