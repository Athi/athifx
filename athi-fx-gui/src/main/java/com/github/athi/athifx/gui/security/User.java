package com.github.athi.athifx.gui.security;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by Athi
 */
public class User {

    private String login;

    private List<String> permissions;

    public User(String login, List<String> permissions) {
        this.login = login;
        this.permissions = permissions;
    }

    public boolean isPermitted(String permission) {
        return Objects.nonNull(permissions) && permissions.contains(permission);
    }

    public boolean isAllPermitted(String... permissions) {
        return Arrays.stream(permissions).allMatch(this::isPermitted);
    }

    public boolean isAnyPermitted(String... permissions) {
        return Arrays.stream(permissions).anyMatch(this::isPermitted);
    }

    public String getLogin() {
        return login;
    }

    public List<String> getPermissions() {
        return permissions;
    }
}
