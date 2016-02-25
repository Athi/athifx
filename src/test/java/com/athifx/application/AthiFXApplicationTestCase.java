package com.athifx.application;

import com.athifx.application.log.Log;
import org.junit.After;

import java.io.File;
import java.util.List;

/**
 * Created by Athi
 */
public class AthiFXApplicationTestCase extends AthiFXApplication {

    @Override
    public List getGroups() {
        return null;
    }

    @Override
    public List getItems() {
        return null;
    }

    @After
    public void tearDown() {
        File file = new File(Log.DEFAULT_LOG_FILE_NAME);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }
}