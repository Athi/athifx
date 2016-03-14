package org.athifx.injector.scopes;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Athi
 */
@SuppressWarnings("CdiInjectionPointsInspection")
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