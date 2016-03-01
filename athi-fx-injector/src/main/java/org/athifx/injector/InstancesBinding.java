package org.athifx.injector;

import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;

/**
 * Created by Athi
 */
class InstancesBinding {

    static void bind(Binder binder) {
        Reflections reflections = new Reflections("", new FieldAnnotationsScanner());
        Set<Field> fieldsAnnotatedWith = reflections.getFieldsAnnotatedWith(Any.class);
        for (Field field : fieldsAnnotatedWith) {
            ParameterizedType instanceType = (ParameterizedType) field.getGenericType();
            Type generic = instanceType.getActualTypeArguments()[0];
            binder.bind(TypeLiteral.get(instanceType)).annotatedWith(Any.class).toInstance(captureAthiFXInstance(generic));
        }
    }

    private static <T extends Instance> T captureAthiFXInstance(Type generic) {
        return (T) new AthiFXInstance(generic);
    }

}
