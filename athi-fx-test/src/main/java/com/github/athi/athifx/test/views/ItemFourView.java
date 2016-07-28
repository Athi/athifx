package com.github.athi.athifx.test.views;

import com.github.athi.athifx.gui.menu.item.DisableEnableAMenuItemEvent;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.test.configuration.TestItems;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;

/**
 * Created by Athi
 */
@View(itemId = 4)
public class ItemFourView extends AView {

    private static final String VIEW_NAME = ItemFourView.class.getSimpleName();

    @Inject
    private EventBus eventBus;

    @PostConstruct
    public void initItemFourView() {
        getChildren().add(new Label(VIEW_NAME));

        Button disableButton = new Button("Disable/Enable item3");
        disableButton.setOnAction(event -> eventBus.post(new DisableEnableAMenuItemEvent(TestItems.ITEM_FIVE)));

        this.getChildren().addAll(disableButton);
    }

    @Override
    public void enter() {
        System.out.println("ENTER: " + VIEW_NAME);
    }

}
