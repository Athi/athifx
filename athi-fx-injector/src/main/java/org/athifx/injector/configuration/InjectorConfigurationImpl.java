package org.athifx.injector.configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
public class InjectorConfigurationImpl implements InjectorConfiguration {

    private static final String PROPERTIES_SUFFIX = ".properties";

    private Optional<List<String>> propertiesFilesNames;

    public InjectorConfigurationImpl(String... propertiesFileNames) {
        this.propertiesFilesNames = initPropertiesFilesList(propertiesFileNames);
    }

    @Override
    public Optional<List<String>> getPropertiesFilesNames() {
        return propertiesFilesNames;
    }

    private Optional<List<String>> initPropertiesFilesList(String... propertiesFiles) {
        if (propertiesFiles.length == 0) {
            return Optional.empty();
        } else {
            List<String> propertiesFilesList = Arrays.asList(propertiesFiles).stream().map(this::endsWithPropertiesSuffix).collect(Collectors.toList());
            return Optional.of(propertiesFilesList);
        }
    }

    private String endsWithPropertiesSuffix(String name) {
        return name.endsWith(PROPERTIES_SUFFIX) ? name : name.concat(PROPERTIES_SUFFIX);
    }
}
