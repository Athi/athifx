package com.github.athi.athifx.gui.window;

import com.github.athi.athifx.gui.application.AthiFXApplication;
import com.github.athi.athifx.gui.util.DefaultResources;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Athi
 */
public class AWindow extends Stage {

    private AnchorPane anchorPane;

    public AWindow(String caption, double width, double height) {
        this(width, height);
        setTitle(caption);
    }

    public AWindow(double width, double height) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);

        initOwner(AthiFXApplication.getPrimaryStage());

        anchorPane = new AnchorPane();
        anchorPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        anchorPane.setPadding(new Insets(20));

        Scene scene = new Scene(anchorPane, width, height);
        scene.getStylesheets().add(DefaultResources.FONT_AWESOME_CSS.toString());
        scene.getStylesheets().add(DefaultResources.NOTIFICATION_CSS.toString());

        setScene(scene);

        setOnShown(event -> center());
    }

    public void setContent(Node content) {
        anchorPane.getChildren().add(content);
        AnchorPane.setBottomAnchor(content, 2d);
        AnchorPane.setLeftAnchor(content, 2d);
        AnchorPane.setTopAnchor(content, 2d);
        AnchorPane.setRightAnchor(content, 2d);
    }

    public void center() {
        Stage primaryStage = AthiFXApplication.getPrimaryStage();
        setX(primaryStage.getX() + (primaryStage.getWidth() / 2) - (this.getWidth() / 2));
        setY(primaryStage.getY() + (primaryStage.getHeight() / 2) - (this.getHeight() / 2));
    }

}