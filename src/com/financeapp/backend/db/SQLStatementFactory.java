package com.financeapp.backend.db;

public class SQLStatementFactory {
    public static String constructUserDataStatement()
    {
        return "SELECT * FROM user_data WHERE username = ? AND password = ?;";
    }
}
