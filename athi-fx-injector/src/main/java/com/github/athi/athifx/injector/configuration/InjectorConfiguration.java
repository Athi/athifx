package com.github.athi.athifx.injector.configuration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athi
 */
public class InjectorConfiguration {

    public static final String PROPERTIES_SUFFIX = ".properties";
    public static final String INI_SUFFIX = ".ini";

    private final List<URL> propertiesURLs = new ArrayList<>();
    private final List<URL> iniURLs = new ArrayList<>();

    public InjectorConfiguration() {
    }

    public InjectorConfiguration(List<File> configurationFiles) {
        initConfigurationFiles(configurationFiles);
    }

    /**
     * @return - defined URLs for all the *.properties files.
     */
    public List<URL> getPropertiesURLs() {
        return propertiesURLs;
    }

    /**
     * @return - defined URLs for all the *.ini files.
     */
    public List<URL> getIniURLs() {
        return iniURLs;
    }

    private void initConfigurationFiles(List<File> configurationFiles) {
        for (File configurationFile : configurationFiles) {
            String name = configurationFile.getName();
            try {
                URL url = configurationFile.toURI().toURL();
                if (name.endsWith(PROPERTIES_SUFFIX)) {
                    this.propertiesURLs.add(url);
                } else if (name.endsWith(INI_SUFFIX)) {
                    this.iniURLs.add(url);
                }
            } catch (MalformedURLException mue) {
                throw new RuntimeException("Wrong file: " + name);
            }
        }
    }
}
