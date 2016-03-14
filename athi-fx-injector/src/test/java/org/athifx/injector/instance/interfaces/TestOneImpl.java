package org.athifx.injector.instance.interfaces;

import org.athifx.injector.instance.qualifiers.QualifierOne;

/**
 * Created by Athi
 */
@QualifierOne
public class TestOneImpl implements Test {
    @Override
    public String test() {
        return String.valueOf(getClass());
    }
}
