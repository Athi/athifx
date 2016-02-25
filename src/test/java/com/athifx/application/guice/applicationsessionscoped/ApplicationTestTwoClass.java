package com.athifx.application.guice.applicationsessionscoped;

import javax.inject.Inject;
import java.io.Serializable;

/**
 * Created by Athi
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
