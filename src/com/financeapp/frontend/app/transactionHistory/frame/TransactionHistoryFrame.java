package com.financeapp.frontend.app.transactionHistory.frame;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.utils.BaseFrame;
import com.financeapp.frontend.app.transactionHistory.panels.HistoryCardsPanel;
import com.financeapp.frontend.app.transactionHistory.panels.TransactionHistoryButtonPanel;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class TransactionHistoryFrame extends BaseFrame {
    private JScrollPane historyCardsScrollPane;
    private HistoryCardsPanel historyCardsPanel;
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

        add(historyCardsScrollPane);
        add(transactionHistoryButtonPanel);

        revalidate();
        repaint();
    }

    private void initializePanels()
    {
        historyCardsPanel = new HistoryCardsPanel(user);
        historyCardsScrollPane = new JScrollPane(historyCardsPanel);
        historyCardsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        historyCardsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        transactionHistoryButtonPanel = new TransactionHistoryButtonPanel(this, getWidth());
    }

    private void arrangePanels()
    {
        historyCardsScrollPane.setBounds(0, 90, getWidth(), 380);
        transactionHistoryButtonPanel.setBounds(0, 500, getWidth(), 40);
    }

    private void addWelcomingComponents()
    {
        add(createSeparator());
        add(createTransactionHistoryLabel());
        add(createHistoryCardsPanel());
    }

    private JLabel createTransactionHistoryLabel()
    {
        return UIComponentFactory.createLabel(
                "Transaction History", 0, 20, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
    }

    private JPanel createHistoryCardsPanel()
    {
        return new HistoryCardsPanel(user);
    }
}
