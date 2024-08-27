package com.expenseTracker.test;

import com.expenseTracker.backend.data.User;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void testUserInitialization() {
        User user = new User(1, "testUser", "password123", new BigDecimal("100.00"));

        assertEquals(1, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals(new BigDecimal("100.00").setScale(2), user.getBalance());
    }

    @Test
    public void testBalanceUpdate() {
        User user = new User(1, "testUser", "password123", new BigDecimal("100.00"));

        user.setBalance(new BigDecimal("200.987"));

        assertEquals(new BigDecimal("200.98").setScale(2), user.getBalance());
    }
}
