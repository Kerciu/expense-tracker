package com.financeapp.frontend.app;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends FinanceAppGUI {
    public MainFrame()
    {
        super("Finance Manager");
    }

    @Override
    protected void addGuiComponents() {
        add(createMainPanel());
        add(createTransactionPanel());
    }

    private JPanel createMainPanel()
    {
        JPanel mainPanel = new JPanel(new BorderLayout());
        addMainPanelWelcomingText(mainPanel);
        addMainPanelCurrentBalance(mainPanel);
        addMainPanelButtons(mainPanel);
        addMainPanelAttributes(mainPanel);
        return mainPanel;
    }

    private JPanel createTransactionPanel()
    {
        JPanel transactionPanel = new JPanel();
        addTransactionPanelAttributeS(transactionPanel);
        return transactionPanel;
    }

    private void addMainPanelAttributes(JPanel mainPanel)
    {

    }

    private void addTransactionPanelAttributeS(JPanel transactionPanel)
    {
        JLabel transactionLabel = new JLabel("Recent Transactions");
        transactionLabel.setFont(new Font("Dialog", Font.PLAIN, 30));
        transactionPanel.add(transactionLabel);

        transactionPanel.setBorder(BorderFactory.createEmptyBorder());
        JTable transactionTable = new JTable();
        transactionPanel.add(new JScrollPane(transactionTable), BorderLayout.CENTER);
    }

    private void addMainPanelWelcomingText(JPanel mainPanel)
    {

    }

    private void addMainPanelCurrentBalance(JPanel mainPanel)
    {

    }

    private void addMainPanelButtons(JPanel mainPanel)
    {

    }
}
