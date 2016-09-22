package com.github.athi.athifx.gui.navigation.navigator;

import com.github.athi.athifx.gui.font_awesome.FontAwesome;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.view.AView;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by Athi
 */
@SessionScoped
public class NavigationPane extends AnchorPane implements Serializable {

    @PostConstruct
    private void initNavigationPane() {
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2.0))));
    }

    public void setViewAsContent(Item item, AView node) {
        getChildren().clear();

        Label caption = createCaptionLabel(item);

        node.enter();
        node.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2.0))));
        getChildren().addAll(caption, node);

        AnchorPane.setLeftAnchor(caption, 0.0);
        AnchorPane.setTopAnchor(caption, 0.0);
        AnchorPane.setRightAnchor(caption, 0.0);

        AnchorPane.setLeftAnchor(node, 2.0);
        AnchorPane.setTopAnchor(node, 25.0);
        AnchorPane.setBottomAnchor(node, 2.0);
        AnchorPane.setRightAnchor(node, 2.0);
    }

    private Label createCaptionLabel(Item item) { //TODO all styles need to be changed to css (for future styling possibilities)
        Label label = new Label();
        label.setStyle("-fx-background-color: dimgray;");
        label.setTextFill(Color.WHITE);
        label.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0.0, 0.0, 2.0, 0.0))));
        label.setGraphic(FontAwesome.labelIcon(item.icon(), 16, "white"));
        label.setText(item.caption());
        return label;
    }
}
