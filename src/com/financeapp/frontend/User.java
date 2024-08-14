package com.financeapp.frontend;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class User {
    private final int id;
    private final String username;
    private final String password;
    private BigDecimal balance;

    public User(int id, String username, String password, BigDecimal balance)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.FLOOR);

    }
}
