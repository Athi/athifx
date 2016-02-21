package com.athifx.application;

import com.athifx.application.configuration.AthiFXApplicationConfiguration;
import com.athifx.application.guice.AthiFXInjector;
import com.athifx.application.menu.group.Group;
import com.athifx.application.menu.item.Item;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;

/**
 * Created by Athi
 */
public abstract class AthiFXApplication extends Application {

    @Inject
    private AthiFXApplicationConfiguration applicationConfiguration;

    @PostConstruct
    private void initialize() {
        //TODO its called before start!!!!
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AthiFXInjector.createInjector(this);

        primaryStage.setTitle("Hello World");
        VBox root = new VBox();
        primaryStage.setScene(new Scene(root, 300, 275));


        System.out.println("VIEWS:" + applicationConfiguration.getViews());
        System.out.println("MENU_ITEMS:" + applicationConfiguration.getMenuItems());

        primaryStage.show();
    }

    //FIXME define an enum
    public abstract Class<? extends Group> getMenuGroupsDefinition();

    //FIXME define an enum
    public abstract Class<? extends Item> getMenuItemsDefinition();

}
