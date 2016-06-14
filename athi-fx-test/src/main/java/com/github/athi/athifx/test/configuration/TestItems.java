package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.item.Item;

/**
 * Created by Athi
 */
public enum TestItems implements Item<TestGroup> {
    ITEM_ONE(1, "ITEM_ONE", FontAwesome.CALENDAR, TestGroup.GROUP_ONE),
    ITEM_TWO(2, "ITEM_TWO", FontAwesome.CAMERA, TestGroup.GROUP_ONE),
    ITEM_THREE(3, "ITEM_THREE", FontAwesome.CARET_SQUARE_O_DOWN, TestGroup.GROUP_TWO),
    ITEM_FOUR(4, "ITEM_FOUR", FontAwesome.ARROW_CIRCLE_DOWN, TestGroup.GROUP_TWO),
    ITEM_FIVE(5, "ITEM_FIVE", FontAwesome.SHOPPING_CART);

    private final long id;
    private final String caption;
    private final FontAwesome icon;
    private final TestGroup group;

    TestItems(long id, String caption, FontAwesome icon) {
        this(id, caption, icon, null);
    }

    TestItems(long id, String caption, FontAwesome icon, TestGroup group) {
        this.id = id;
        this.caption = caption;
        this.icon = icon;
        this.group = group;
    }

    @Override
    public long id() {
        return id;
    }

    @Override
    public String caption() {
        return caption;
    }

    @Override
    public FontAwesome icon() {
        return icon;
    }

    @Override
    public TestGroup group() {
        return group;
    }
}
