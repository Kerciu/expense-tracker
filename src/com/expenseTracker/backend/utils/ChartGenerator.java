package com.expenseTracker.backend.utils;

import com.expenseTracker.backend.data.Transaction;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;

import java.util.List;

public class ChartGenerator {
    public static void createPieChartXLSX(XSSFSheet sheet, List<Transaction> transactionList, int startRow, int dataRow)
    {
        populateSheetWithData(sheet, transactionList, startRow);

        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, dataRow + 2, 10, dataRow + 20);

        XSSFChart chart = drawing.createChart(anchor);

        XDDFDataSource<String> categories = XDDFDataSourcesFactory.fromStringCellRange(sheet,
                new CellRangeAddress(startRow, dataRow - 1, 0, 0));
        XDDFNumericalDataSource<Double> values = XDDFDataSourcesFactory.fromNumericCellRange(sheet,
                new CellRangeAddress(startRow, dataRow - 1, 1, 1));

        XDDFChartData data = chart.createData(ChartTypes.PIE, null, null);
        XDDFChartData.Series series = data.addSeries(categories, values);
        series.setTitle("Expenses", null);

        chart.setTitleText("Expenses by Category");
        chart.setTitleOverlay(false);
        chart.plot(data);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.RIGHT);
    }

    private static void populateSheetWithData(XSSFSheet sheet, List<Transaction> transactionList, int startRow) {
        int rowNum = startRow;

        Row headerRow = sheet.createRow(rowNum++);
        headerRow.createCell(0).setCellValue("Category");
        headerRow.createCell(1).setCellValue("Amount");

        for (Transaction transaction : transactionList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transaction.getCategory());
            row.createCell(1).setCellValue(transaction.getAmount().doubleValue()); // Assuming `getAmount()` returns a `Number`
        }
    }

    public  static void createPieChartPDF()
    {

    }
}
