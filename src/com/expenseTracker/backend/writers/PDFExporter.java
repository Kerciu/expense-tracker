package com.expenseTracker.backend.writers;


import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.utils.DateBoundPair;
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
import java.time.LocalDate;

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

        }
        catch(IOException e) {
            throw new RuntimeException();
        }
    }

    private Paragraph createHeader()
    {
        Paragraph headerParagraph = new Paragraph(user.getUsername());
        headerParagraph.setFontSize(20);
        headerParagraph.setBold();
        headerParagraph.setTextAlignment(TextAlignment.LEFT);
        return headerParagraph;
    }

    private Table createProcessedTransactionSummaryTable()
    {
        float[] tableCols = new float[] {1, 1, 1};
        Table table = new Table(tableCols);
        table.setWidth(UnitValue.createPercentValue(100));
        return table;
    }

    private Table addProcessedTransactionSummaryTableCells(Table table)
    {
        table.addCell(new Cell().add(new Paragraph()));
        table.addCell(new Cell().add(new Paragraph()));
        table.addCell(new Cell().add(new Paragraph()));
        return table;
    }

    private String createReportDateBoundsInfo()
    {
        DateBoundPair dateBoundPair = fetchTransactionDateBounds();
        return "Transaction Dates: \n" +
                dateBoundPair.getFirst().toString()
                +"—"+
                dateBoundPair.getSecond().toString();
    }

    private DateBoundPair fetchTransactionDateBounds()
    {
        // transaction list is ordered by date descending
        return new DateBoundPair(
            transactionList.getLast().getDate(),
            transactionList.getFirst().getDate()
        );
    }

}
