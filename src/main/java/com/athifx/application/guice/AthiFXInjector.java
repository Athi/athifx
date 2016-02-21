package com.athifx.application.guice;

import com.athifx.application.AthiFXApplication;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.matcher.Matchers;

/**
 * Created by Athi
 */
public class AthiFXInjector {

    public static void createInjector(AthiFXApplication self) {
        Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
                bind(AthiFXApplication.class).toInstance(self);

                bindListener(Matchers.any(), new PostConstructInjectionListener());
            }
        });
    }

}
