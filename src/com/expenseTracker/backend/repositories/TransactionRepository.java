package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.DatabaseConnection;
import com.expenseTracker.backend.db.SQLStatementFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {
    public static void insertTransactionIntoDatabase(int userID, BigDecimal amount, String type, String category, LocalDate date, String description) throws SQLException
    {
        String query = SQLStatementFactory.insertTransactionIntoDatabase();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            PreparedStatementParametersSetter.setParameters(preparedStatement, userID, amount, type, category, date, description);
            preparedStatement.executeUpdate();
        }
    }

    public static void updateUserBalanceFromTransactions(User user) throws SQLException
    {
        String selectAmountsQuery = SQLStatementFactory.selectAllUserTransactionAmounts();
        String updateBalanceQuery = SQLStatementFactory.updateUserBalance();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement selectAmountsPreparedStatement = connection.prepareStatement(selectAmountsQuery);
             PreparedStatement updateUserBalancePreparedStatement = connection.prepareStatement(updateBalanceQuery))
        {
            PreparedStatementParametersSetter.setParameters(selectAmountsPreparedStatement, user.getUsername());
            ResultSet selectAmountsResultSet = selectAmountsPreparedStatement.executeQuery();

            user.setBalance(BigDecimal.ZERO);

            while(selectAmountsResultSet.next())
            {
                String type = selectAmountsResultSet.getString("type");
                BigDecimal transactionAmount = selectAmountsResultSet.getBigDecimal("amount");

                if (type.equalsIgnoreCase("Expense")) {
                    user.subBalance(transactionAmount);
                } else {
                    user.addBalance(transactionAmount);
                }
            }

            PreparedStatementParametersSetter.setParameters(updateUserBalancePreparedStatement, user.getBalance(), user.getId());
            updateUserBalancePreparedStatement.executeUpdate();
        }
    }

    public static List<Transaction> getTransactionHistoryDetails(User user) throws SQLException
    {
        List<Transaction> transactions = new ArrayList<>();
        String query = SQLStatementFactory.selectTransactionHistoryDetailsForCardDisplay();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            PreparedStatementParametersSetter.setParameters(preparedStatement, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String type = resultSet.getString("type");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                transactions.add(new Transaction(id, user.getId(), amount, date, type, category, description));
            }

            return transactions;
        }
    }

    public static void deleteTransactionHistoryCard(int transactionId) throws  SQLException
    {
        String query = SQLStatementFactory.deleteTransactionHistoryRecord();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            PreparedStatementParametersSetter.setParameters(preparedStatement, transactionId);
            preparedStatement.executeUpdate();
        }
    }

    public static void updateTransactionCard(int transactionId, BigDecimal amount, String type, LocalDate date, String category, String description) throws SQLException
    {
        String query = SQLStatementFactory.updateTransactionHistoryRecord();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            PreparedStatementParametersSetter.setParameters(preparedStatement, amount, type, date, category, description, transactionId);
            preparedStatement.executeUpdate();
        }
    }
}
