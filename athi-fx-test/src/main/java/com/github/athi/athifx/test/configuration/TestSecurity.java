package com.github.athi.athifx.test.configuration;

import com.github.athi.athifx.gui.security.Security;
import com.github.athi.athifx.gui.security.SecurityException;
import com.github.athi.athifx.gui.security.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athi
 */
public class TestSecurity implements Security {

    @Override
    public User login(String userLogin, String userPassword) throws SecurityException {

        List<String> permissions = new ArrayList<>();
//        permissions.add("test");

        User user = new User(userLogin, permissions);
        if (!user.isPermitted("test")) {
            throw new SecurityException("No \"test\" permission FOR USER: " + userLogin + " with password: " + userPassword);
        }
        return user;
    }
}
