package com.github.athi.athifx.injector.injection;

import com.github.athi.athifx.injector.configuration.InjectorConfiguration;
import com.google.inject.Binder;

/**
 * Created by Athi
 */
interface ConfigurationFileBinding {

    void bind(Binder binder, InjectorConfiguration injectorConfiguration);

}
