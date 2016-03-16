package com.github.athi.athifx.injector.injection;

import com.google.common.eventbus.EventBus;
import com.google.inject.Binder;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Created by Athi
 */
class EventBusBinding implements Binding {

    private final static EventBus eventBus = new EventBus();

    @Override
    public void bind(Binder binder) {
        binder.bind(EventBus.class).toInstance(eventBus);
        binder.bindListener(Matchers.any(), new TypeListener() {
            @Override
            public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
                typeEncounter.register((InjectionListener<I>) eventBus::register);
            }
        });
    }

}
