package com.github.athi.athifx.injector.interfaceinjection;

/**
 * Created by Athi
 */
public class BaseInterfaceImpl implements BaseInterface {

    public static final String CALLED_VALUE = "CALLED";

    @Override
    public void test() {
        System.out.println(CALLED_VALUE);
    }
}
