package org.athifx.injector.injection;

import com.google.inject.Binder;
import org.athifx.injector.configuration.InjectorConfiguration;

/**
 * Created by Athi
 */
interface ConfigurationFileBinding {

    void bind(Binder binder, InjectorConfiguration injectorConfiguration);

}
