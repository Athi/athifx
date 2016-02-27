package com.athifx.application.guice.applicationsessionscoped;

import com.athifx.application.guice.test.AthiFXTestCase;
import org.junit.Ignore;
import org.junit.Test;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
@Ignore("needs to be tested in a different way")
public class SessionApplicationScopedTest extends AthiFXTestCase {

    @Inject
    private SessionTestOneClass sessionTestOneClassFirst;

    @Inject
    private SessionTestOneClass sessionTestOneClassSecond;

    @Inject
    private SessionTestTwoClass sessionTestTwoClass;

    @Inject
    private ApplicationTestOneClass applicationTestOneClassFirst;

    @Inject
    private ApplicationTestOneClass applicationTestOneClassSecond;

    @Inject
    private ApplicationTestTwoClass applicationTestTwoClass;

    @Test
    public void testSessionScopedAnnotatedClass() {
        checkValueForAllSession(0);
        sessionTestOneClassFirst.setValue(1);
        checkValueForAllSession(1);
        sessionTestOneClassSecond.setValue(2);
        checkValueForAllSession(2);
        sessionTestTwoClass.setSessionOneValue(3);
        checkValueForAllSession(3);
    }

    @Test
    public void testApplicationScopedAnnotatedClass() {
        checkValueForAllApplication(0);
        applicationTestOneClassFirst.setValue(1);
        checkValueForAllApplication(1);
        applicationTestOneClassSecond.setValue(2);
        checkValueForAllApplication(2);
        applicationTestTwoClass.setSessionOneValue(3);
        checkValueForAllApplication(3);
    }

    private void checkValueForAllSession(int value) {
        assertEquals(value, sessionTestOneClassFirst.getValue());
        assertEquals(value, sessionTestOneClassSecond.getValue());
        assertEquals(value, sessionTestTwoClass.getSessionOneValue());
    }

    private void checkValueForAllApplication(int value) {
        assertEquals(value, applicationTestOneClassFirst.getValue());
        assertEquals(value, applicationTestOneClassSecond.getValue());
        assertEquals(value, applicationTestTwoClass.getSessionOneValue());
    }

}
