package com.github.athi.athifx.gui.menu.group;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;

/**
 * Created by Athi
 */
public interface Group {

    long id();

    String caption();

    FontAwesome icon();

    default String itemId() {
        return id() + "_" + caption();
    }
}
