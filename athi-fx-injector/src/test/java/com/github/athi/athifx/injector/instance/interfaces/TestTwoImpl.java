package com.github.athi.athifx.injector.instance.interfaces;

import com.github.athi.athifx.injector.instance.qualifiers.QualifierTwo;

/**
 * Created by Athi
 */
@QualifierTwo
public class TestTwoImpl implements Test {
    @Override
    public String test() {
        return String.valueOf(getClass());
    }
}
