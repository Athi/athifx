package org.athifx.injector.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Athi
 */
public class InjectorConfigurationImpl implements InjectorConfiguration {

    public static final String PROPERTIES_SUFFIX = ".properties";

    private List<String> resourcesPropertiesFileNames;

    public InjectorConfigurationImpl(String... resourcesPropertiesFileNames) {
        this.resourcesPropertiesFileNames = initPropertiesFilesPathsList(resourcesPropertiesFileNames);
    }

    @Override
    public List<String> resourcesPropertiesFileNames() {
        return resourcesPropertiesFileNames;
    }

    private List<String> initPropertiesFilesPathsList(String... resourcesPropertiesFileNames) {
        if (resourcesPropertiesFileNames.length == 0) {
            return Collections.emptyList();
        } else {
            return Arrays.asList(resourcesPropertiesFileNames).stream().map(this::endsWithPropertiesSuffix).collect(Collectors.toList());
        }
    }

    private String endsWithPropertiesSuffix(String name) {
        return name.endsWith(PROPERTIES_SUFFIX) ? name : name.concat(PROPERTIES_SUFFIX);
    }
}
