package org.athifx.gui;

import com.google.inject.Inject;
import javafx.application.Application;
import javafx.stage.Stage;
import org.athifx.gui.configuration.AthiFXApplicationConfiguration;
import org.athifx.gui.menu.group.Groups;
import org.athifx.gui.menu.item.Items;
import org.athifx.injector.AthiFXInjector;

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
