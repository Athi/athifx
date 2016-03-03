package org.athifx.injector.scopes;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Athi on 2016-03-03.
 */
public class SessionTestTwoClass implements Serializable {

    @Inject
    private SessionTestOneClass sessionOne;

    public void setSessionOneValue(int value) {
        sessionOne.setValue(value);
    }

    public int getSessionOneValue() {
        return sessionOne.getValue();
    }
}
