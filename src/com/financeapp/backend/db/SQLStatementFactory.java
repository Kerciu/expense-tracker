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

    public static String insertTransactionIntoDatabase()
    {
        return "INSERT INTO transactions (user_id, amount, type, category, description) "
                + "VALUES (?, ?, ?, ?, ?);";
    }

    public static String selectAllUserTransactionAmounts()
    {
        return "SELECT amount, type FROM transactions WHERE user_id = ("
                + "SELECT id FROM user_data WHERE username = ?" +
                ");";
    }

    public static String selectTransactionHistoryDetailsForCardDisplay()
    {
        return "SELECT amount, type, category, description " +
                "FROM transactions WHERE user_id = ("
                + "SELECT id FROM user_data WHERE username = ?" +
                ");";
    }

    public static String deleteTransactionHistoryRecord()
    {
        return "DELETE FROM transactions WHERE user_id = ("
                + "SELECT id FROM user_data WHERE username = ?" +
                ");";
    }
}
