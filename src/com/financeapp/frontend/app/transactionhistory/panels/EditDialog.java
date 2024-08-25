package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.frontend.app.addframe.panels.*;

import javax.swing.*;

public class EditDialog extends JDialog {
    private HistoryCardsPanel source;
    private HistoryCard historyCard;
    private AmountPanel amountPanel;
    private TransactionTypePanel transactionTypePanel;
    private CategoryPanel categoryPanel;
    private DescriptionPanel descriptionPanel;
    private EditButtonPanel editButtonPanel;

    public EditDialog(HistoryCardsPanel source, HistoryCard historyCard)
    {
        this.source = source;
        this.historyCard = historyCard;

        setTitle("Edit Card");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setModal(false);
        setLocationRelativeTo(source);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);

        initializeAllComponents();
    }

    private void initializeAllComponents()
    {
        initializePanels();
        fillWithExistingValues();
        addComponents();
    }

    private void addComponents()
    {
        add(amountPanel);
        add(transactionTypePanel);
        add(categoryPanel);
        add(descriptionPanel);
        add(editButtonPanel);
    }

    private void initializePanels()
    {
        amountPanel = new AmountPanel(getWidth());
        categoryPanel = new CategoryPanel(getWidth(), true);
        transactionTypePanel = new TransactionTypePanel(categoryPanel, getWidth());
        descriptionPanel = new DescriptionPanel(getWidth());
        editButtonPanel = new EditButtonPanel(this, getWidth());
    }

    private void fillWithExistingValues()
    {
        amountPanel.setAmountText(String.valueOf(historyCard.getAmount()));
        transactionTypePanel.setExpense(historyCard.getType().equalsIgnoreCase("Expense"));
        descriptionPanel.getDescriptionTextArea().setText(historyCard.getDescription());

        JComboBox<String> categoryComboBox = categoryPanel.getCategoryComboBox();
        String categoryFromDB = historyCard.getCategory();

        if (categoryFromDB != null) {
            categoryComboBox.setSelectedItem(categoryFromDB);
        }
    }

    public AmountPanel getAmountPanel() {
        return amountPanel;
    }

    public TransactionTypePanel getTransactionTypePanel() {
        return transactionTypePanel;
    }

    public CategoryPanel getCategoryPanel() {
        return categoryPanel;
    }

    public DescriptionPanel getDescriptionPanel() {
        return descriptionPanel;
    }

    public HistoryCard getHistoryCard() {
        return historyCard;
    }

    public HistoryCardsPanel getSource() {
        return source;
    }
}
