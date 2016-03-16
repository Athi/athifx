package org.athifx.injector.interfaceinjection;

import com.google.inject.Inject;
import org.athifx.injector.AthiFXTestCase;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class InterfaceInjectionTest extends AthiFXTestCase {

    @Inject
    private BaseInterface baseInterface;

    @Inject
    private BaseSessionImplementationInterface baseSessionImplementation1;

    @Inject
    private BaseSessionImplementationInterface baseSessionImplementation2;

    @Rule
    private final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

    @Test
    public void baseInterfaceInjectionTest() {
        baseInterface.test();
        assertEquals(BaseInterfaceImpl.CALLED_VALUE.trim(), systemOutRule.getLog().trim());
    }

    @Test
    public void baseSessionImplementationTest() {
        assertEquals(0, baseSessionImplementation1.getValue());
        assertEquals(0, baseSessionImplementation2.getValue());
        assertEquals(0, baseSessionImplementation1.getInnerImplValue());
        assertEquals(0, baseSessionImplementation2.getInnerImplValue());

        baseSessionImplementation1.incrementByTwo();

        assertEquals(2, baseSessionImplementation1.getValue());
        assertEquals(2, baseSessionImplementation2.getValue());
        assertEquals(2, baseSessionImplementation1.getInnerImplValue());
        assertEquals(2, baseSessionImplementation2.getInnerImplValue());

        baseSessionImplementation2.incrementByTwo();

        assertEquals(4, baseSessionImplementation1.getValue());
        assertEquals(4, baseSessionImplementation2.getValue());
        assertEquals(4, baseSessionImplementation1.getInnerImplValue());
        assertEquals(4, baseSessionImplementation2.getInnerImplValue());
    }

}
