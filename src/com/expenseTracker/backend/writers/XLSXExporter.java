package com.expenseTracker.backend.writers;

import com.expenseTracker.backend.data.User;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;


public class XLSXExporter extends FileExporter{
    public XLSXExporter(String filePath, User user) {
        super(filePath, user);
    }

    @Override
    public void exportFile() {
        if (transactionList == null || transactionList.isEmpty()) return;

        try (Workbook workbook = new XSSFWorkbook("Transactions")) {
            Sheet sheet = workbook.createSheet();
            int rowNum = 0;

            rowNum = createHeader(sheet, rowNum);
            rowNum = createSummaryTable(sheet, rowNum);
            createTransactionsTable(sheet, rowNum);

            try(FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
                workbook.write(fileOutputStream);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error while writing to XLSX file.");
        }

    }

    private int createHeader(Sheet sheet, int rowNum) {
        return 0;
    }

    private int createSummaryTable(Sheet sheet, int rowNum) {
        return 0;
    }

    private void createTransactionsTable(Sheet sheet, int rowNum) {
    }
}
