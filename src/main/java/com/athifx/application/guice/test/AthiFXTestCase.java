package com.athifx.application.guice.test;

import com.athifx.application.guice.AthiFXInjector;
import com.athifx.application.log.Log;
import org.junit.After;
import org.junit.Before;

import java.io.File;

/**
 * Created by Athi on 2016-02-27.
 */
public class AthiFXTestCase {

    @Before
    public void setUp() {
        AthiFXInjector.createInjector(this);
    }

    @After
    public void tearDown() {
        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

}
