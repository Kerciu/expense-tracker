package com.expenseTracker.backend.fileExporters;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;

import java.io.FileWriter;
import java.io.IOException;

public class CSVExporter extends FileExporter{

    public CSVExporter(String filePath, User user)
    {
        super(filePath, user);
    }

    @Override
    public void exportFile() {
        if (transactionList == null || transactionList.isEmpty()) return;

        try(FileWriter csvWriter = new FileWriter(filePath)) {
            csvWriter.append("amount,date,type,category,description\n");

            for (Transaction transaction : transactionList) {
                csvWriter.append(String.format("%s,%s,%s,%s,\"%s\"\n",
                        transaction.getAmount().toString(),
                        transaction.getDate().toString(),
                        transaction.getType(),
                        transaction.getCategory(),
                        escapeCsv(transaction.getDescription())));
            }

            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String escapeCsv(String value) {
        if (value == null) return "";
        return value.replace("\"", "\"\"");
    }
}
