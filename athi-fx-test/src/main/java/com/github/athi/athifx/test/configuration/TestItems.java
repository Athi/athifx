package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.menu.item.Item;

/**
 * Created by Athi
 */
public enum TestItems implements Item<TestGroup> {
    ITEM_ONE(1, "ITEM_ONE", TestGroup.GROUP_ONE),
    ITEM_TWO(2, "ITEM_TWO", TestGroup.GROUP_ONE),
    ITEM_THREE(3, "ITEM_THREE", TestGroup.GROUP_TWO),
    ITEM_FOUR(4, "ITEM_FOUR", TestGroup.GROUP_TWO),
    ITEM_FIVE(5, "ITEM_FIVE");

    private long id;
    private String caption;
    private TestGroup group;

    TestItems(long id, String caption) {
        this(id, caption, null);
    }

    TestItems(long id, String caption, TestGroup group) {
        this.id = id;
        this.caption = caption;
        this.group = group;
    }

    public long id() {
        return id;
    }

    public String caption() {
        return caption;
    }

    public TestGroup group() {
        return group;
    }
}
