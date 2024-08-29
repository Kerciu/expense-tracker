package com.expenseTracker.backend.db;

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
        return "INSERT INTO transactions (user_id, amount, type, category, date, description) "
                + "VALUES (?, ?, ?, ?, ?, ?);";
    }

    public static String selectAllUserTransactionAmounts()
    {
        return "SELECT amount, type FROM transactions WHERE user_id = ("
                + "SELECT id FROM user_data WHERE username = ?" +
                ");";
    }

    public static String selectTransactionHistoryDetailsForCardDisplay()
    {
        return "SELECT id, amount, type, date, category, description " +
                "FROM transactions " +
                "WHERE user_id = (" +
                "  SELECT id " +
                "  FROM user_data " +
                "  WHERE username = ?" +
                ") " +
                "ORDER BY date DESC;";
    }

    public static String deleteTransactionHistoryRecord()
    {
        return "DELETE FROM transactions WHERE id = ?;";
    }

    public static String updateTransactionHistoryRecord()
    {
        return "UPDATE transactions SET " +
                "amount = ?, type = ?, date = ?, category = ?, description = ? "
                + "WHERE id = ?;";
    }

    public static String updateUserBalance()
    {
        return "UPDATE user_data SET balance = ? WHERE id = ?";
    }
}
