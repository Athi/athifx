package com.github.athi.athifx.gui;

import com.github.athi.athifx.gui.configuration.AthiFXApplicationConfiguration;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.stage.Stage;
import com.github.athi.athifx.gui.menu.group.Groups;
import com.github.athi.athifx.gui.menu.item.Items;
import com.github.athi.athifx.injector.injection.AthiFXInjector;

import java.util.List;

/**
 * Created by Athi
 */
public abstract class AthiFXApplication<GROUPS extends Enum<?> & Groups, ITEMS extends Enum<?> & Items> extends Application {

    @Inject
    private AthiFXApplicationConfiguration applicationConfiguration;

    @Override
    public void start(Stage primaryStage) throws Exception {
        AthiFXInjector.createInjector(this);


        List<GROUPS> groups = getGroups();
        List<ITEMS> items = getItems();

        beforeLaunch();

        primaryStage.show();
    }

    public abstract void beforeLaunch();

    public abstract List<GROUPS> getGroups();

    public abstract List<ITEMS> getItems();

}
