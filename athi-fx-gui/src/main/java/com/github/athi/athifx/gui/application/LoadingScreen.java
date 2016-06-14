package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Athi
 */
class LoadingScreen extends AbstractScreen {

    private static final Stage loadingStage = new Stage();

    static void show() {
        loadingStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane root = prepareRoot(loadingStage);
        root.setPrefWidth(200);
        root.setPrefHeight(20);

        ProgressBar progressBar = new ProgressBar();
        Label label = new Label("Loading application...");

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(FontAwesome.labelIcon(FontAwesome.CLOSE_ALIAS, 26));

        button.setOnAction(event -> Platform.exit());
        root.getChildren().addAll(progressBar, label, button);

        setAnchors(progressBar, 2.0, 2.0, 2.0, 2.0);
        setAnchors(label, 8.0, 2.0, 102.0, 2.0);
        setAnchors(button, 196.0, 2.0, 2.0, 2.0);

        loadingStage.show();
    }

    static void close() {
        if (loadingStage.isShowing()) {
            loadingStage.close();
        }
    }

}
