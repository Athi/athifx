package org.athifx.test;

import org.athifx.test.test_annotations.AnnotationOne;

/**
 * Created by Athi
 */
@AnnotationOne
public class TestInterfaceImplOne implements TestInterface {
    @Override
    public void test() {
        System.out.println("called TestInterfaceImplOne");
    }
}
