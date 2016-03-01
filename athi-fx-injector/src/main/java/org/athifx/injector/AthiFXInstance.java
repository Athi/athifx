package org.athifx.injector;

import org.athifx.injector.log.Log;
import org.reflections.Reflections;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Athi
 */
class AthiFXInstance<T> implements Instance<T> {

    private static final Log LOGGER = Log.getLogger(AthiFXInstance.class);

    private ArrayDeque<T> instances = new ArrayDeque<>();

    public AthiFXInstance(Type genericType) {
        Reflections reflections = new Reflections();
        Class genericClass = ((Class) genericType);
        Set<Class<T>> subTypesOfGeneric = reflections.getSubTypesOf(genericClass);
        fillInstancesCollection(subTypesOfGeneric);
    }

    private void fillInstancesCollection(Set<Class<T>> subTypeOfGeneric) {
        subTypeOfGeneric.forEach(genericClass -> {
            try {
                instances.add(genericClass.newInstance());
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    @Override
    public Instance<T> select(Annotation... annotations) {
        //TODO
        return null;
    }

    @Override
    public <U extends T> Instance<U> select(Class<U> aClass, Annotation... annotations) {
        //TODO
        return null;
    }

    @Override
    public <U extends T> Instance<U> select(TypeLiteral<U> typeLiteral, Annotation... annotations) {
        //TODO
        return null;
    }

    @Override
    public boolean isUnsatisfied() {
        return false;
    }

    @Override
    public boolean isAmbiguous() {
        return false;
    }

    @Override
    public void destroy(T t) {
        //TODO
    }

    @Override
    public Iterator<T> iterator() {
        return instances.iterator();
    }

    @Override
    public T get() {
        return instances.getFirst();
    }
}
