package com.github.athi.athifx.gui.security;

/**
 * Created by Athi
 */
public interface Security {

    User login(String userLogin, String userPassword) throws SecurityException;

}
