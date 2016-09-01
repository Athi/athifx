package com.github.athi.athifx.gui.security;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Athi
 */
public class UserTest {

    private static final String LOGIN_1 = "ONE";
    private static final String LOGIN_2 = "TWO";

    private static final List<String> PERMISSIONS_1 = Arrays.asList("ONE_P1", "ONE_P2", "ONE_P3");
    private static final List<String> PERMISSIONS_2 = Arrays.asList("TWO_P1", "TWO_P2", "TWO_P3");

    private User user1;
    private User user2;

    @Before
    public void setUp() {
        user1 = new User(LOGIN_1, PERMISSIONS_1);
        user2 = new User(LOGIN_2, PERMISSIONS_2);
    }


    @Test
    public void constructionTest() {
        assertEquals(LOGIN_1, user1.getLogin());
        assertEquals(LOGIN_2, user2.getLogin());

        assertEquals(PERMISSIONS_1, user1.getPermissions());
        assertEquals(PERMISSIONS_2, user2.getPermissions());
    }

    @Test
    public void isStringPermittedTest() {
        for (String permission : PERMISSIONS_1) {
            assertTrue(user1.isPermitted(permission));
            assertFalse(user2.isPermitted(permission));
        }

        for (String permission : PERMISSIONS_2) {
            assertTrue(user2.isPermitted(permission));
            assertFalse(user1.isPermitted(permission));
        }
    }

    @Test
    public void isAllPermitted() {
        assertTrue(user1.isAllPermitted(((String[]) PERMISSIONS_1.toArray())));
        assertFalse(user2.isAllPermitted(((String[]) PERMISSIONS_1.toArray())));

        assertFalse(user1.isAllPermitted(((String[]) PERMISSIONS_2.toArray())));
        assertTrue(user2.isAllPermitted(((String[]) PERMISSIONS_2.toArray())));
    }

    @Test
    public void isAnyPermitted() {
        assertTrue(user1.isAllPermitted(((String[]) PERMISSIONS_1.toArray())));
        assertFalse(user2.isAllPermitted(((String[]) PERMISSIONS_1.toArray())));

        assertFalse(user1.isAllPermitted(((String[]) PERMISSIONS_2.toArray())));
        assertTrue(user2.isAllPermitted(((String[]) PERMISSIONS_2.toArray())));

        for (String permission : PERMISSIONS_1) {
            assertTrue(user1.isAnyPermitted(permission));
            assertFalse(user2.isAnyPermitted(permission));
        }

        for (String permission : PERMISSIONS_2) {
            assertTrue(user2.isAnyPermitted(permission));
            assertFalse(user1.isAnyPermitted(permission));
        }
    }
}