package org.athifx.injector.injection;

import com.google.inject.Binder;
import org.athifx.injector.log.Log;
import org.reflections.Reflections;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Athi
 */
class ScopesBinding implements Binding {

    private static final Log LOGGER = Log.getLogger(ScopesBinding.class);

    @Override
    public void bind(Binder binder) {
        getSessionApplicationScopedClassesToBind().entrySet().forEach(classObjectEntry ->
                binder.bind(classObjectEntry.getKey()).toInstance(classObjectEntry.getValue()));
    }

    private static Map<Class<Object>, Object> getSessionApplicationScopedClassesToBind() {
        Map<Class<Object>, Object> classObjectToBind = new HashMap<>();

        Reflections reflections = AthiFXInjector.getReflections();
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
