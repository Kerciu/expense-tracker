package com.expenseTracker.test;

import com.expenseTracker.frontend.app.addFrame.panels.CategoryPanel;
import com.expenseTracker.frontend.app.addFrame.panels.TransactionTypePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTypePanelTest {

    private TransactionTypePanel panel;
    private CategoryPanel mockCategoryPanel;

    @BeforeEach
    public void setUp() {
        mockCategoryPanel = new CategoryPanel(800, true);
        panel = new TransactionTypePanel(mockCategoryPanel, 800);
    }

    @Test
    public void testCheckBoxesInitialization() {
        assertNotNull(panel.getExpenseCheckBox());
        assertNotNull(panel.getIncomeCheckBox());
    }

    @Test
    public void testItemSelection() {
        JCheckBox expenseCheckBox = panel.getExpenseCheckBox();
        JCheckBox incomeCheckBox = panel.getIncomeCheckBox();

        expenseCheckBox.setSelected(true);
        //assertTrue(panel.getIsExpense());

        incomeCheckBox.setSelected(true);
        assertFalse(panel.getIsExpense());
    }
}