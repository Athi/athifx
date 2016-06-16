package com.github.athi.athifx.test.views;

import com.github.athi.athifx.gui.menu.item.DisableEnableAMenuItemEvent;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.test.configuration.TestItems;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Athi
 */
@View(itemId = 4)
public class ItemFourView extends AView {

    @Inject
    private Navigator navigator;

    @Inject
    private EventBus eventBus;

    public ItemFourView() {
        getChildren().add(new Label("ITEM FOUR VIEW"));
        Button test = new Button("Navigate to ItemOne");
        test.setOnAction(event -> navigator.navigateTo(TestItems.ITEM_ONE));

        Button disableButton = new Button("Disable item3");
        disableButton.setOnAction(event -> eventBus.post(new DisableEnableAMenuItemEvent(TestItems.ITEM_FIVE)));

        this.getChildren().addAll(test, disableButton);
    }

    @Override
    public void enter() {
        System.out.println("TEST ITEM FOUR ENTER");
    }

}
