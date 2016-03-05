package org.athifx.injector.injection;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.athifx.injector.configuration.InjectorConfiguration;
import org.athifx.injector.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by Athi
 */
class ResourcesPropertiesBinding implements ConfigurationBinding {

    private static final Log LOGGER = Log.getLogger(ResourcesPropertiesBinding.class);

    @Override
    public void bind(Binder binder, InjectorConfiguration injectorConfiguration) {
        List<String> resourcesPropertiesFileNames = injectorConfiguration.resourcesPropertiesFileNames();
        loadProperties(resourcesPropertiesFileNames).ifPresent(properties -> Names.bindProperties(binder, properties));
    }

    private static Optional<Properties> loadProperties(List<String> resourcesPropertiesFileNames) {
        if (!resourcesPropertiesFileNames.isEmpty()) {
            Properties properties = new Properties();
            resourcesPropertiesFileNames.forEach(resourcesPropertiesFileName ->
                    loadResourcesProperties(properties, resourcesPropertiesFileName));
            return Optional.of(properties);
        } else {
            return Optional.empty();
        }
    }

    private static void loadResourcesProperties(Properties properties, String resourcesPropertiesFileName) {
        URL resource = AthiFXInjector.class.getClassLoader().getResource(resourcesPropertiesFileName);
        if (Objects.nonNull(resource)) {
            try (InputStream resourceStream = resource.openStream()) {
                properties.load(resourceStream);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Failed to load properties file: " + resourcesPropertiesFileName);
        }
    }
}
