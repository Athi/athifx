package com.github.athi.athifx.injector.postconstruct;

import javax.annotation.PostConstruct;

/**
 * Created by Athi
 */
public class WithPublicPostConstruct {

    public static final String PUBLIC_INIT_VALUE_BEFORE_CALL = "BEFORE_PUBLIC_INIT";
    public static final String PUBLIC_INIT_VALUE_AFTER_CALL = "AFTER_PUBLIC_INIT";

    private String value = PUBLIC_INIT_VALUE_BEFORE_CALL;


    @PostConstruct
    public void publicInit() {
        this.value = PUBLIC_INIT_VALUE_AFTER_CALL;
    }

    public String getValue() {
        return value;
    }
}
