package com.expenseTracker.backend.writers;


import com.expenseTracker.backend.data.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

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

        }
        catch(IOException e) {
            throw new RuntimeException();
        }
    }
}
