package com.expenseTracker.backend.writers;

import com.expenseTracker.backend.data.User;
import com.mysql.cj.xdevapi.Column;
import org.apache.poi.ss.usermodel.*;
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
        Row row = sheet.createRow(rowNum++);
        Cell cell = row.createCell(0);
        cell.setCellValue(user.getUsername());

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();

        font.setFontHeightInPoints((short) 28);
        font.setBold(true);

        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);

        return rowNum;
    }

    private int createSummaryTable(Sheet sheet, int rowNum) {
        rowNum = addSummaryRow(sheet, rowNum, createReportDateBoundsInfo());
        rowNum = addSummaryRow(sheet, rowNum, createReportGeneratedDate());
        rowNum = addSummaryRow(sheet, rowNum, createUserBalanceInfo());
        rowNum = addSummaryRow(sheet, rowNum, createTotalProcessedExpensesInfo());
        rowNum = addSummaryRow(sheet, rowNum, createTotalProcessedIncomeInfo());
        ++rowNum;
        return rowNum;
    }

    private int addSummaryRow(Sheet sheet, int rowNum, String text) {
        Row row = sheet.createRow(rowNum++);
        Cell cell = row.createCell(0);
        cell.setCellValue(text);

        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        cell.setCellStyle(cellStyle);

        return rowNum;
    }

    private void createTransactionsTable(Sheet sheet, int rowNum) {
        
    }
}
