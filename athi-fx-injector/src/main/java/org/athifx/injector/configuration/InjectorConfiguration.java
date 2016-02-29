package org.athifx.injector.configuration;

import java.util.List;
import java.util.Optional;

/**
 * Created by Athi
 */
public interface InjectorConfiguration {

    Optional<List<String>> getPropertiesFilesNames();

}
