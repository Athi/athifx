package com.github.athi.athifx.test.views;

import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.google.inject.Inject;
import javafx.scene.control.Label;

/**
 * Created by Athi
 */
@View(itemId = 1)
public class ItemOneView extends AView {

    private static final String VIEW_NAME = ItemOneView.class.getSimpleName();

    @Inject
    private Navigator navigator;

    public ItemOneView() {
        getChildren().add(new Label(VIEW_NAME));
    }

    @Override
    public void enter() {
        System.out.println("ENTER: " + VIEW_NAME);
    }

}
