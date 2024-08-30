package com.expenseTracker.backend.writers;


import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.utils.DateBoundPair;
import com.expenseTracker.backend.utils.UserBalanceAggregator;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.mysql.cj.conf.ConnectionUrlParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PDFExporter extends FileExporter {
    public PDFExporter(String filePath, User user) {
        super(filePath, user);
    }

    @Override
    public void exportFile() {
        if (transactionList == null || transactionList.isEmpty()) return;

        try {
            PdfWriter pdfWriter = new PdfWriter(filePath);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        }
    }

    private Paragraph createHeader() {
        Paragraph headerParagraph = new Paragraph(user.getUsername());
        headerParagraph.setFontSize(20);
        headerParagraph.setBold();
        headerParagraph.setTextAlignment(TextAlignment.LEFT);
        return headerParagraph;
    }

    private Table createSummaryTable() {
        float[] tableCols = new float[]{1, 1, 1};
        Table table = new Table(tableCols);
        table.setWidth(UnitValue.createPercentValue(100));
        return table;
    }

    private Table populateSummaryTable(Table table) {
        addSummaryCell(table, createReportDateBoundsInfo(), 10, false, TextAlignment.LEFT);
        addSummaryCell(table, createReportGeneratedDate(), 10, false, TextAlignment.LEFT);
        addSummaryCell(table, createUserBalanceInfo(), 12, true, TextAlignment.RIGHT);
        addSummaryCell(table, createTotalProcessedExpensesInfo(), 12, true, TextAlignment.RIGHT);
        addSummaryCell(table, createTotalProcessedIncomeInfo(), 12, true, TextAlignment.RIGHT);
        return table;
    }

    private void addSummaryCell(Table table, String text, int fontSize, boolean isBold, TextAlignment alignment) {
        Paragraph paragraph = new Paragraph(text).setFontSize(fontSize);
        if (isBold) {
            paragraph.setBold();
        }
        Cell cell = new Cell().add(paragraph).setTextAlignment(alignment).setBorder(null);
        table.addCell(cell);
    }

    private String createReportDateBoundsInfo() {
        DateBoundPair dateBoundPair = fetchTransactionDateBounds();
        return "Transaction Dates: \n" +
                dateBoundPair.getFirst().toString()
                + "—" +
                dateBoundPair.getSecond().toString();
    }

    private DateBoundPair fetchTransactionDateBounds() {
        // transaction list is ordered by date descending
        return new DateBoundPair(
                transactionList.getLast().getDate(),
                transactionList.getFirst().getDate()
        );
    }

    private String createReportGeneratedDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
        return "Report Generated on:\n" + LocalDateTime.now().format(dateTimeFormatter);
    }

    private String createUserBalanceInfo() {
        String username = user.getUsername();
        String usernameCapitalized = username.substring(0, 1).toUpperCase() + username.substring(1);
        String userBalance = user.getBalance().toString();
        return usernameCapitalized + "'s Balance: \n" + userBalance;
    }

    private String createTotalProcessedExpensesInfo() {
        BigDecimal processedExpenses = UserBalanceAggregator.getSummedUserExpenses(transactionList);
        return "Total Expenses:\n" + processedExpenses.toString();
    }

    private String createTotalProcessedIncomeInfo() {
        BigDecimal processedIncome = UserBalanceAggregator.getSummedUserIncome(transactionList);
        return "Total Income:\n" + processedIncome.toString();
    }
}
