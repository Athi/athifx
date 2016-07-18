package com.github.athi.athifx.test.views.window;

import com.github.athi.athifx.gui.window.AWindow;
import javafx.beans.NamedArg;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Created by Athi
 */
public class ItemOneTestWindow extends AWindow {

    public ItemOneTestWindow(@NamedArg("width") double width, @NamedArg("height") double height) {
        super(width, height);
        setResizable(true);

        setContent(new VBox(new Label("TES")));
    }
}
