package com.github.athi.athifx.gui.menu;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.exception.AMenuConfigurationException;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mp2
 */
public class AGroup extends TitledPane {

    private Stream<AItem> items = Stream.empty();

    private Navigator navigator;

    private long groupId;
    private String caption;
    private FontAwesome icon;

    private AGroup(long groupId, String caption, FontAwesome icon) {
        setPadding(Insets.EMPTY);

        this.groupId = groupId;
        this.caption = caption;
        this.icon = icon;

        setText(Objects.nonNull(caption) ? caption : "");
        setGraphic(Objects.nonNull(icon) ? FontAwesome.labelIcon(icon, 16) : null);
    }

    public AItem getItem(long itemId) {
        return items.filter(aItem -> aItem.getItem().id() == itemId)
                .collect(Collectors.collectingAndThen(Collectors.toList(), singleAItemFunction(itemId)));
    }

    public AItem addItem(AItem newItem) {
        newItem.setNavigator(navigator);
        return items.filter(aItem -> aItem.getItem().id() == newItem.getItem().id())
                .collect(Collectors.collectingAndThen(Collectors.toList(), addAItemFunction(newItem)));
    }

    public Stream<AItem> getItems() {
        return this.items;
    }

    public long getGroupId() {
        return this.groupId;
    }

    public String getCaption() {
        return this.caption;
    }

    public FontAwesome getIcon() {
        return this.icon;
    }

    public static class Builder {

        private long groupId;
        private String caption;
        private FontAwesome icon;

        public AGroup build() {
            groupId = Optional.ofNullable(groupId).orElseThrow(() -> new AMenuConfigurationException("No id was defined for AGroup."));
            return new AGroup(groupId, caption, icon);
        }

        public Builder withGroupId(long groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withCaption(String caption) {
            this.caption = caption;
            return this;
        }

        public Builder withIcon(FontAwesome icon) {
            this.icon = icon;
            return this;
        }
    }

    protected void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    private Function<List<AItem>, AItem> addAItemFunction(AItem item) {
        return aItems -> {
            if (aItems.isEmpty()) {
                aItems.add(item);
                aItems.sort((i1, i2) -> Long.compare(i1.getItem().id(), i2.getItem().id()));
                return item;
            } else {
                throw new AMenuConfigurationException("Item with id: " + item.getItem().id() + " is already added to group.");
            }
        };
    }

    private Function<List<AItem>, AItem> singleAItemFunction(long itemIdToValidate) {
        return aItems -> {
            if (aItems.isEmpty()) {
                throw new AMenuConfigurationException("Item with id: " + itemIdToValidate + " does not exist.");
            } else if (aItems.size() == 1) {
                return aItems.get(0);
            } else {
                throw new AMenuConfigurationException("There are multiple items with id: " + itemIdToValidate);
            }
        };
    }
}
