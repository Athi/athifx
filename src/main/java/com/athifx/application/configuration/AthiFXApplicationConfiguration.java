package com.athifx.application.configuration;

import com.athifx.application.menu.item.MenuItem;
import com.athifx.application.navigation.view.AView;
import com.google.common.collect.Lists;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

/**
 * Created by Athi
 */
public class AthiFXApplicationConfiguration {

    private List<? super AView> views = Lists.newArrayList();
    private List<? super AView> menuItems = Lists.newArrayList();

    @PostConstruct
    private void initApplicationConfiguration() throws Exception {
        Reflections reflections = new Reflections();
        Set<Class<? extends AView>> viewClasses = reflections.getSubTypesOf(AView.class);
        for (Class<? extends AView> viewClass : viewClasses) {
            AView viewInstance = viewClass.newInstance();
            views.add(viewInstance);
            if (viewClass.isAnnotationPresent(MenuItem.class)) {
                menuItems.add(viewInstance);
            }
        }
    }

    public List<? super AView> getViews() {
        return views;
    }

    public List<? super AView> getMenuItems() {
        return menuItems;
    }
}
