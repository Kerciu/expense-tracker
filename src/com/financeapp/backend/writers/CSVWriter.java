package com.financeapp.backend.writers;

import com.financeapp.backend.db.DatabaseConnection;
import com.financeapp.backend.db.SQLStatementFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVWriter {
    public static void exportToCSV()
    {

    }

    private static Connection getDatabaseConnection() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    private static PreparedStatement constructPreparedStatement() throws SQLException
    {
        String query = SQLStatementFactory.selectTransactionHistoryDetailsForCardDisplay();
        PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement();
    }
}
