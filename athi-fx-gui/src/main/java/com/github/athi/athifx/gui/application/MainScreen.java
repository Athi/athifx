package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.menu.Menu;
import com.github.athi.athifx.gui.navigation.navigator.NavigationPane;
import com.google.common.io.Resources;
import com.google.inject.Inject;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.Objects;

import static com.github.athi.athifx.gui.configuration.ApplicationConfiguration.LOGO_RESOURCE_PATH;

/**
 * Created by Athi
 */
class MainScreen extends AbstractScreen {

    @Inject
    private Menu menu;

    @Inject
    private NavigationPane navigationPane;

    private AnchorPane root;

    void show(Stage primaryStage) {
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

        String logoResourcePath = LOGO_RESOURCE_PATH;
        if (Objects.nonNull(logoResourcePath)) {
            ImageView logo = initLogo(logoResourcePath);
            leftAnchorPane.getChildren().addAll(logo, menu);
            setAnchors(logo, 2.0, 2.0, 2.0, null);
            setAnchors(menu, 2.0, 72.0, 2.0, 2.0);
        } else {
            leftAnchorPane.getChildren().add(menu);
            setAnchors(menu, 2.0, 2.0, 2.0, 2.0);
        }

        root.getChildren().addAll(leftAnchorPane, navigationPane);

        setAnchors(leftAnchorPane, 2.0, 2.0, null, 2.0);
        setAnchors(navigationPane, 210.0, 2.0, 2.0, 2.0);
    }

    private ImageView initLogo(String logoResourcePath) {
        Image applicationLogo = new Image(Resources.getResource(logoResourcePath).toExternalForm());
        ImageView imageView = new ImageView(applicationLogo);
        imageView.setFitHeight(68);
        return imageView;
    }

    private void showWithoutMenu() {
        root.getChildren().add(navigationPane);
        setAnchors(navigationPane, 2.0);
    }


}
