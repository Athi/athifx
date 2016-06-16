package com.github.athi.athifx.gui.menu.item;

/**
 * Created by Athi
 */
public class DisableEnableAMenuItemEvent {

    private final Item item;

    public DisableEnableAMenuItemEvent(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
