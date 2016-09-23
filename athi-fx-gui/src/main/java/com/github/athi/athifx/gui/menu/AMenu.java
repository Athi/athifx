package com.github.athi.athifx.gui.menu;

import com.github.athi.athifx.gui.menu.exception.AMenuConfigurationException;
import com.github.athi.athifx.gui.navigation.navigator.Navigator;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by mp2
 */
public class AMenu extends VBox {

    public static final double DEFAULT_LARGE_WIDTH = 200;
    public static final double DEFAULT_SMALL_WIDTH = 20;

    private Stream<AGroup> groups = Stream.empty();

    private Navigator navigator;

    public AMenu(Navigator navigator) {
        this.navigator = navigator;

        setPrefWidth(DEFAULT_LARGE_WIDTH);
        setPadding(Insets.EMPTY);
    }

    public AGroup addGroup(AGroup newGroup) {
        newGroup.setNavigator(navigator);
        return groups.filter(aGroup -> aGroup.getGroupId() == newGroup.getGroupId())
                .collect(Collectors.collectingAndThen(Collectors.toList(), addAGroupFunction(newGroup)));
    }

    public AGroup getGroup(long id) {
        return groups.filter(aGroup -> aGroup.getGroupId() == id)
                .collect(Collectors.collectingAndThen(Collectors.toList(), singleAGroupFunction(id)));
    }

    private Function<List<AGroup>, AGroup> addAGroupFunction(AGroup group) {
        return aGroups -> {
            if (aGroups.isEmpty()) {
                aGroups.add(group);
                aGroups.sort((g1, g2) -> Long.compare(g1.getGroupId(), g2.getGroupId()));
                return group;
            } else {
                throw new AMenuConfigurationException("Group with id: " + group.getGroupId() + " is already added to the menu.");
            }
        };
    }

    private Function<List<AGroup>, AGroup> singleAGroupFunction(long groupIdToValidate) {
        return aGroups -> {
            if (aGroups.isEmpty()) {
                throw new AMenuConfigurationException("Group with id: " + groupIdToValidate + " does not exist.");
            } else if (aGroups.size() == 1) {
                return aGroups.get(0);
            } else {
                throw new AMenuConfigurationException("There are multiple groups with id: " + groupIdToValidate);
            }
        };
    }

}