package com.github.athi.athifx.injector.interfaceinjection;

import java.io.Serializable;

/**
 * Created by Athi
 */
public interface BaseSessionImplementationInterface extends Serializable {

    int getValue();

    void incrementByTwo();

    int getInnerImplValue();
}
