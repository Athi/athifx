package org.athifx.injector;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.athifx.injector.log.Log;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Athi
 */
class PostConstructInjectionListener implements TypeListener {

    private static final Log LOGGER = Log.getLogger(PostConstructInjectionListener.class);

    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        typeEncounter.register((InjectionListener<I>) i -> invokeMethodWithAnnotation(i, PostConstruct.class));
    }

    private void invokeMethodWithAnnotation(Object object, Class<? extends Annotation> annotation) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(annotation)) {
                try {
                    if (!method.isAccessible()) {
                        method.setAccessible(true);
                    }
                    method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(e.getMessage(), e);
                }
            }
        }
    }
}