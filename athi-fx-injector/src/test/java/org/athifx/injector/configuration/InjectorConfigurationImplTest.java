package org.athifx.injector.configuration;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class InjectorConfigurationImplTest {

    @Test
    public void emptyCreationTest() {
        InjectorConfiguration injectorConfiguration = new InjectorConfigurationImpl();
        assertEquals(0, injectorConfiguration.resourcesPropertiesFileNames().size());
    }

    @Test
    public void normalCreationTest() {
        String fileName1 = "test1";
        String fileName2 = "test2.properties";
        InjectorConfiguration injectorConfiguration = new InjectorConfigurationImpl(fileName1, fileName2);

        assertEquals(2, injectorConfiguration.resourcesPropertiesFileNames().size());

        List<String> propertiesFilesNames = injectorConfiguration.resourcesPropertiesFileNames();
        assertEquals(false, propertiesFilesNames.contains(fileName1));
        assertEquals(true, propertiesFilesNames.contains(fileName1.concat(InjectorConfigurationImpl.PROPERTIES_SUFFIX)));
        assertEquals(true, propertiesFilesNames.contains(fileName2));
    }
}