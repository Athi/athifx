package org.athifx.injector;

import com.google.inject.Binder;
import org.reflections.Reflections;

import javax.inject.Qualifier;
import java.util.Arrays;

/**
 * Created by Athi
 */
class QualifierBinding {

    static void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        reflections.getTypesAnnotatedWith(Qualifier.class).stream()
                .filter(qualifierAnnotated -> !qualifierAnnotated.isAnnotationPresent(Qualifier.class))
                .forEach(c -> Arrays.asList(c.getGenericInterfaces()).forEach(type -> Arrays.asList(c.getAnnotations()).forEach(annotation -> {
                    try {
                        binder.bind(((Class<Object>) type)).annotatedWith(annotation).toInstance(c.newInstance());
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                })));
    }
}
