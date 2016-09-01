package com.github.athi.athifx.gui.application;

import com.github.athi.athifx.gui.security.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * Created by Athi
 */
@SessionScoped
public class AthiFXSession implements Serializable {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
