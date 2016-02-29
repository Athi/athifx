package org.athifx.injector.configuration;

import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class InjectorConfigurationImplTest {

    @Test
    public void emptyCreationTest() {
        InjectorConfiguration injectorConfiguration = new InjectorConfigurationImpl();
        assertEquals(Optional.empty(), injectorConfiguration.getPropertiesFilesNames());
    }

    @Test
    public void normalCreationTest() {
        String fileName1 = "test1";
        String fileName2 = "test2.properties";
        InjectorConfiguration injectorConfiguration = new InjectorConfigurationImpl(fileName1, fileName2);

        assertEquals(true, injectorConfiguration.getPropertiesFilesNames().isPresent());

        List<String> propertiesFilesNames = injectorConfiguration.getPropertiesFilesNames().get();
        assertEquals(false, propertiesFilesNames.contains(fileName1));
        assertEquals(true, propertiesFilesNames.contains(fileName1.concat(InjectorConfigurationImpl.PROPERTIES_SUFFIX)));
        assertEquals(true, propertiesFilesNames.contains(fileName2));

    }
}