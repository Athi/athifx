package com.github.athi.athifx.injector.instance;

import com.github.athi.athifx.injector.AthiFXTestCase;
import com.github.athi.athifx.injector.instance.interfaces.*;
import org.junit.Before;
import org.junit.Test;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.util.TypeLiteral;
import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Athi
 */
public class AnyInstanceTest extends AthiFXTestCase {

    @Inject
    @Any
    private Instance<com.github.athi.athifx.injector.instance.interfaces.Test> testInstance;

    private List<String> expectedList;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        expectedList = Arrays.asList(new TestOneImpl().test(), new TestTwoImpl().test());
        expectedList.sort(String::compareTo);
    }

    @Test
    public void testAnyInstanceIteratorTest() {
        List<String> resultList = new ArrayList<>();
        testInstance.iterator().forEachRemaining(test -> resultList.add(test.test()));
        resultList.sort(String::compareTo);
        assertArrayEquals(expectedList.toArray(), resultList.toArray());
    }

    @Test
    public void testAnyInstanceGetTest() {
        String result = testInstance.get().test();
        assertEquals(true, expectedList.contains(result));
    }

    @Test
    public void testAnyInstanceSelectAnnotationsTest() {
        assertEquals(new TestOneImpl().test(), testInstance.select(TestOneImpl.class.getAnnotations()).get().test());
        assertEquals(new TestTwoImpl().test(), testInstance.select(TestTwoImpl.class.getAnnotations()).get().test());

        List<Annotation> annotations = new ArrayList<>();
        annotations.addAll(Arrays.asList(TestOneImpl.class.getAnnotations()));
        annotations.addAll(Arrays.asList(TestTwoImpl.class.getAnnotations()));
        assertEquals(true, expectedList.contains(testInstance.select(annotations.toArray(new Annotation[annotations.size()])).get().test()));
    }

    @Test
    public void testAnyInstanceSelectClassAnnotationsTest() {
        assertEquals(new TestOneImpl().test(), testInstance.select(TestOneImpl.class, TestOneImpl.class.getAnnotations()).get().test());
        assertEquals(new TestTwoImpl().test(), testInstance.select(TestTwoImpl.class, TestTwoImpl.class.getAnnotations()).get().test());
    }

    @Test
    public void testAnyInstanceTypeLiteralClassAnnotationsTest() {
        assertEquals(new TestOneImpl().test(), testInstance.select(new TypeLiteral<TestOneImpl>() {
        }, TestOneImpl.class.getAnnotations()).get().test());
        assertEquals(new TestTwoImpl().test(), testInstance.select(new TypeLiteral<TestTwoImpl>() {
        }, TestTwoImpl.class.getAnnotations()).get().test());
    }

    @Test
    public void testAnyInstanceInjectedInterfaces() {
        assertEquals(InjectionOneImpl.VALUE, testInstance.select(TestOneImpl.class.getAnnotations()).get().returnFromInjection());
        assertEquals(InjectionTwoImpl.VALUE, testInstance.select(TestTwoImpl.class.getAnnotations()).get().returnFromInjection());
    }

}
