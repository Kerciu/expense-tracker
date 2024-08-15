package com.financeapp.backend.db;

import com.financeapp.backend.data.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnector {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/finance_manager";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "FinanceManager123@";

    public static User validateLogin(String username, String password)
    {
        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
