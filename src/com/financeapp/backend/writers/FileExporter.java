package com.financeapp.backend.writers;

import com.financeapp.backend.data.Transaction;
import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public abstract class FileExporter {
    protected String filePath;
    protected User user;
    protected List<Transaction> transactionList;

    public FileExporter(String filePath, User user)
    {
        this.filePath = filePath;
        this.user = user;
        fetchUserTransactions();
    }

    public abstract void exportFile();

    private void fetchUserTransactions()
    {
        List<Transaction> transactionDetails = null;
        try {
            transactionDetails = getTransactionDetails(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        this.transactionList = transactionDetails;
    }

    private List<Transaction> getTransactionDetails(User user) throws SQLException
    {
        return MySQLConnector.getTransactionHistoryDetails(user);
    }
}
