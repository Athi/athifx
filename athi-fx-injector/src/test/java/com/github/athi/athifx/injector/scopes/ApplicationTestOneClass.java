package com.github.athi.athifx.injector.scopes;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

/**
 * Created by Athi
 */
@ApplicationScoped
public class ApplicationTestOneClass implements Serializable {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
