package com.github.athi.athifx.gui.notification;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by Athi
 */
public final class Notification {

    private static final double WIDTH = 300;
    private static final double HEIGHT = 80;

    private static final Duration ANIMATION_TIME = Duration.millis(500);
    private static final Duration DURATION = Duration.millis(5000);

    public static final String ERROR_ICON_COLOR = "red";
    public static final String WARNING_ICON_COLOR = "yellow";
    public static final String INFO_ICON_COLOR = "blue";
    public static final String SUCCESS_ICON_COLOR = "green";
    
    public static final String TITLE_STYLE = "title";
    public static final String MESSAGE_STYLE = "message";
    public static final String NOTIFICATION_STYLE = "notification";

    private static Stage stage;

    /**
     * Show ERROR Notification
     */
    public static void error(String title, String message) {
        notify(title, message, FontAwesome.TIMES_CIRCLE, ERROR_ICON_COLOR);
    }


    /**
     * Show WARNING Notification
     */
    public static void warning(String title, String message) {
        notify(title, message, FontAwesome.EXCLAMATION_TRIANGLE, WARNING_ICON_COLOR);
    }

    /**
     * Show INFO Notification
     */
    public static void info(String title, String message) {
        notify(title, message, FontAwesome.INFO_CIRCLE, INFO_ICON_COLOR);
    }

    /**
     * Show SUCCESS Notification
     */
    public static void success(String title, String message) {
        notify(title, message, FontAwesome.CHECK_CIRCLE, SUCCESS_ICON_COLOR);
    }

    public static void setStage(Stage stage) {
        Notification.stage = stage;
    }

    private static void notify(String title, String message, FontAwesome iconFont, String color) {
        showPopup(title, message, iconFont, color);
    }

    private static void showPopup(String title, String message, FontAwesome iconFont, String color) {
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add(TITLE_STYLE);

        Label icon = FontAwesome.labelIcon(iconFont, 24, color);
        icon.setTextFill(Color.RED);

        Label messageLabel = new Label(message, icon);
        messageLabel.getStyleClass().add(MESSAGE_STYLE);

        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.getChildren().addAll(titleLabel, messageLabel);

        StackPane popupContent = new StackPane();
        popupContent.setPrefSize(WIDTH, HEIGHT);
        popupContent.getStyleClass().add(NOTIFICATION_STYLE);
        popupContent.getChildren().addAll(popupLayout);

        Popup popup = new Popup();
        popup.setX(stage.getX() + (stage.getWidth() - WIDTH) * 0.5);
        popup.setY(stage.getY() + (stage.getHeight() - HEIGHT) / 2 - 25);
        popup.getContent().add(popupContent);
        popup.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> popup.hide());

        KeyValue fadeOutBegin = new KeyValue(popup.opacityProperty(), 1.0);
        KeyValue fadeOutEnd = new KeyValue(popup.opacityProperty(), 0.0);

        KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
        KeyFrame kfEnd = new KeyFrame(ANIMATION_TIME, fadeOutEnd);

        Timeline timeline = new Timeline(kfBegin, kfEnd);
        timeline.setDelay(DURATION);
        timeline.setOnFinished(actionEvent -> Platform.runLater(popup::hide));

        popup.show(stage);
        timeline.play();
    }

}
