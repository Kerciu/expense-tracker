package com.financeapp.backend.db;

public class SQLStatementFactory {

    public static String constructUsernameStatement()
    {
        return "SELECT * FROM user_data WHERE username = ?;";
    }

    public static String insertUserIntoDatabase()
    {
        return "INSERT INTO user_data (username, password) VALUES (?, ?);";
    }
}
