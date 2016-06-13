package com.github.athi.athifx.gui.navigation.navigator;

import com.github.athi.athifx.gui.navigation.view.AView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

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
        setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(2))));
    }

    public void setViewAsContent(AView node) {
        getChildren().clear();
        node.enter();
        node.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.NONE, CornerRadii.EMPTY, new BorderWidths(2))));
        getChildren().add(node);

        AnchorPane.setLeftAnchor(node, 2.0);
        AnchorPane.setTopAnchor(node, 2.0);
        AnchorPane.setBottomAnchor(node, 2.0);
        AnchorPane.setRightAnchor(node, 2.0);
    }
}
