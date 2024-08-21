package com.financeapp.frontend.app.transactionhistory;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.BaseFrame;
import com.financeapp.frontend.app.transactionhistory.panels.TransactionHistoryButtonPanel;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class TransactionHistoryFrame extends BaseFrame {
    private TransactionHistoryButtonPanel transactionHistoryButtonPanel;

    public TransactionHistoryFrame(String title, User user, int width, int height) {
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

        add(transactionHistoryButtonPanel);

        revalidate();
        repaint();
    }

    private void initializePanels()
    {
        transactionHistoryButtonPanel = new TransactionHistoryButtonPanel(this, getWidth());
    }

    private void arrangePanels()
    {
        transactionHistoryButtonPanel.setBounds(0, 500, getWidth(), 30);
    }

    private void addWelcomingComponents()
    {
        add(createSeparator());
        add(createTransactionHistoryLabel());
    }

    private JLabel createTransactionHistoryLabel()
    {
        return UIComponentFactory.createLabel(
                "Transaction History", 0, 20, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
    }

    private JSeparator createSeparator()
    {
        return UIComponentFactory.createSeparator(15, 70, getWidth() - 30, 10);
    }
}
