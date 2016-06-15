package com.github.athi.athifx.gui;

import com.github.athi.athifx.injector.injection.AthiFXInjector;
import com.github.athi.athifx.injector.log.Log;
import com.sun.glass.ui.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
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
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;

/**
 * Created by Athi
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(AthiFXInjector.class)
public abstract class AthiFXTestCase extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        Platform.runLater(() -> Application.GetApplication().createRobot());
    }

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