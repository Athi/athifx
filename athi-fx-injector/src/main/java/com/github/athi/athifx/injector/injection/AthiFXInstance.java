package com.github.athi.athifx.injector.injection;

import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Binder;
import com.google.inject.Injector;
import org.reflections.Reflections;

import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import javax.ws.rs.NotSupportedException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Athi
 */
class AthiFXInstance<T> implements Instance<T> {

    private static final Log LOGGER = Log.getLogger(AthiFXInstance.class);

    private ArrayDeque<T> instances = new ArrayDeque<>();

    public AthiFXInstance(Type genericType, Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        Class genericClass = ((Class) genericType);
        Set<Class<T>> subTypesOfGeneric = reflections.getSubTypesOf(genericClass);
        fillInstancesCollection(subTypesOfGeneric, binder);
    }

    private AthiFXInstance(ArrayDeque<T> newInstancesDeque) {
        this.instances = newInstancesDeque;
    }

    private void fillInstancesCollection(Set<Class<T>> subTypeOfGeneric, Binder binder) {
        subTypeOfGeneric.forEach(genericClass -> {
            try {
                T instance = genericClass.newInstance();
                binder.requestInjection(instance);
                instances.push(instance);
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(e.getMessage(), e);
            }
        });
    }

    private ArrayDeque<T> newInstancesDequeByAnnotations(Annotation... annotations) {
        ArrayDeque<T> newInstancesDeque = new ArrayDeque<>();
        instances.iterator().forEachRemaining(instance -> {
            for (Annotation annotation : instance.getClass().getAnnotations()) {
                if (Arrays.asList(annotations).contains(annotation)) {
                    newInstancesDeque.push(instance);
                }
            }
        });
        return newInstancesDeque;
    }

    @Override
    public Instance<T> select(Annotation... annotations) {
        ArrayDeque<T> newInstancesDeque = newInstancesDequeByAnnotations(annotations);
        return new AthiFXInstance<>(newInstancesDeque);
    }

    @Override
    public <U extends T> Instance<U> select(Class<U> aClass, Annotation... annotations) {
        ArrayDeque<U> newInstancesDeque = (ArrayDeque<U>) newInstancesDequeByAnnotations(annotations);
        Iterator<U> descendingIterator = newInstancesDeque.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (!descendingIterator.next().getClass().isAssignableFrom(aClass)) {
                descendingIterator.remove();
            }
        }
        return new AthiFXInstance<>(newInstancesDeque);
    }

    @Override
    public <U extends T> Instance<U> select(TypeLiteral<U> typeLiteral, Annotation... annotations) {
        ArrayDeque<U> newInstancesDeque = (ArrayDeque<U>) newInstancesDequeByAnnotations(annotations);
        Iterator<U> descendingIterator = newInstancesDeque.descendingIterator();
        while (descendingIterator.hasNext()) {
            if (!descendingIterator.next().getClass().equals(typeLiteral.getType())) {
                descendingIterator.remove();
            }
        }
        return new AthiFXInstance<>(newInstancesDeque);
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
        throw new NotSupportedException();
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
