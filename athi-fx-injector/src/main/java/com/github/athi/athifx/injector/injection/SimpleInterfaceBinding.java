package com.github.athi.athifx.injector.injection;

import com.google.inject.Binder;
import com.google.inject.Inject;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Athi
 */
class SimpleInterfaceBinding implements Binding {

    private static final String HAS_TO_MANY_IMPLEMENTATIONS = " has to many implementations...";

    @Override
    public void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Inject.class);

        fieldsAnnotatedWith.stream()
                .filter(field -> field.getType().isInterface())
                .forEach(interfaceField -> {
                    Class<Object> interfaceType = (Class<Object>) interfaceField.getType();
                    Iterator iterator = reflections.getSubTypesOf(interfaceType).iterator();
                    Class<?> interfaceImpl = (Class<?>) iterator.next();
                    if (iterator.hasNext()) {
                        throw new RuntimeException(interfaceType + HAS_TO_MANY_IMPLEMENTATIONS);
                    } else {
                        binder.bind(interfaceType).to(interfaceImpl);
                    }
                });
    }
}
