package com.github.athi.athifx.injector.configuration;

import java.net.URL;
import java.util.List;

/**
 * Created by Athi
 */
public interface InjectorConfiguration {

    List<URL> getPropertiesURLs();

    List<URL> getIniURLs();

}
