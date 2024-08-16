package com.financeapp.backend.db;

import com.financeapp.backend.data.User;

import java.math.BigDecimal;
import java.sql.*;

public class MySQLConnector {
    public static User validateLogin(String username, String password) {
        String query = SQLStatementFactory.constructUsernameStatement();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedHashedPassword = resultSet.getString("password");

                    if (PasswordUtils.checkIfPasswordMatches(password, storedHashedPassword)) {
                        int userId = resultSet.getInt("id");
                        BigDecimal balance = resultSet.getBigDecimal("balance");
                        return new User(userId, username, password, balance);
                    }
                    else {
                        System.out.println("Password does not match.");
                    }
                }
                else {
                    System.out.println("No user found with the given username and password.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    public static boolean registerNewUser(String username, String password) throws SQLException
    {
        String hashedPassword = PasswordUtils.hashPassword(password);

        /* true - success, false - failure */
        if (!checkIfUserExists(username))
        {
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                SQLStatementFactory.insertUserIntoDatabase()
            );

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            preparedStatement.executeUpdate();

            return true;
        }

        return false;
    }

    private static boolean checkIfUserExists(String username) throws SQLException
    {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
            SQLStatementFactory.constructUsernameStatement()
        );

        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}
