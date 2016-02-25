package com.athifx.application;

import com.athifx.application.configuration.AthiFXApplicationConfiguration;
import com.athifx.application.guice.AthiFXInjector;
import com.athifx.application.menu.group.Groups;
import com.athifx.application.menu.item.Items;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.stage.Stage;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Athi
 */
public abstract class AthiFXApplication<GROUPS extends Enum<?> & Groups, ITEMS extends Enum<?> & Items> extends Application {

    @Inject
    private AthiFXApplicationConfiguration applicationConfiguration;

    @PostConstruct
    private void initialize() {
        //TODO its called before start!!!!
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AthiFXInjector.createInjector(this);


        List<GROUPS> groups = getGroups();
        List<ITEMS> items = getItems();

        primaryStage.show();
    }

    public abstract List<GROUPS> getGroups();

    public abstract List<ITEMS> getItems();

}
