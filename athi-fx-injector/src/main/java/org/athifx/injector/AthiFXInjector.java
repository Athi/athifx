package org.athifx.injector;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import org.athifx.injector.configuration.InjectorConfiguration;
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
        createInjector(self, null);
    }

    public static <T> void createInjector(T self, InjectorConfiguration configuration) {
        Optional<InjectorConfiguration> optionalConfiguration = Optional.ofNullable(configuration);
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                optionalConfiguration.ifPresent(injectorConfiguration ->
                        loadProperties(injectorConfiguration).ifPresent(properties ->
                                Names.bindProperties(binder(), properties)));

                bind((Class<Object>) self.getClass()).toInstance(self);

                getClassAndInstanceToBind().entrySet().forEach(classObjectEntry -> bind(classObjectEntry.getKey()).toInstance(classObjectEntry.getValue()));

                bindListener(Matchers.any(), new PostConstructInjectionListener());
            }
        });
    }

    private static Optional<Properties> loadProperties(InjectorConfiguration injectorConfiguration) {
        if (injectorConfiguration.getPropertiesFilesNames().isPresent()) {
            Properties properties = new Properties();
            injectorConfiguration.getPropertiesFilesNames().get().forEach(propertiesFileName -> addProperties(properties, propertiesFileName));
            return Optional.of(properties);
        } else {
            return Optional.empty();
        }
    }

    private static void addProperties(Properties properties, String propertiesFileName) {
        URL resource = AthiFXInjector.class.getClassLoader().getResource(propertiesFileName);
        if (Objects.nonNull(resource)) {
            try (InputStream resourceStream = resource.openStream()) {
                properties.load(resourceStream);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
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
