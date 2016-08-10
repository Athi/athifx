package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.security.Security;
import com.github.athi.athifx.gui.security.SecurityException;
import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Inject;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;

import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.*;

/**
 * Created by Athi
 */
class LoginScreen extends AbstractScreen {

    private static final Log LOGGER = Log.getLogger(LoginScreen.class);

    @Inject
    @Any
    private Instance<Security> securityInstance;

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
        loginStage.setTitle(LOGIN_TITLE);
        return root;
    }

    private VBox createLoginBox() {
        VBox loginBox = new VBox();
        Node userIcon = FontAwesome.labelIcon(FontAwesome.USER);
        Label caption = new Label(LOGIN_FIELD_CAPTION, userIcon);
        loginTextField = new TextField();
        loginBox.getChildren().addAll(caption, loginTextField);
        return loginBox;
    }

    private VBox createPasswordBox() {
        VBox passwordBox = new VBox();
        Node keyIcon = FontAwesome.labelIcon(FontAwesome.KEY);
        Label caption = new Label(PASSWORD_FIELD_CAPTION, keyIcon);
        passwordField = new PasswordField();
        passwordBox.getChildren().addAll(caption, passwordField);
        return passwordBox;
    }

    private Label initErrorLabel() { // TODO make improvements (what if to long error message ?? wrap and bigger window or scrollable label?)
        Label label = new Label(DEFAULT_LOGIN_WINDOW_MESSAGE, FontAwesome.labelIcon(FontAwesome.INFO_CIRCLE));
        label.setStyle("-fx-text-fill: red;");
        return label;
    }

    private Button createLoginButton(Runnable afterLogin) {
        Button loginButton = new Button(LOGIN_BUTTON_CAPTION, FontAwesome.labelIcon(FontAwesome.CHECK_CIRCLE_O));
        loginButton.setOnAction(event -> {
            Security next = securityInstance.iterator().next();
            try {
                validateLoginAndPasswordFields();
                next.login(loginTextField.getText(), passwordField.getText());
                loginStage.close();
                Platform.runLater(afterLogin);
            } catch (SecurityException e) {
                LOGGER.info(e.getMessage());
                Platform.runLater(() -> errorLabel.setText(e.getMessage()));
            }

        });
        return loginButton;
    }

    private void validateLoginAndPasswordFields() throws SecurityException { //TODO make it better ??
        if (this.loginTextField.getText().trim().isEmpty()) {
            throw new SecurityException(EMPTY_LOGIN_MESSAGE);
        } else if (this.passwordField.getText().trim().isEmpty()) {
            throw new SecurityException(EMPTY_PASSWORD_MESSAGE);
        }
    }

    private Button createCloseButton() {
        Button closeButton = new Button(CLOSE_BUTTON_CAPTION, FontAwesome.labelIcon(FontAwesome.TIMES));
        closeButton.setOnAction(event -> loginStage.close());
        return closeButton;
    }

}
