package com.github.athi.athifx.injector.instance.interfaces;

/**
 * Created by Athi
 */
public class InjectionTwoImpl implements InjectionTwo {

    public static final String VALUE = "InjectionTwoImpl";

    @Override
    public String call() {
        return VALUE;
    }
}
