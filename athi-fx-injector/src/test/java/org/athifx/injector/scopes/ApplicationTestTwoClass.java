package org.athifx.injector.scopes;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Athi on 2016-03-03.
 */
public class ApplicationTestTwoClass implements Serializable {

    @Inject
    private ApplicationTestOneClass applicationOne;

    public void setSessionOneValue(int value) {
        applicationOne.setValue(value);
    }

    public int getSessionOneValue() {
        return applicationOne.getValue();
    }
}