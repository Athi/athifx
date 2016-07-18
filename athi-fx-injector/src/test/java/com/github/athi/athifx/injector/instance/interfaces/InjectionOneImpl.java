package com.github.athi.athifx.injector.instance.interfaces;

/**
 * Created by Athi
 */
public class InjectionOneImpl implements InjectionOne {

    public static final String VALUE = "InjectionOneImpl";

    @Override
    public String call() {
        return VALUE;
    }
}
