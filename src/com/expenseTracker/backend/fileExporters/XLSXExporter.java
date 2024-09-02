package com.expenseTracker.backend.fileExporters;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
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

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Transactions");
            int rowNum = 0;

            rowNum = createHeader(sheet, rowNum);
            rowNum = createSummaryTable(sheet, rowNum);
            createTransactionsTable(sheet, rowNum);
            // ChartGenerator.createPieChartXLSX((XSSFSheet) sheet, transactionList, rowNum +1, rowNum +2);

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
        return ++rowNum;
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
        createHeaderColumns(sheet, rowNum++);
        createTransactionRows(sheet, rowNum);
        autoSizeColumns(sheet);
    }

    private void createHeaderColumns(Sheet sheet, int rowNum) {
        Row row = sheet.createRow(rowNum);

        int cellNum = 0;
        for (String header : headers) {
            Cell cell = row.createCell(cellNum++);
            cell.setCellValue(header);

            CellStyle cellStyle = sheet.getWorkbook().createCellStyle();

            cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(cellStyle);
        }
    }

    private void createTransactionRows(Sheet sheet, int rowNum) {
        for (Transaction transaction : transactionList) {
            Row row = sheet.createRow(rowNum++);
            int cellNum = 0;

            row.createCell(cellNum++).setCellValue(transaction.getDate().toString());
            row.createCell(cellNum++).setCellValue(transaction.getType());
            row.createCell(cellNum++).setCellValue(transaction.getCategory());
            row.createCell(cellNum++).setCellValue(transaction.getAmount().toString());
            row.createCell(cellNum).setCellValue(transaction.getDescription());
        }
    }

    private void autoSizeColumns(Sheet sheet) {
        for (int i = 0; i < headers.length; ++i) {
            sheet.autoSizeColumn(i);
        }
    }
}
