package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.notification.Notification;
import com.github.athi.athifx.gui.util.DefaultResources;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.APPLICATION_TITLE;
import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.ICON_RESOURCE_PATH;

/**
 * Created by Athi
 */
abstract class AbstractScreen {

    static AnchorPane prepareRoot(Stage primaryStage) {
        URL fontAwesomeFonts = DefaultResources.FONT_AWESOME_FONTS;
        URL fontAwesomeCSS = DefaultResources.FONT_AWESOME_CSS;
        URL notificationCSS = DefaultResources.NOTIFICATION_CSS;

        Font.loadFont(fontAwesomeFonts.toExternalForm(), 16);
        Notification.setStage(primaryStage);

        primaryStage.setTitle(APPLICATION_TITLE);
        setApplicationIcon(primaryStage);

        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(fontAwesomeCSS.toExternalForm());
        scene.getStylesheets().add(notificationCSS.toExternalForm());

        primaryStage.setScene(scene);
        return root;
    }

    static void setAnchors(Node child, Double value) {
        setAnchors(child, value, value, value, value);
    }

    static void setAnchors(Node child, Double left, Double top, Double right, Double bottom) {
        setVAnchors(child, top, bottom);
        setHAnchors(child, left, right);
    }

    static void setVAnchors(Node child, Double top, Double bottom) {
        AnchorPane.setTopAnchor(child, top);
        AnchorPane.setBottomAnchor(child, bottom);
    }

    static void setHAnchors(Node child, Double left, Double right) {
        AnchorPane.setLeftAnchor(child, left);
        AnchorPane.setRightAnchor(child, right);
    }

    private static void setApplicationIcon(Stage primaryStage) {
        String iconResourcePath = ICON_RESOURCE_PATH;
        if (Objects.nonNull(iconResourcePath)) {
            Image applicationIcon = new Image(iconResourcePath);
            primaryStage.getIcons().add(applicationIcon);
        }
    }
}
