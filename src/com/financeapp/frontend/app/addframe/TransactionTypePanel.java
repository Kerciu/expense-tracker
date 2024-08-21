package com.financeapp.frontend.app.addframe;

import com.financeapp.frontend.components.UIComponentFactory;

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
        this.isExpense = false;

        setLayout(null);
        addCheckBoxes(width);
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
        int checkBoxHeight = 30;
        int gap = 10;
        int offset = 20;

        int totalWidth = width - 2 * gap;
        int centerY = 160;

        int expenseCheckBoxX = (totalWidth / 2) - checkBoxWidth - gap + offset;
        int incomeCheckBoxX = (totalWidth / 2) + gap + offset;

        expenseCheckBox = UIComponentFactory.createCheckBox(
                "Expense", expenseCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, false
        );
        incomeCheckBox = UIComponentFactory.createCheckBox(
                "Income", incomeCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, true
        );

        expenseCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = true;
                incomeCheckBox.setSelected(false);
                categoryPanel.updateCategories();
            }
        });

        incomeCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = false;
                expenseCheckBox.setSelected(false);
                categoryPanel.updateCategories();
            }
        });
    }
}
