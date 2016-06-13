package com.github.athi.athifx.test.views;

import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.test.configuration.TestItems;
import com.google.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * Created by Athi
 */
@View(itemId = 4)
public class ItemFourView extends AView {

    @Inject
    private Navigator navigator;

    public ItemFourView() {
        getChildren().add(new Label("ITEM FOUR VIEW"));
        Button test = new Button("Navigate to ItemOne");
        test.setOnAction(event -> navigator.navigateTo(TestItems.ITEM_ONE));
        this.getChildren().add(test);
    }

    @Override
    public void enter() {
        System.out.println("TEST ITEM FOUR ENTER");
    }

}
