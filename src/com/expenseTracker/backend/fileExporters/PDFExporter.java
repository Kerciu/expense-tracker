package com.expenseTracker.backend.fileExporters;


import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.FileNotFoundException;
import java.io.IOException;

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

            document.add(createHeader());

            Table summaryTable = createSummaryTable();
            document.add(summaryTable);

            Table transactionTable = createTransactionTable();
            document.add(transactionTable);

            document.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + filePath, e);
        } catch (IOException e) {
            throw new RuntimeException("Error while closing the document", e);
        }
    }

    private Paragraph createHeader() {
        Paragraph headerParagraph = new Paragraph(user.getUsername());
        headerParagraph.setFontSize(28);
        headerParagraph.setBold();
        headerParagraph.setTextAlignment(TextAlignment.LEFT);
        headerParagraph.setMarginBottom(10);
        headerParagraph.setPadding(5);
        return headerParagraph;
    }

    private Table createSummaryTable() {
        float[] tableCols = new float[]{1, 1, 1};
        Table table = new Table(tableCols);
        table.setWidth(UnitValue.createPercentValue(100));
        table.setMarginBottom(20);
        table.setBorder(Border.NO_BORDER);
        populateSummaryTable(table);
        return table;
    }

    private Table createTransactionTable() {
        float[] tableCols = new float[]{2, 1, 2, 2, 3};
        Table table = new Table(tableCols);
        table.setWidth(UnitValue.createPercentValue(100));
        table.setMarginTop(10);
        populateTransactionTable(table);
        return table;
    }

    private void populateTransactionTable(Table table) {
        addTransactionHeaders(table);

        for (Transaction transaction : transactionList) {
            addTransactionCell(table,
                transaction.getDate().toString(),
                transaction.getType(),
                transaction.getCategory(),
                transaction.getAmount().toString(),
                transaction.getDescription()
            );
        }
    }

    private void populateSummaryTable(Table table) {
        addSummaryCell(table, createReportDateBoundsInfo(), 10, false);
        addSummaryCell(table, createReportGeneratedDate(), 10, false);
        table.addCell(new Cell().add(new Paragraph()).setBorder(Border.NO_BORDER));

        addSummaryCell(table, createUserBalanceInfo(), 12, true);
        addSummaryCell(table, createTotalProcessedExpensesInfo(), 12, true);
        addSummaryCell(table, createTotalProcessedIncomeInfo(), 12, true);
    }

    private void addSummaryCell(Table table, String text, int fontSize, boolean isBold) {
        Paragraph paragraph = new Paragraph(text).setFontSize(fontSize).setTextAlignment(TextAlignment.LEFT);
        if (isBold) {
            paragraph.setBold();
        }
        Cell cell = new Cell().add(paragraph).setTextAlignment(TextAlignment.LEFT).setBorder(null).setPadding(5);
        table.addCell(cell);
    }

    private void addTransactionCell(Table table, String... texts) {
        for (String text : texts) {
            Cell cell = new Cell().add(new Paragraph(text));
            cell.setPadding(5);
            table.addCell(cell);
        }
    }

    private void addTransactionHeaders(Table table)
    {
        for (String header : headers) {
            Cell cell = new Cell().add(new Paragraph(header));
            cell.setBackgroundColor(new DeviceRgb(200, 200, 200));
            cell.setTextAlignment(TextAlignment.CENTER);
            cell.setPadding(5);

            table.addCell(cell);
        }
    }
}
