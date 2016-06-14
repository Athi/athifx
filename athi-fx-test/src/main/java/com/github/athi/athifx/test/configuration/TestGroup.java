package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.group.Group;

/**
 * Created by Athi
 */
enum TestGroup implements Group {
    GROUP_ONE(1, "GROUP_ONE", FontAwesome.ADJUST),
    GROUP_TWO(2, "GROUP_TWO", FontAwesome.ANGLE_DOUBLE_DOWN);

    private final long id;
    private final String caption;
    private final FontAwesome icon;

    TestGroup(long id, String caption, FontAwesome icon) {
        this.id = id;
        this.caption = caption;
        this.icon = icon;
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


}
