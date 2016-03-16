package com.github.athi.athifx.gui.menu.item;


import com.github.athi.athifx.gui.menu.group.Groups;

/**
 * Created by Athi
 */
public interface Items<GROUP extends Enum<?> & Groups> {

    long id();

    String caption();

    GROUP group();
}
