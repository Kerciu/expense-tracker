package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.backend.utils.ExpenseWrapper;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class TransactionTypePanel extends JPanel {
    private JCheckBox expenseCheckBox;
    private JCheckBox incomeCheckBox;
    private CategoryPanel categoryPanel;
    private boolean isExpense;

    public TransactionTypePanel(CategoryPanel categoryPanel, int width)
    {
        this.categoryPanel = categoryPanel;

        setLayout(null);
        addCheckBoxes(width);
    }

    public JCheckBox getExpenseCheckBox() {
        return expenseCheckBox;
    }

    public JCheckBox getIncomeCheckBox() {
        return incomeCheckBox;
    }

    private void addCheckBoxes(int width)
    {
        createTypeCheckingCheckBoxes(width);
        add(expenseCheckBox);
        add(incomeCheckBox);
    }

    private void createTypeCheckingCheckBoxes(int width)
    {
        int checkBoxWidth = 140;
        int gap = 10;
        int offset = 20;

        int totalWidth = width - 2 * gap;

        int expenseCheckBoxX = (totalWidth / 2) - checkBoxWidth - gap + offset;
        int incomeCheckBoxX = (totalWidth / 2) + gap + offset;

        isExpense = true;
        ExpenseWrapper.setIsExpense(true);

        expenseCheckBox = UIComponentFactory.createCheckBox(
                "Expense", expenseCheckBoxX, 0, checkBoxWidth, 30, 20, true
        );
        incomeCheckBox = UIComponentFactory.createCheckBox(
                "Income", incomeCheckBoxX, 0, checkBoxWidth, 30, 20, false
        );

        expenseCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = true;
                incomeCheckBox.setSelected(false);
                categoryPanel.updateCategories(this.isExpense);
                ExpenseWrapper.setIsExpense(true);
            }
        });

        incomeCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = false;
                expenseCheckBox.setSelected(false);
                categoryPanel.updateCategories(this.isExpense);
                ExpenseWrapper.setIsExpense(false);
            }
        });
    }

    public boolean getIsExpense() {
        return  isExpense;
    }

    public void setExpense(boolean expense) {
        isExpense = expense;
    }
}
