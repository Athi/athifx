package com.github.athi.athifx.injector.instance;

import com.github.athi.athifx.injector.AthiFXTestCase;
import com.github.athi.athifx.injector.instance.interfaces.Test;
import com.github.athi.athifx.injector.instance.interfaces.TestOneImpl;
import com.github.athi.athifx.injector.instance.interfaces.TestTwoImpl;
import com.github.athi.athifx.injector.instance.qualifiers.QualifierOne;
import com.github.athi.athifx.injector.instance.qualifiers.QualifierTwo;
import com.google.inject.Inject;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class QualifierTest extends AthiFXTestCase {

    @QualifierOne
    @Inject
    private Test testOne;

    @QualifierTwo
    @Inject
    private Test testTwo;

    @Override
    public void setUp() {
        super.setUp();

    }

    @org.junit.Test
    public void testInjection() {
        assertEquals(TestOneImpl.class, testOne.getClass());
        assertEquals(TestTwoImpl.class, testTwo.getClass());
    }

}
