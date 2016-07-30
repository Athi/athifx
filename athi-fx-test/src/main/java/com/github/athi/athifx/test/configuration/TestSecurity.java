package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.security.Security;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athi
 */
public class TestSecurity implements Security {
    @Override
    public String login() {
        return "XXX";
    }

    @Override
    public List<String> permissions() {
        return new ArrayList<>();
    }
}
