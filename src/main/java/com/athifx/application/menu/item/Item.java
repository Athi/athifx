package com.athifx.application.menu.item;

import com.athifx.application.menu.group.Group;

/**
 * Created by Athi
 */
public interface Item {

    long id();

    String caption();

    <T extends Group> T group();
}
