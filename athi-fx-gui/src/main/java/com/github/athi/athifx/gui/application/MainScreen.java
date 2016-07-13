package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.menu.Menu;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by Athi
 */
class MainScreen extends AbstractScreen {

    @Inject
    private Menu menu;

    private NavigationPane navigationPane;
    private AnchorPane root;

    void show(Stage primaryStage, NavigationPane navigationPane) {
        this.navigationPane = navigationPane;

        primaryStage.setMaximized(true);
        root = prepareRoot(primaryStage);
        root.setPrefWidth(800);
        root.setPrefHeight(600);

        if (menu.hasItems()) {
            showWithMenu();
        } else {
            showWithoutMenu();
        }

        primaryStage.show();
    }

    private void showWithMenu() {
        AnchorPane leftAnchorPane = new AnchorPane();

        ImageView imageView = new ImageView(new Image(Resources.getResource("application_title.jpg").toExternalForm())); // TODO title image
        imageView.setFitHeight(68);

        leftAnchorPane.getChildren().addAll(imageView, menu);
        root.getChildren().addAll(leftAnchorPane, navigationPane);
        setAnchors(imageView, 2.0, 2.0, 2.0, null);
        setAnchors(menu, 2.0, 72.0, 2.0, 2.0);

        setAnchors(leftAnchorPane, 2.0, 2.0, null, 2.0);
        setAnchors(navigationPane, 210.0, 2.0, 2.0, 2.0);
    }

    private void showWithoutMenu() {
        root.getChildren().add(navigationPane);
        setAnchors(navigationPane, 2.0);
    }
}
