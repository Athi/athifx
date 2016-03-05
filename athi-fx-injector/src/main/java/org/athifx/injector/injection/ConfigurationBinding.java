package org.athifx.injector.injection;

import com.google.inject.Binder;
import org.athifx.injector.configuration.InjectorConfiguration;

/**
 * Created by Athi
 */
interface ConfigurationBinding {

    void bind(Binder binder, InjectorConfiguration injectorConfiguration);

}
