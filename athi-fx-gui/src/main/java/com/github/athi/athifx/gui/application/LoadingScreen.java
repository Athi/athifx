package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
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

    private Stage loadingStage;

    private Label errorLabel;

    void show() {
        loadingStage = new Stage();
        loadingStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane root = prepareRoot(loadingStage);
        root.setPrefWidth(400);
        root.setPrefHeight(40);

        ProgressBar progressBar = new ProgressBar();
        Label infoLabel = new Label(ApplicationConfiguration.LOADING_APPLICATION_MESSAGE);
        errorLabel = new Label();

        Button button = new Button();
        button.setStyle("-fx-background-color: transparent;");
        button.setGraphic(FontAwesome.labelIcon(FontAwesome.CLOSE_ALIAS, 26));

        button.setOnAction(event -> Platform.exit());
        root.getChildren().addAll(progressBar, infoLabel, errorLabel, button);

        setAnchors(progressBar, 2.0, 2.0, 2.0, 2.0);
        setAnchors(infoLabel, 8.0, 2.0, 22.0, 22.0);
        setAnchors(errorLabel, 8.0, 32.0, 22.0, 2.0);
        setAnchors(button, 396.0, 2.0, 2.0, 22.0);

        loadingStage.show();
    }

    void close() {
        if (loadingStage.isShowing()) {
            loadingStage.close();
        }
    }

    void setError(String error) {
        errorLabel.setText(error);
    }

}
