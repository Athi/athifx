package com.github.athi.athifx.injector.configuration;

import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.github.athi.athifx.injector.log.Log;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.ini4j.Ini;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.reflections8.Reflections;
import org.reflections8.scanners.FieldAnnotationsScanner;
import org.reflections8.scanners.SubTypesScanner;
import org.reflections8.scanners.TypeAnnotationsScanner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
@SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
@RunWith(PowerMockRunner.class)
@PrepareForTest(AthiFXInjector.class)
public class InjectorConfigurationTest {

    @Inject
    @Named("test1value")
    private String test1value;

    @Inject
    @Named("test2value")
    private String test2value;

    @Inject
    @Named("ini1.ini")
    private Ini ini1;

    @Inject
    @Named("ini2.ini")
    private Ini ini2;

    @Inject
    @Named("ini:ini1header:ini1value")
    private String ini1value;

    @Inject
    @Named("ini:ini2header:ini2value")
    private String ini2value;

    @Rule
    private ExpectedException expectedException = ExpectedException.none();

    private final List<File> files = new ArrayList<>();

    @SuppressWarnings("ConstantConditions")
    @Before
    public void setUp() throws Exception {
        files.add(new File(Thread.currentThread().getContextClassLoader().getResource("properties1.properties").getPath()));
        files.add(new File(Thread.currentThread().getContextClassLoader().getResource("properties2.properties").getPath()));
        files.add(new File(Thread.currentThread().getContextClassLoader().getResource("ini1.ini").getPath()));
        files.add(new File(Thread.currentThread().getContextClassLoader().getResource("ini2.ini").getPath()));
    }

    @Test
    public void emptyCreationTest() {
        InjectorConfiguration injectorConfiguration = new InjectorConfiguration();
        assertEquals(0, injectorConfiguration.getIniURLs().size());
        assertEquals(0, injectorConfiguration.getPropertiesURLs().size());
    }

    @Test
    public void normalCreationTest() {
        InjectorConfiguration injectorConfiguration = new InjectorConfiguration(files);

        assertEquals(2, injectorConfiguration.getPropertiesURLs().size());
        assertEquals(2, injectorConfiguration.getIniURLs().size());
    }

    @Test
    public void propertiesLoadTest() {
        setUpWithPropertiesFiles(files);
        assertEquals(test1value, "test1value");
        assertEquals(test2value, "test2value");
    }

    @Test
    public void iniLoadTest() {
        setUpWithPropertiesFiles(files);
        assertEquals("ini1value", ini1.get("ini1header").get("ini1value"));
        assertEquals("ini2value", ini2.get("ini2header").get("ini2value"));
    }

    @Test
    public void iniValueTest() {
        setUpWithPropertiesFiles(files);
        assertEquals("ini1value", ini1value);
        assertEquals("ini2value", ini2value);
    }

    @After
    public void tearDown() {
        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    private void setUpWithPropertiesFiles(List<File> files) {
        InjectorConfiguration configuration = new InjectorConfiguration(files);
        AthiFXInjector.createInjector(this, configuration);
        PowerMockito.mockStatic(AthiFXInjector.class);
        Mockito.when(AthiFXInjector.getReflections())
                .thenReturn(new Reflections("", new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner()));
    }
}