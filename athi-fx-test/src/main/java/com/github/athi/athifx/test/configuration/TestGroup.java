package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.menu.group.Group;

/**
 * Created by Athi
 */
enum TestGroup implements Group {
    GROUP_ONE(1, "GROUP_ONE"),
    GROUP_TWO(2, "GROUP_TWO");

    private long id;
    private String caption;

    TestGroup(long id, String caption) {
        this.id = id;
        this.caption = caption;
    }

    public long id() {
        return id;
    }

    public String caption() {
        return caption;
    }
}
