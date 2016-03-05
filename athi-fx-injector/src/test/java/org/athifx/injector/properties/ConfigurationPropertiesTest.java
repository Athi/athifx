package org.athifx.injector.properties;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.athifx.injector.configuration.InjectorConfigurationImpl;
import org.athifx.injector.injection.AthiFXInjector;
import org.athifx.injector.log.Log;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AthiFXInjector.class)
public class ConfigurationPropertiesTest {

    @Inject
    @Named("test1value")
    private String test1value;

    @Inject
    @Named("test2value")
    private String test2value;

    @Rule
    private ExpectedException expectedException = ExpectedException.none();

    @Test
    public void propertiesWrongFileTest() {
        expectedException.expect(RuntimeException.class);
        expectedException.expectMessage("Failed to load properties file: test3.pro.properties");
        setUpWithPropertiesFiles("test1.properties", "test2.properties", "test3.pro");
    }

    @Test
    public void propertiesLoadTest() {
        setUpWithPropertiesFiles("test1.properties", "test2.properties");
        assertEquals(test1value, "test1value");
        assertEquals(test2value, "test2value");
    }

    @After
    public void tearDown() {
        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    public void setUpWithPropertiesFiles(String... fileNames) {
        InjectorConfigurationImpl configuration = new InjectorConfigurationImpl(fileNames);
        AthiFXInjector.createInjector(this, configuration);
        PowerMockito.mockStatic(AthiFXInjector.class);
        Mockito.when(AthiFXInjector.getReflections())
                .thenReturn(new Reflections("", new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner()));
    }

}
