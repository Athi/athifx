package org.athifx.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import org.athifx.injector.log.Log;
import org.reflections.Reflections;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

/**
 * Created by Athi
 */
public class AthiFXInjector {

    private static final Log LOGGER = Log.getLogger(AthiFXInjector.class);

    public static <T> void createInjector(T self) {
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                Names.bindProperties(binder(), loadProperties());
                bind((Class<Object>) self.getClass()).toInstance(self);
                getClassAndInstanceToBind().entrySet().forEach(classObjectEntry -> bind(classObjectEntry.getKey()).toInstance(classObjectEntry.getValue()));
                bindListener(Matchers.any(), new PostConstructInjectionListener());
            }
        });
    }

    private static Properties loadProperties() {
        Properties result = new Properties();
        URL resource = AthiFXInjector.class.getClassLoader().getResource("application.properties");
        try (InputStream resourceStream = resource.openStream()) {
            result.load(resourceStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
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
                LOGGER.error(e.getMessage(), e);
            }
        }
        return classObjectToBind;
    }
}
