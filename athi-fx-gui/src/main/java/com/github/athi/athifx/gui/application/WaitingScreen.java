package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.WAITING_FOR_COMPLETION_MESSAGE;

/**
 * Created by Athi
 */
class WaitingScreen extends AbstractScreen {

    private Stage waitingScreen;

    void show(Task<?> task) {
        waitingScreen = new Stage();

        waitingScreen.initStyle(StageStyle.UNDECORATED);
        waitingScreen.setResizable(false);
        waitingScreen.initModality(Modality.APPLICATION_MODAL);

        AnchorPane root = prepareRoot(waitingScreen);
        root.setPrefWidth(400);
        root.setPrefHeight(40);

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(task.progressProperty());

        Label infoLabel = new Label(WAITING_FOR_COMPLETION_MESSAGE);

        root.getChildren().addAll(progressBar, infoLabel);

        setAnchors(progressBar, 2.0, 2.0, 2.0, 2.0);
        setAnchors(infoLabel, 8.0, 2.0, 2.0, 2.0);

        waitingScreen.show();
    }

    void close() {
        if (waitingScreen.isShowing()) {
            waitingScreen.close();
        }
    }

}