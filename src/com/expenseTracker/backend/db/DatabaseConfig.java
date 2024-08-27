package com.expenseTracker.backend.db;

import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {
    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    static {
        try (InputStream input = MySQLConnector.class.getClassLoader().getResourceAsStream("com/expenseTracker/backend/resources/config.properties")) {
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

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUsername() {
        return DB_USERNAME;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }
}
