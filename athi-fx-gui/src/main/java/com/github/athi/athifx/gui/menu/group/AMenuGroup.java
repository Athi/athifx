package com.github.athi.athifx.gui.menu.group;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.item.AMenuItem;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * Created by Athi
 */
public class AMenuGroup extends TitledPane {

    public AMenuGroup(Group group, List<AMenuItem> items) {
        setPadding(Insets.EMPTY);

        setId(group.itemId());
        setText(group.caption());
        setGraphic(FontAwesome.labelIcon(group.icon(), 16));

        VBox groupContent = new VBox();
        groupContent.setPadding(Insets.EMPTY);
        items.forEach(item -> groupContent.getChildren().add(((Node) item))); //TODO order by id??
        setContent(groupContent);
    }
}
