package com.expenseTracker.backend.db;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.repositories.TransactionRepository;
import com.expenseTracker.backend.repositories.UserRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class MySQLConnector {
    public static User validateLogin(String username, String password) {
        return UserRepository.validateLogin(username, password);
    }

    public static boolean registerNewUser(String username, String password) throws SQLException {
        return UserRepository.registerNewUser(username, password);
    }

    public static void insertTransactionIntoDatabase(int userID, BigDecimal amount, String type, String category, LocalDate date, String description) throws SQLException
    {
        TransactionRepository.insertTransactionIntoDatabase(userID, amount, type, category, date, description);
    }

    public static void updateUserBalanceFromTransactions(User user) throws SQLException
    {
        TransactionRepository.updateUserBalanceFromTransactions(user);
    }

    public static List<Transaction>  getTransactionHistoryDetails(User user) throws SQLException
    {
        return TransactionRepository.getTransactionHistoryDetails(user);
    }

    public static void deleteTransactionHistoryCard(int transactionId) throws  SQLException
    {
        TransactionRepository.deleteTransactionHistoryCard(transactionId);
    }

    public static void updateTransactionCard(int transactionId, BigDecimal amount, String type, LocalDate date, String category, String description) throws SQLException
    {
        TransactionRepository.updateTransactionCard(transactionId, amount, type, date, category, description);
    }
}
