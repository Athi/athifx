package com.athifx.application.guice;

import com.athifx.application.AthiFXApplication;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import org.reflections.Reflections;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Athi
 */
public class AthiFXInjector {

    public static <T extends AthiFXApplication> void createInjector(T self) {
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(AthiFXApplication.class).toInstance(self);

                getClassAndInstanceToBind().entrySet().forEach(classObjectEntry -> bind(classObjectEntry.getKey()).toInstance(classObjectEntry.getValue()));

                bindListener(Matchers.any(), new PostConstructInjectionListener());
            }
        });
    }

    private static Map<Class<Object>, Object> getClassAndInstanceToBind() {
        Map<Class<Object>, Object> classObjectToBind = new HashMap<>();

        Reflections reflections = new Reflections();
        Set<Class<?>> typesAnnotatedWith = new HashSet<>();
        typesAnnotatedWith.addAll(reflections.getTypesAnnotatedWith(SessionScoped.class));
        typesAnnotatedWith.addAll(reflections.getTypesAnnotatedWith(ApplicationScoped.class));

        for (Class<?> type : typesAnnotatedWith) {
            try {
                classObjectToBind.put((Class<Object>) type, type.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return classObjectToBind;
    }
}
