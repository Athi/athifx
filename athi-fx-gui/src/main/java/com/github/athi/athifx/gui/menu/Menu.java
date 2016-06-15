package com.github.athi.athifx.gui.menu;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.menu.group.AMenuGroup;
import com.github.athi.athifx.gui.menu.group.Group;
import com.github.athi.athifx.gui.menu.item.AMenuItem;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.google.inject.Inject;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
public class Menu extends VBox {

    @Inject
    private AthiFXApplicationProperties properties;

    @Inject
    private Navigator navigator;

    @PostConstruct
    private void initMenu() {
        setPrefWidth(200);
        setPadding(Insets.EMPTY);

        List<Item> items = properties.getItems();
        List<Group> groups = properties.getGroups()
                .stream()
                .sorted((g1, g2) -> Long.compare(g1.id(), g2.id()))
                .collect(Collectors.toList());

        for (Group group : groups) {
            List<AMenuItem> groupItems = items.stream()
                    .filter(item -> item.group() == group)
                    .sorted((i1, i2) -> Long.compare(i1.id(), i2.id()))
                    .map(item -> new AMenuItem(item, navigator))
                    .collect(Collectors.toList());
            if (!groupItems.isEmpty()) {
                getChildren().add(new AMenuGroup(group, groupItems));
            }
        }
    }
}
