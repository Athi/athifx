package com.github.athi.athifx.injector.injection;

import com.google.inject.Binder;
import org.reflections.Reflections;

import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.util.Arrays;

/**
 * Created by Athi
 */
class QualifierBinding implements Binding {

    @Override
    public void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        reflections.getTypesAnnotatedWith(Qualifier.class).stream()
                .filter(qualifierAnnotated -> !qualifierAnnotated.isAnnotationPresent(Qualifier.class))
                .forEach(c -> Arrays.asList(c.getGenericInterfaces()).forEach(type -> {
                    if (!type.equals(Annotation.class)) {
                        Arrays.asList(c.getAnnotations()).forEach(annotation -> {
                            try {
                                binder.bind(((Class<Object>) type)).annotatedWith(annotation).toInstance(c.newInstance());
                            } catch (InstantiationException | IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                }));
    }
}
