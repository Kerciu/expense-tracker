package com.financeapp.frontend.app.generateReport.frame;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.transactionHistory.panels.HistoryCardsPanel;
import com.financeapp.frontend.app.transactionHistory.panels.TransactionHistoryButtonPanel;
import com.financeapp.frontend.app.utils.BaseFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class GenerateReportFrame extends BaseFrame {
    public GenerateReportFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
    }

    @Override
    protected void addGuiComponents() {
        addWelcomingComponents();
        addPanels();
    }

    private void addPanels()
    {
        initializePanels();
        arrangePanels();

        revalidate();
        repaint();
    }

    private void initializePanels()
    {

    }

    private void arrangePanels()
    {

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
