package com.github.athi.athifx.gui.menu.item;

import javafx.scene.control.Button;

/**
 * Created by mp2
 */
public class AItem extends Button {

    private long itemId;

    public long getItemId() {
        return this.itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }
}
