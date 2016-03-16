package com.github.athi.athifx.injector.injection;

import com.github.athi.athifx.injector.configuration.InjectorConfiguration;
import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Binder;
import com.google.inject.name.Names;

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
class PropertiesFilesFileBinding implements ConfigurationFileBinding {

    private static final Log LOGGER = Log.getLogger(PropertiesFilesFileBinding.class);

    @Override
    public void bind(Binder binder, InjectorConfiguration injectorConfiguration) {
        List<URL> propertiesURLs = injectorConfiguration.getPropertiesURLs();
        loadProperties(propertiesURLs).ifPresent(properties -> Names.bindProperties(binder, properties));
    }

    private static Optional<Properties> loadProperties(List<URL> propertiesURLs) {
        if (!propertiesURLs.isEmpty()) {
            Properties properties = new Properties();
            propertiesURLs.forEach(resource -> loadResourcesProperties(properties, resource));
            return Optional.of(properties);
        } else {
            return Optional.empty();
        }
    }

    private static void loadResourcesProperties(Properties properties, URL resource) {
        if (Objects.nonNull(resource)) {
            try (InputStream resourceStream = resource.openStream()) {
                properties.load(resourceStream);
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
