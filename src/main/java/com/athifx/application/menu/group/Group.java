package com.athifx.application.menu.group;

import com.athifx.application.menu.item.Item;

import java.util.List;

/**
 * Created by Athi
 */
public interface Group {

    long id();

    String caption();

    List<? extends Item> items();
}
