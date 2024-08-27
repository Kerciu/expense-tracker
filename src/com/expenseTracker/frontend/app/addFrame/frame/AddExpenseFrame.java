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
        descriptionPanel = new DescriptionPanel(getWidth());
        buttonPanel = new AddExpenseFrameButtonPanel(this, getWidth());
    }

    private void arrangePanels()
    {
        amountPanel.setBounds(0, 80, getWidth(), 80);
        transactionTypePanel.setBounds(0, 160, getWidth(), 40);
        categoryPanel.setBounds(0, 200, getWidth(), 80);
        descriptionPanel.setBounds(0, 280, getWidth(), 220);
        buttonPanel.setBounds(0, 500, getWidth(), 40);
    }

    private void addWelcomingComponents()
    {
        add(createSeparator());
        add(createAddExpenseLabel());
    }

    private JLabel createAddExpenseLabel()
    {
        return UIComponentFactory.createLabel(
                "Add New Transaction", 0, 20, getWidth() - 10, 50, 30, SwingConstants.CENTER
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
