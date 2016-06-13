package com.github.athi.athifx.gui.application;

import com.google.common.io.Resources;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.net.URL;

/**
 * Created by Athi
 */
class AbstractScreen {

    static AnchorPane prepareRoot(Stage primaryStage) {
        URL fontAwesomeFonts = Resources.getResource("fonts/fontawesome-webfont.ttf");
        URL fontAwesomeCSS = Resources.getResource("css/fontawesome-webfont.css");

        Font.loadFont(fontAwesomeFonts.toExternalForm(), 16);

        AnchorPane root = new AnchorPane();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(fontAwesomeCSS.toExternalForm());

        primaryStage.setTitle("Application title");
//        primaryStage.getIcons().add(new Image("")); //TODO
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
}
