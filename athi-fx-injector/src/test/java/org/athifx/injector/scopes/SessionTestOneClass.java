package org.athifx.injector.scopes;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by Athi on 2016-03-03.
 */
@SessionScoped
public class SessionTestOneClass implements Serializable {

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
