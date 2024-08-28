package com.expenseTracker.frontend.app.generateReport.frame;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.frontend.app.generateReport.panels.GenerateReportButtonPanel;
import com.expenseTracker.frontend.app.generateReport.panels.ReportTypePanel;
import com.expenseTracker.frontend.app.utils.BaseFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;

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
        int initialYValue = 70;
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
        add(createGuidingLabel());
    }

    private JLabel createGenerateReportLabel()
    {
        return UIComponentFactory.createLabel(
                "Generate Report", 0, 0, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
    }

    private JLabel createGuidingLabel()
    {
        String guidingText = "Check desired file extensions and enter their file names";
        return UIComponentFactory.createLabel(
                guidingText, 0, 50, getWidth() - 10, 20, 12, SwingConstants.CENTER
        );
    }

    public ReportTypePanel getCsvReportTypePanel() {
        return csvReportTypePanel;
    }

    public ReportTypePanel getPdfReportTypePanel() {
        return pdfReportTypePanel;
    }

    public ReportTypePanel getXlsxReportTypePanel() {
        return xlsxReportTypePanel;
    }

    public ReportTypePanel getTxtReportTypePanel() {
        return txtReportTypePanel;
    }
}
