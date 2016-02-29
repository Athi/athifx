package org.athifx.injector.test;

import org.athifx.injector.AthiFXInjector;
import org.athifx.injector.log.Log;
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
