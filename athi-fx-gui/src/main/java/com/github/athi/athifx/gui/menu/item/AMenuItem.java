package com.github.athi.athifx.gui.menu.item;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Created by Athi
 */
public class AMenuItem extends Button {

    public AMenuItem(Item item, Navigator navigator) {
        setPrefWidth(200);
        prefHeight(20);

        setAlignment(Pos.CENTER_LEFT);

        setText(item.caption());
        setId(item.itemId());
        setOnAction(event -> navigator.navigateTo(item));
        setGraphic(FontAwesome.labelIcon(item.icon(), 16));
    }
}
