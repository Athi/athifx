package com.github.athi.athifx.gui.configuration;

import com.github.athi.athifx.gui.menu.group.Group;
import com.github.athi.athifx.gui.menu.item.Item;
import com.github.athi.athifx.gui.navigation.view.AView;
import com.github.athi.athifx.gui.navigation.view.View;
import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
@SessionScoped
public class AthiFXApplicationProperties implements Serializable {

    @Any
    @Inject
    private Instance<AView> viewsInstance;

    private List<Group> groups;
    private List<Item> items;

    @PostConstruct
    private void initApplicationConfiguration() throws Exception {
        Reflections reflections = AthiFXInjector.getReflections();

        Set<Class<? extends Group>> groupsClasses = reflections.getSubTypesOf(Group.class);
        groups = Arrays.asList(groupsClasses.iterator().next().getEnumConstants());

        Set<Class<? extends Item>> itemsClasses = reflections.getSubTypesOf(Item.class);
        items = Arrays.asList(itemsClasses.iterator().next().getEnumConstants());
    }

    public Map<Long, AView> getViews() {
        return Lists.newArrayList(viewsInstance)
                .stream()
                .filter(v -> v.getClass().isAnnotationPresent(View.class))
                .collect(Collectors.toMap(v -> v.getClass().getAnnotation(View.class).itemId(), aView -> aView));
    }

    public List<Group> getGroups() {
        return groups;
    }

    public List<Item> getItems() {
        return items;
    }
}
