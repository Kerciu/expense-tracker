package com.expenseTracker.frontend.app.addFrame.frame;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.frontend.app.utils.BaseFrame;
import com.expenseTracker.frontend.app.addFrame.panels.*;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;

public class AddExpenseFrame extends BaseFrame {
    private AmountPanel amountPanel;
    private TransactionTypePanel transactionTypePanel;
    private CategoryPanel categoryPanel;
    private DatePanel datePanel;
    private DescriptionPanel descriptionPanel;
    private AddExpenseFrameButtonPanel buttonPanel;
    private boolean isExpense;

    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);

        initializePanels();
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

        add(amountPanel);
        add(transactionTypePanel);
        add(categoryPanel);
        add(datePanel);
        add(descriptionPanel);
        add(buttonPanel);

        revalidate();
        repaint();
    }

    private void initializePanels()
    {
        amountPanel = new AmountPanel(getWidth());
        categoryPanel = new CategoryPanel(getWidth(), true);
        transactionTypePanel = new TransactionTypePanel(categoryPanel, getWidth());
        datePanel = new DatePanel(getWidth());
        descriptionPanel = new DescriptionPanel(getWidth());
        buttonPanel = new AddExpenseFrameButtonPanel(this, getWidth());
    }

    private void arrangePanels()
    {
        amountPanel.setBounds(0, 60, getWidth(), 80);
        transactionTypePanel.setBounds(0, 140, getWidth(), 30);
        categoryPanel.setBounds(0, 170, getWidth(), 80);
        datePanel.setBounds(0, 250, getWidth(), 80);
        descriptionPanel.setBounds(0, 330, getWidth(), 190);
        buttonPanel.setBounds(0, 520, getWidth(), 40);
    }

    private void addWelcomingComponents()
    {
        add(createSeparator());
        add(createAddExpenseLabel());
    }

    private JLabel createAddExpenseLabel()
    {
        return UIComponentFactory.createLabel(
                "Add New Transaction", 0, 0, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
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

    public DatePanel getDatePanel() { return datePanel; }

    public DescriptionPanel getDescriptionPanel() {
        return descriptionPanel;
    }

    public AddExpenseFrameButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
