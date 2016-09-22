package com.github.athi.athifx.gui.menu.group;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.item.AItem;
import javafx.geometry.Insets;
import javafx.scene.control.TitledPane;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mp2
 */
public class AGroup extends TitledPane {

    private List<AItem> items = new LinkedList<>();

    private long groupId;
    private String caption;
    private FontAwesome icon;

    private AGroup(long groupId, String caption, FontAwesome icon) {
        setPadding(Insets.EMPTY);

        this.groupId = groupId;
        this.caption = caption;
        this.icon = icon;

        setId("AGroup" + groupId); //TODO ??
        setText(caption);
        setGraphic(FontAwesome.labelIcon(icon, 16));
    }

    public static class Builder {

        private long groupId;
        private String caption;
        private FontAwesome icon;

        public AGroup build() {

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


    public AItem getItem(long itemId) {
        return null; //TODO
    }

    public AItem addItem(AItem aItem) {
        return null; //TODO
    }

    public AGroup withItem(AItem aItem) {
        //TODO
        return this;
    }

    public AGroup withItems(AItem... aItems) {
        //TODO
        return this;
    }

    public List<AItem> getItems() {
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
}
