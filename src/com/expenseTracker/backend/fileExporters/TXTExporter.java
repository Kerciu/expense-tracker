package com.expenseTracker.backend.fileExporters;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;

import java.io.FileWriter;
import java.io.IOException;

public class TXTExporter extends FileExporter {
    public TXTExporter(String filePath, User user)
    {
        super(filePath, user);
    }

    @Override
    public void exportFile() {
        if (transactionList == null || transactionList.isEmpty()) return;

        try (FileWriter txtWriter = new FileWriter(filePath)) {
            txtWriter.append("Username: ").append(user.getUsername()).append("\n");
            txtWriter.append(createReportDateBoundsInfo()).append("\n");
            txtWriter.append(createReportGeneratedDate()).append("\n\n");

            txtWriter.append(createUserBalanceInfo()).append("\n");
            txtWriter.append(createTotalProcessedExpensesInfo()).append("\n");;
            txtWriter.append(createTotalProcessedIncomeInfo()).append("\n");;

            txtWriter.append("Transaction History\n");
            txtWriter.append("--------------------\n");

            int transactionCounter = 1;
            for (Transaction transaction : transactionList)
            {
                txtWriter.append("Transaction #").append(String.valueOf(transactionCounter)).append("\n");
                txtWriter.append(" - Amount: ").append(transaction.getAmount().toString()).append("\n");
                txtWriter.append(" - Date: ").append(transaction.getDate().toString()).append("\n");
                txtWriter.append(" - Type: ").append(transaction.getType()).append("\n");
                txtWriter.append(" - Category: ").append(transaction.getCategory()).append("\n");
                txtWriter.append(" - Description: ").append(transaction.getDescription()).append("\n\n");
                ++transactionCounter;
            }

            txtWriter.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
