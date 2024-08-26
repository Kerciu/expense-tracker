package com.financeapp.frontend.app.generateReport.frame;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.generateReport.panels.GenerateReportButtonPanel;
import com.financeapp.frontend.app.generateReport.panels.ReportTypePanel;
import com.financeapp.frontend.app.transactionHistory.panels.HistoryCardsPanel;
import com.financeapp.frontend.app.transactionHistory.panels.TransactionHistoryButtonPanel;
import com.financeapp.frontend.app.utils.BaseFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class GenerateReportFrame extends BaseFrame {
    private ReportTypePanel csvReportTypePanel;
    private ReportTypePanel pdfReportTypePanel;
    private ReportTypePanel xlsxReportTypePanel;
    private ReportTypePanel txtReportTypePanel;
    private GenerateReportButtonPanel buttonPanel;

    public GenerateReportFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
    }

    @Override
    protected void addGuiComponents() {
        setLayout(null);

        addWelcomingComponents();
        addPanels();
    }

    private void addPanels()
    {
        initializePanels();
        arrangePanels();

        add(csvReportTypePanel);
        add(pdfReportTypePanel);
        add(xlsxReportTypePanel);
        add(txtReportTypePanel);
        add(buttonPanel);

        revalidate();
        repaint();
    }

    private void initializePanels()
    {
        csvReportTypePanel = new ReportTypePanel("CSV", getWidth());
        pdfReportTypePanel = new ReportTypePanel("PDF", getWidth());
        xlsxReportTypePanel =  new ReportTypePanel("XLSX", getWidth());
        txtReportTypePanel = new ReportTypePanel("TXT", getWidth());
        buttonPanel = new GenerateReportButtonPanel(this, getWidth());
    }

    private void arrangePanels()
    {
        int initialYValue = 80;
        int offset = 100;
        csvReportTypePanel.setBounds(0, initialYValue, getWidth() - 10, 90);
        pdfReportTypePanel.setBounds(0, initialYValue + offset, getWidth() - 10, 90);
        xlsxReportTypePanel.setBounds(0, initialYValue + 2 * offset, getWidth() - 10, 90);
        txtReportTypePanel.setBounds(0, initialYValue + 3 * offset, getWidth() - 10, 90);
        buttonPanel.setBounds(0, 500, getWidth(), 40);
    }

    private void addWelcomingComponents()
    {
        add(createSeparator());
        add(createGenerateReportLabel());
    }

    private JLabel createGenerateReportLabel()
    {
        return UIComponentFactory.createLabel(
                "Generate Report", 0, 20, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
    }
}
