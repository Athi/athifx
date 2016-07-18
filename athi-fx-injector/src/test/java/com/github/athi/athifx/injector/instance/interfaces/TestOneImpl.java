package com.github.athi.athifx.injector.instance.interfaces;

import com.github.athi.athifx.injector.instance.qualifiers.QualifierOne;
import com.google.inject.Inject;

/**
 * Created by Athi
 */
@QualifierOne
public class TestOneImpl implements Test {

    @Inject
    private InjectionOne injectionOne;

    @Override
    public String test() {
        return String.valueOf(getClass());
    }

    @Override
    public String returnFromInjection() {
        return injectionOne.call();
    }
}
