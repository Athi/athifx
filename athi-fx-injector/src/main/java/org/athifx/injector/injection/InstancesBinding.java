package org.athifx.injector.injection;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import org.reflections.Reflections;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by Athi
 */
class InstancesBinding implements Binding {

    @Override
    public void bind(Binder binder) {
        Reflections reflections = AthiFXInjector.getReflections();
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Any.class);
        fieldsAnnotatedWith.stream().filter(field -> field.getType().equals(Instance.class)).forEach(field -> {
            ParameterizedType instanceType = (ParameterizedType) field.getGenericType();
            Type generic = instanceType.getActualTypeArguments()[0];
            binder.bind(TypeLiteral.get(instanceType)).annotatedWith(Any.class).toInstance(captureAthiFXInstance(generic));
        });
    }

    private static <T extends Instance> T captureAthiFXInstance(Type generic) {
        return (T) new AthiFXInstance(generic);
    }

}
