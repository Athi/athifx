package com.github.athi.athifx.gui.notification;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.stream.IntStream;

/**
 * Created by Athi
 */
public final class Notification {

    private static final Image INFO_ICON = null;//new Image(Notification.class.getResourceAsStream("info.png")); // TODO
    private static final Image WARNING_ICON = null;//new Image(Notification.class.getResourceAsStream("warning.png")); // TODO
    private static final Image SUCCESS_ICON = null;//new Image(Notification.class.getResourceAsStream("success.png")); // TODO
    private static final Image ERROR_ICON = null;//new Image(Notification.class.getResourceAsStream("error.png")); // TODO

    private static double notificationWidth = 300;
    private static double notificationHeight = 80;
    private static Pos popupLocation = Pos.TOP_RIGHT;

    private static final Duration popupAnimationTime = Duration.millis(500);
    private static Duration popupDuration = Duration.millis(5000);
    private static ObservableList<Popup> popups = FXCollections.observableArrayList();

    private static Stage stage;

    /**
     * Show ERROR Notification
     */
    public static void error(String title, String message) {
        notify(title, message, Notification.ERROR_ICON);
    }


    /**
     * Show WARNING Notification
     */
    public static void warning(String title, String message) {
        notify(title, message, Notification.WARNING_ICON);
    }

    /**
     * Show INFO Notification
     */
    public static void info(String title, String message) {
        notify(title, message, Notification.INFO_ICON);
    }

    /**
     * Show SUCCESS Notification
     */
    public static void success(String title, String message) {
        notify(title, message, Notification.SUCCESS_ICON);
    }

    public static void setStage(Stage stage) {
        Notification.stage = stage;
    }

    private static void notify(String title, String message, Image image) {
        orderNotifications();
        showPopup(title, message, image);
    }

    private static void orderNotifications() {
        if (popups.isEmpty()) return;
        IntStream.range(0, popups.size()).parallel().forEachOrdered(
                i -> {
                    switch (popupLocation) {
                        case TOP_LEFT:
                        case TOP_CENTER:
                        case TOP_RIGHT:
                            popups.get(i).setY(popups.get(i).getY() + notificationHeight + 5);
                            break;
                        case BOTTOM_LEFT:
                        case BOTTOM_CENTER:
                        case BOTTOM_RIGHT:
                            popups.get(i).setY(popups.get(i).getY() - notificationHeight - 5);
                            break;
                        default:
                            popups.get(i).setY(popups.get(i).getY() - notificationHeight - 5);
                            break;
                    }
                }
        );
    }

    private static void showPopup(String title, String message, Image image) {
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("title");

        ImageView icon = new ImageView(image);
        icon.setFitWidth(24);
        icon.setFitHeight(24);

        Label messageLabel = new Label(message, icon);
        messageLabel.getStyleClass().add("message");

        VBox popupLayout = new VBox();
        popupLayout.setSpacing(10);
        popupLayout.setPadding(new Insets(10, 10, 10, 10));
        popupLayout.getChildren().addAll(titleLabel, messageLabel);

        StackPane popupContent = new StackPane();
        popupContent.setPrefSize(notificationWidth, notificationHeight);
        popupContent.getStyleClass().add("notification");
        popupContent.getChildren().addAll(popupLayout);

        Popup popup = new Popup();
        popup.setX(getX());
        popup.setY(getY());
        popup.getContent().add(popupContent);
        popups.add(popup);

        // Add a timeline for popup fade out
        KeyValue fadeOutBegin = new KeyValue(popup.opacityProperty(), 1.0);
        KeyValue fadeOutEnd = new KeyValue(popup.opacityProperty(), 0.0);

        KeyFrame kfBegin = new KeyFrame(Duration.ZERO, fadeOutBegin);
        KeyFrame kfEnd = new KeyFrame(popupAnimationTime, fadeOutEnd);

        Timeline timeline = new Timeline(kfBegin, kfEnd);
        timeline.setDelay(popupDuration);
        timeline.setOnFinished(actionEvent -> Platform.runLater(() -> {
            popup.hide();
            popups.remove(popup);
        }));

        popup.show(stage);
        timeline.play();
    }

    private static double getX() {
        return calcX(stage.getX(), stage.getWidth());
    }

    private static double getY() {
        return calcY(stage.getY(), stage.getHeight());
    }

    private static double calcX(double left, double totalWidth) {
        switch (popupLocation) {
            case TOP_LEFT:
            case CENTER_LEFT:
            case BOTTOM_LEFT:
                return left;
            case TOP_CENTER:
            case CENTER:
            case BOTTOM_CENTER:
                return left + (totalWidth - notificationWidth) * 0.5;
            case TOP_RIGHT:
            case CENTER_RIGHT:
            case BOTTOM_RIGHT:
                return left + totalWidth - notificationWidth;
            default:
                return 0.0;
        }
    }

    private static double calcY(double top, double totalHeight) {
        switch (popupLocation) {
            case TOP_LEFT:
            case TOP_CENTER:
            case TOP_RIGHT:
                return top + 25;
            case CENTER_LEFT:
            case CENTER:
            case CENTER_RIGHT:
                return top + (totalHeight - notificationHeight) / 2 - 25;
            case BOTTOM_LEFT:
            case BOTTOM_CENTER:
            case BOTTOM_RIGHT:
                return top + totalHeight - notificationHeight - 25;
            default:
                return 0.0;
        }
    }

}
