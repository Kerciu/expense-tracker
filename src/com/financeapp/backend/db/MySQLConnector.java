package com.financeapp.backend.db;

import com.financeapp.backend.data.Transaction;
import com.financeapp.backend.data.User;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLConnector {
    public static User validateLogin(String username, String password) {
        String query = SQLStatementFactory.constructUsernameStatement();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, query, username);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            return processValidationResult(resultSet, password);
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    public static boolean registerNewUser(String username, String password) throws SQLException {
        String hashedPassword = PasswordUtils.hashPassword(password);

        if (!checkIfUserExists(username)) {
            String query = SQLStatementFactory.insertUserIntoDatabase();
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                setPreparedStatementParameters(preparedStatement, username, hashedPassword);
                preparedStatement.executeUpdate();
                return true;
            }
        }

        return false;
    }

    private static boolean checkIfUserExists(String username) throws SQLException {
        String query = SQLStatementFactory.constructUsernameStatement();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = createPreparedStatement(connection, query, username);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            return resultSet.next();
        }
    }

    private static PreparedStatement createPreparedStatement(Connection connection, String query, String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        return preparedStatement;
    }

    private static void setPreparedStatementParameters(PreparedStatement preparedStatement, int id) throws SQLException
    {
        preparedStatement.setString(1, String.valueOf(id));
    }

    private static void setPreparedStatementParameters(PreparedStatement preparedStatement, String username) throws SQLException
    {
        preparedStatement.setString(1, username);
    }

    private static void setPreparedStatementParameters(PreparedStatement preparedStatement, String username, String hashedPassword) throws SQLException {
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, hashedPassword);
    }

    private static void setPreparedStatementParameters(PreparedStatement preparedStatement, int userId, BigDecimal amount, String type, String category, String description) throws  SQLException
    {
        preparedStatement.setString(1, String.valueOf(userId));
        preparedStatement.setString(2, String.valueOf(amount.setScale(2, RoundingMode.FLOOR)));
        preparedStatement.setString(3, type);
        preparedStatement.setString(4, category);
        preparedStatement.setString(5, description);
    }

    private static User processValidationResult(ResultSet resultSet, String password) throws SQLException {
        if (resultSet.next()) {
            String storedHashedPassword = resultSet.getString("password");

            if (PasswordUtils.checkIfPasswordMatches(password, storedHashedPassword)) {
                int userId = resultSet.getInt("id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                return new User(userId, resultSet.getString("username"), password, balance);
            } else {
                System.out.println("Password does not match.");
            }
        } else {
            System.out.println("No user found with the given username and password.");
        }
        return null;
    }

    public static void insertTransactionIntoDatabase(int userID, BigDecimal amount, String type, String category, String description) throws SQLException
    {
        String query = SQLStatementFactory.insertTransactionIntoDatabase();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            setPreparedStatementParameters(preparedStatement, userID, amount, type, category, description);
            preparedStatement.executeUpdate();
        }
    }

    public static void getAllTransactionAmounts(User user) throws SQLException
    {
        String query = SQLStatementFactory.selectAllUserTransactionAmounts();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            setPreparedStatementParameters(preparedStatement, user.getUsername());
            ResultSet resultSet = preparedStatement.executeQuery();

            user.setBalance(BigDecimal.ZERO);

            while(resultSet.next())
            {
                String type = resultSet.getString("type");
                BigDecimal transactionAmount = resultSet.getBigDecimal("amount");

                if (type.equalsIgnoreCase("Expense")) {
                    user.subBalance(transactionAmount);
                } else {
                    user.addBalance(transactionAmount);
                }
            }
        }
    }

    public static List<Transaction>  getTransactionHistoryDetailsForCards(User user) throws SQLException
    {
        List<Transaction> transactions = new ArrayList<>();
        String query = SQLStatementFactory.selectTransactionHistoryDetailsForCardDisplay();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            setPreparedStatementParameters(preparedStatement, user.getUsername());

            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                BigDecimal amount = resultSet.getBigDecimal("amount");
                String type = resultSet.getString("type");
                String category = resultSet.getString("category");
                String description = resultSet.getString("description");
                transactions.add(new Transaction(id, user.getId(), amount, type, category, description));
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
            setPreparedStatementParameters(preparedStatement, transactionId);
            preparedStatement.executeUpdate();
        }
    }
}
