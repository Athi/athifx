package com.github.athi.athifx.gui.window;

import com.github.athi.athifx.gui.application.AthiFXApplication;
import com.github.athi.athifx.gui.util.DefaultResources;
import javafx.beans.NamedArg;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by Athi
 */
public abstract class AWindow extends Stage {

    private VBox content;

    public AWindow(@NamedArg("width") double width, @NamedArg("height") double height) {
        initStyle(StageStyle.UNDECORATED);
        initModality(Modality.APPLICATION_MODAL);

        initOwner(AthiFXApplication.getPrimaryStage());

        content = new VBox();
        content.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(1))));
        content.setSpacing(10);
        content.setPadding(new Insets(20));

        Scene scene = new Scene(content, width, height);
        scene.getStylesheets().add(DefaultResources.FONT_AWESOME_CSS.toString());
        scene.getStylesheets().add(DefaultResources.NOTIFICATION_CSS.toString());

        setScene(scene);
    }

    public VBox getContent() {
        return content;
    }
}