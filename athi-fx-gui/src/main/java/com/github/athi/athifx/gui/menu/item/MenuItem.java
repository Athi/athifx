package com.github.athi.athifx.gui.menu.item;

import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.google.inject.Inject;
import javafx.scene.control.Button;

/**
 * Created by Athi
 */
public class MenuItem extends Button {

    @Inject
    private Navigator navigator;

    public MenuItem(Item item) {
        //TODO icon??
        setText(item.caption());


        setOnAction(event -> navigator.navigateTo(item));
    }
}
