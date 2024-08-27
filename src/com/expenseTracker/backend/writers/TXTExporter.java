package com.expenseTracker.backend.writers;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.utils.UserBalanceAggregator;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

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
            txtWriter.append("Balance: ").append(user.getBalance().toString()).append("\n\n");

            BigDecimal totalExpenses = UserBalanceAggregator.getSummedUserExpenses(transactionList);
            BigDecimal totalIncome = UserBalanceAggregator.getSummedUserIncome(transactionList);

            txtWriter.append("Total expenses: ").append(totalExpenses.toString()).append("\n");
            txtWriter.append("Total income: ").append(totalIncome.toString()).append("\n\n");

            txtWriter.append("Transaction History:\n");
            txtWriter.append("--------------------\n");

            int transactionCounter = 1;
            for (Transaction transaction : transactionList)
            {
                txtWriter.append("Transaction #").append(String.valueOf(transactionCounter)).append("\n");
                txtWriter.append(" - Amount: ").append(transaction.getAmount().toString()).append("\n");
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
