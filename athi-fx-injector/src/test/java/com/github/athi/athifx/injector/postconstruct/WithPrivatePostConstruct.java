package com.github.athi.athifx.injector.postconstruct;

import javax.annotation.PostConstruct;

/**
 * Created by Athi
 */
public class WithPrivatePostConstruct {

    public static final String PRIVATE_INIT_VALUE_BEFORE_CALL = "BEFORE_PRIVATE_INIT";
    public static final String PRIVATE_INIT_VALUE_AFTER_CALL = "AFTER_PRIVATE_INIT";

    private String value = PRIVATE_INIT_VALUE_BEFORE_CALL;

    @PostConstruct
    private void privateInit() {
        this.value = PRIVATE_INIT_VALUE_AFTER_CALL;
    }

    public String getValue() {
        return value;
    }
}
