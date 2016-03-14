package org.athifx.injector;

import org.athifx.injector.injection.AthiFXInjector;
import org.athifx.injector.log.Log;
import org.junit.After;
import org.junit.Before;
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

/**
 * Created by Athi
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AthiFXInjector.class)
public abstract class AthiFXTestCase {

    @Before
    public void setUp() {
        AthiFXInjector.createInjector(this);
        PowerMockito.mockStatic(AthiFXInjector.class);
        Mockito.when(AthiFXInjector.getReflections())
                .thenReturn(new Reflections("", new FieldAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner()));
    }

    @After
    public void tearDown() {
        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

}
