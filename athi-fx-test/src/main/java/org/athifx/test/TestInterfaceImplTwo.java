package org.athifx.test;

import org.athifx.test.test_annotations.AnnotationTwo;

/**
 * Created by Athi
 */
@AnnotationTwo
public class TestInterfaceImplTwo implements TestInterface {
    @Override
    public void test() {
        System.out.println("called TestInterfaceImplTwo");
    }
}
