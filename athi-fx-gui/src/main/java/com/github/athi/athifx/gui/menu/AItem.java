package com.github.athi.athifx.gui.menu;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Created by mp2
 */
public class AItem extends Button {

    public static final double DEFAULT_HEIGHT = 20;

    private Navigator navigator;

    private Item item;

    public AItem(Item item) {
        this.item = item;

        setPrefWidth(AMenu.DEFAULT_LARGE_WIDTH);
        setPrefHeight(DEFAULT_HEIGHT);

        setAlignment(Pos.CENTER_LEFT);

        setText(item.caption());
        setId(item.itemId());
        setOnAction(event -> navigator.navigateTo(item));
        setGraphic(FontAwesome.labelIcon(item.icon(), 16));
    }

    public Item getItem() {
        return this.item;
    }

    protected void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }
}
