package com.athifx.application.menu.item;

import com.athifx.application.menu.group.Groups;

/**
 * Created by Athi
 */
public interface Items<GROUP extends Enum<?> & Groups> {

    long id();

    String caption();

    GROUP group();
}
