package org.athifx.injector.injection;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;
import org.athifx.injector.configuration.InjectorConfiguration;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Created by Athi
 */
public class AthiFXInjector {

    private static final Set<Binding> bindings = new HashSet<>();

    static {
        bindings.add(new InstancesBinding());
        bindings.add(new QualifierBinding());
        bindings.add(new ScopesBinding());
    }

    private static final Set<ConfigurationBinding> configurationBindings = new HashSet<>();

    static {
        configurationBindings.add(new ResourcesPropertiesBinding());
    }

    private static Reflections reflections;

    public static <T> void createInjector(T self) {
        createInjector(self, null);
    }

    public static <T> void createInjector(T self, InjectorConfiguration configuration) {
        Optional<InjectorConfiguration> optionalConfiguration = Optional.ofNullable(configuration);
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind((Class<Object>) self.getClass()).toInstance(self);

                optionalConfiguration.ifPresent(injectorConfiguration -> {
                    configurationBindings.forEach(binding -> binding.bind(binder(), injectorConfiguration));
                });

                bindings.forEach(binding -> binding.bind(binder()));

                bindListener(Matchers.any(), new PostConstructInjectionListener());
            }
        });
    }

    public static Reflections getReflections() {
        if (Objects.isNull(reflections)) {
            reflections = new Reflections("", new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner());
        }
        return reflections;
    }
}
