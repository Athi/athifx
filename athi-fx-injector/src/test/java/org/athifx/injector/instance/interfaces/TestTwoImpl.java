package org.athifx.injector.instance.interfaces;

import org.athifx.injector.instance.qualifiers.QualifierTwo;

/**
 * Created by Athi on 2016-03-03.
 */
@QualifierTwo
public class TestTwoImpl implements Test {
    @Override
    public String test() {
        return String.valueOf(getClass());
    }
}
