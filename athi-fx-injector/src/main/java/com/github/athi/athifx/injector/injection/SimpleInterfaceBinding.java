package com.github.athi.athifx.injector.injection;

import com.google.inject.Binder;
import com.google.inject.Inject;
import org.reflections.Reflections;

import javax.enterprise.inject.Any;
import javax.inject.Qualifier;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
class SimpleInterfaceBinding implements Binding {

    private static final String HAS_TOO_MANY_IMPLEMENTATIONS = " has too many implementations...";

    @Override
    public void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Inject.class)
                .stream()
                .filter(f -> !f.isAnnotationPresent(Any.class))
                .filter(f -> Arrays.stream(f.getAnnotations()).noneMatch(ann -> ann.annotationType().isAnnotationPresent(Qualifier.class)))
                .collect(Collectors.toSet());

        fieldsAnnotatedWith.stream()
                .filter(field -> field.getType().isInterface())
                .forEach(interfaceField -> {
                    Class<Object> interfaceType = (Class<Object>) interfaceField.getType();
                    Iterator iterator = reflections.getSubTypesOf(interfaceType).iterator();
                    Class<?> interfaceImpl = (Class<?>) iterator.next();
                    if (iterator.hasNext()) {
                        throw new RuntimeException(interfaceType + HAS_TOO_MANY_IMPLEMENTATIONS);
                    } else {
                        binder.bind(interfaceType).to(interfaceImpl);
                    }
                });
    }
}
