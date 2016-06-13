package com.github.athi.athifx.test.views;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.test.configuration.TestItems;
import com.google.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

/**
 * Created by Athi
 */
@View(itemId = 1)
public class ItemOneView extends AView {

    @Inject
    private Navigator navigator;

    public ItemOneView() {
        getChildren().add(new Label("ITEM ONE VIEW"));
        getChildren().add(createIconLabel("\uf115", 16));
        getChildren().add(new TextField());
        Button test1 = new Button("Navigate1 to ItemFour");
        test1.setOnAction(event -> navigator.navigateTo(TestItems.ITEM_FOUR));
        Button test2 = new Button("Navigate2 to ItemFour");
        test2.setOnAction(event -> navigator.navigateTo(TestItems.ITEM_FOUR));
        Button test3 = new Button("Navigate3 to ItemFour");
        test3.setOnAction(event -> navigator.navigateTo(TestItems.ITEM_FOUR));

        Button test4 = createIconButton(FontAwesome.ICON_PLUS, "TEST3", 15);
        this.getChildren().add(new FlowPane(test1, test2, test3, test4)); // IMPORTANT FlowPane is horizontal and wraps content
    }

    public static Label createIconLabel(String iconName, int iconSize) {
        Label label = new Label(iconName);
        label.getStyleClass().add("icons");
        label.setStyle("-fx-font-size: " + iconSize + "px;");
        return label;
    }

    public static Button createIconButton(String iconName, String text, int iconSize) {
        Label icon = createIconLabel(iconName, iconSize);
        Button button = new Button();
        button.setText(text);
        button.setGraphic(icon);
        return button;
    }

    @Override
    public void enter() {
        System.out.println("TEST ITEM ONE ENTER");
    }

}
