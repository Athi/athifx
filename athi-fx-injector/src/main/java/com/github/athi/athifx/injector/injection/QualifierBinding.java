package com.github.athi.athifx.injector.injection;

import com.google.inject.Binder;
import org.reflections8.Reflections;

import javax.inject.Qualifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * Created by Athi
 */
class QualifierBinding implements Binding {

    @Override
    public void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        reflections.getTypesAnnotatedWith(Qualifier.class).stream()
                .filter(qualifierAnnotated -> qualifierAnnotated.isAnnotationPresent(Qualifier.class))
                .forEach(c -> {
                    Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) c;
                    Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(annotationClass);
                    Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith((annotationClass));
                    for (Field field : fieldsAnnotatedWith) {
                        typesAnnotatedWith.stream()
                                .filter(typeAnnotatedWith -> field.getType().isAssignableFrom(typeAnnotatedWith))
                                .forEach(typeAnnotatedWith ->
                                        binder.bind(((Class<Object>) field.getGenericType()))
                                                .annotatedWith(annotationClass)
                                                .to(typeAnnotatedWith));
                    }
                });
    }
}
