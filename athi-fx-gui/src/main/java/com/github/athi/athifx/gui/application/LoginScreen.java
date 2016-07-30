package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Athi
 */
class LoginScreen extends AbstractScreen {

    private Stage loginStage;

    private TextField loginTextField;
    private PasswordField passwordField;
    private Label errorLabel;

    void show(Runnable afterLogin) {
        AnchorPane root = initStage();

        VBox loginBox = createLoginBox();
        VBox passwordBox = createPasswordBox();

        errorLabel = initErrorLabel();

        Button loginButton = createLoginButton(afterLogin);
        Button closeButton = createCloseButton();

        root.getChildren().addAll(loginBox, passwordBox, errorLabel, loginButton, closeButton);

        setAnchors(loginBox, 4.0, 2.0, 4.0, 138.0);
        setAnchors(passwordBox, 4.0, 54.0, 4.0, 86.0);
        setAnchors(errorLabel, 4.0, 106.0, 4.0, 44.0);
        setAnchors(loginButton, 4.0, 138.0, 200.0, 2.0);
        setAnchors(closeButton, 200.0, 138.0, 4.0, 2.0);

        loginStage.show();
    }

    private AnchorPane initStage() {
        loginStage = new Stage();

        AnchorPane root = prepareRoot(loginStage);
        root.setPrefWidth(300);
        root.setPrefHeight(195);

        loginStage.setResizable(false);
        loginStage.setMaxWidth(300);
        loginStage.setMaxHeight(195);
        loginStage.setTitle("Login"); //TODO text from configuration
        return root;
    }

    private VBox createLoginBox() {
        VBox loginBox = new VBox();
        Node userIcon = FontAwesome.labelIcon(FontAwesome.USER);
        Label caption = new Label("Login", userIcon); //TODO text from configuration
        loginTextField = new TextField();
        loginBox.getChildren().addAll(caption, loginTextField);
        return loginBox;
    }

    private VBox createPasswordBox() {
        VBox passwordBox = new VBox();
        Node keyIcon = FontAwesome.labelIcon(FontAwesome.KEY);
        Label caption = new Label("Password", keyIcon); //TODO text from configuration
        passwordField = new PasswordField();
        passwordBox.getChildren().addAll(caption, passwordField);
        return passwordBox;
    }

    private Label initErrorLabel() {
        Label label = new Label("Login to continue", FontAwesome.labelIcon(FontAwesome.INFO_CIRCLE));
        label.setStyle("-fx-text-fill: red;");
        return label;
    }

    private Button createLoginButton(Runnable afterLogin) {
        Button loginButton = new Button("Login", FontAwesome.labelIcon(FontAwesome.CHECK_CIRCLE_O)); //TODO text from configuration
        loginButton.setOnAction(event -> {
            loginStage.close();
            Platform.runLater(afterLogin);
        });
        return loginButton;
    }

    private Button createCloseButton() {
        Button closeButton = new Button("Close", FontAwesome.labelIcon(FontAwesome.TIMES)); //TODO text from configuration
        closeButton.setOnAction(event -> loginStage.close());
        return closeButton;
    }

}
