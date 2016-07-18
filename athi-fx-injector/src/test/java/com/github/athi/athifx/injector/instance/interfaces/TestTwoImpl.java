package com.github.athi.athifx.injector.instance.interfaces;

import com.github.athi.athifx.injector.instance.qualifiers.QualifierTwo;
import com.google.inject.Inject;

/**
 * Created by Athi
 */
@QualifierTwo
public class TestTwoImpl implements Test {

    @Inject
    private InjectionTwo injectionTwo;

    @Override
    public String test() {
        return String.valueOf(getClass());
    }

    @Override
    public String returnFromInjection() {
        return injectionTwo.call();
    }

}
