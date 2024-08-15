package com.financeapp.backend.db;

import com.financeapp.backend.data.User;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Logger;

public class MySQLConnector {

    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    static {
        try (InputStream input = MySQLConnector.class.getClassLoader().getResourceAsStream("config.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            prop.load(input);
            DB_URL = prop.getProperty("db.url");
            DB_USERNAME = prop.getProperty("db.username");
            DB_PASSWORD = prop.getProperty("db.password");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static User validateLogin(String username, String password) {
        String query = SQLStatementFactory.constructUserDataStatement();

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            String hashedPassword = hashPassword(password);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, hashedPassword);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int userId = resultSet.getInt("id");
                    BigDecimal balance = resultSet.getBigDecimal("balance");
                    return new User(userId, username, password, balance);
                }
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }

        return null;
    }

    private static String hashPassword(String password) {
        // replace with actual password hashing logic (e.g., bcrypt)
        return password; // Placeholder
    }
}
