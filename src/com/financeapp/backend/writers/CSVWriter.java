package com.financeapp.backend.writers;

import com.financeapp.backend.data.Transaction;
import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CSVWriter {
    private final String filePath;
    private final User user;

    public CSVWriter(String filePath, User user)
    {
        this.filePath = filePath;
        this.user = user;
    }

    public void exportToCSV()
    {
        List<Transaction> transactionDetails = null;
        try {
            transactionDetails = getTransactionDetails(user);
            saveIntoCSVFormat(transactionDetails);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Transaction> getTransactionDetails(User user) throws SQLException
    {
        return MySQLConnector.getTransactionHistoryDetails(user);
    }

    private void saveIntoCSVFormat(List<Transaction> transactionDetails) throws IOException {
        if (transactionDetails == null || transactionDetails.isEmpty()) return;

        try(FileWriter csvWriter = new FileWriter(filePath)) {
            csvWriter.append("amount,type,category,description\n");

            for (Transaction transaction : transactionDetails) {
                csvWriter.append(String.format("%s,%s,%s,\"%s\"\n",
                        transaction.getAmount().toString(),
                        transaction.getType(),
                        transaction.getCategory(),
                        escapeCsv(transaction.getDescription())));
            }

            csvWriter.flush();
        }
    }

    private String escapeCsv(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }
}
