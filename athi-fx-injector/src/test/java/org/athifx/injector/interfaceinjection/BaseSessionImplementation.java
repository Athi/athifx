package org.athifx.injector.interfaceinjection;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 * Created by Athi
 */
@SuppressWarnings("CdiInjectionPointsInspection")
@SessionScoped
public class BaseSessionImplementation implements BaseSessionImplementationInterface {

    @Inject
    private BaseSessionImplementationInterface innerImpl;

    private int value = 0;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void incrementByTwo() {
        this.value = value + 2;
    }

    @Override
    public int getInnerImplValue() {
        return innerImpl.getValue();
    }
}
