package com.github.athi.athifx.gui.menu;

import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.TitledPane;

/**
 * Created by Athi
 */
public class Menu extends Accordion {

    public Menu() {
        setMinWidth(150);
        setHeight(800);

        getPanes().addAll(new TitledPane("Test1", new Button("Test1ITEM")), new TitledPane("Test2", new Button("Test2ITEM")));
    }
}
