package com.github.athi.athifx.gui.menu;

import com.github.athi.athifx.gui.configuration.ApplicationConfiguration;
import com.github.athi.athifx.gui.configuration.AthiFXApplicationProperties;
import com.github.athi.athifx.gui.menu.group.AMenuGroup;
import com.github.athi.athifx.gui.menu.group.Group;
import com.github.athi.athifx.gui.menu.item.AMenuItem;
import com.github.athi.athifx.gui.menu.item.DisableEnableAMenuItemEvent;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import com.github.athi.athifx.gui.notification.Notification;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
public class Menu extends VBox {

    @Inject
    private AthiFXApplicationProperties properties;

    @Inject
    private Navigator navigator;

    private boolean hasItems = false;

    @PostConstruct
    private void initMenu() {
        setPrefWidth(200);
        setPadding(Insets.EMPTY);

        List<Item> items = properties.getItems();
        List<Group> groups = properties.getGroups()
                .stream()
                .sorted((g1, g2) -> Long.compare(g1.id(), g2.id()))
                .collect(Collectors.toList());

        hasItems = items.stream().anyMatch(item -> Objects.nonNull(item.group()));

        if (hasItems) {
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

    @Subscribe
    public void onDisableEnableAMenuItemEvent(DisableEnableAMenuItemEvent event) {
        try {
            String itemId = event.getItem().itemId();
            Node lookupNode = this.lookup("#" + itemId);
            lookupNode.setDisable(!lookupNode.isDisabled());
        } catch (Exception e) {
            Notification.error(ApplicationConfiguration.VIEW_DOES_NOT_EXIST_MESSAGE,
                    ApplicationConfiguration.CANT_FIND_VIEW_MESSAGE + event.getItem().caption());
        }
    }

    public boolean hasItems() {
        return hasItems;
    }
}
