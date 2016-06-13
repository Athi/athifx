package com.github.athi.athifx.gui.navigation.navigator;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.view.AView;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Athi
 */
@SessionScoped
public class Navigator implements Serializable {

    @Inject
    private AthiFXApplicationProperties properties;

    @Inject
    private NavigationPane navigationPane;

    public void navigateTo(Item item) {
        AView itemNode = getItemNode(item);
        navigationPane.setViewAsContent(itemNode);
    }

    private AView getItemNode(Item item) {
        return properties.getViews()
                .entrySet()
                .stream()
                .filter(entry -> Long.compare(entry.getKey(), item.id()) == 0)
                .findAny()
                .orElseThrow(RuntimeException::new) //TODO specific exception (Runtime?)
                .getValue();
    }
}
