package com.expenseTracker.backend.repositories;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.DatabaseConnection;
import com.expenseTracker.backend.db.PasswordUtils;
import com.expenseTracker.backend.db.SQLStatementFactory;

import java.math.BigDecimal;
import java.sql.*;

public class UserRepository {
    public static User validateLogin(String username, String password) {
        String query = SQLStatementFactory.constructUsernameStatement();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            PreparedStatementParametersSetter.setParameters(preparedStatement, username);
            ResultSet resultSet = preparedStatement.executeQuery();
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

                PreparedStatementParametersSetter.setParameters(preparedStatement, username, hashedPassword);
                preparedStatement.executeUpdate();
                return true;
            }
        }

        return false;
    }

    private static boolean checkIfUserExists(String username) throws SQLException {
        String query = SQLStatementFactory.constructUsernameStatement();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            PreparedStatementParametersSetter.setParameters(preparedStatement, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    private static User processValidationResult(ResultSet resultSet, String password) throws SQLException {
        if (resultSet.next()) {
            String storedHashedPassword = resultSet.getString("password");

            if (PasswordUtils.checkIfPasswordMatches(password, storedHashedPassword)) {
                int userId = resultSet.getInt("id");
                BigDecimal balance = resultSet.getBigDecimal("balance");
                return new User(userId, resultSet.getString("username"), password, balance);
            }
        } else {
            System.out.println("No user found with the given username and password.");
        }
        return null;
    }
}
