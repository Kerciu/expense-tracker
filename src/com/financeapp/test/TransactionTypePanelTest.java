package com.financeapp.test;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.app.addframe.AddExpenseFrame;
import com.financeapp.frontend.app.addframe.panels.CategoryPanel;
import com.financeapp.frontend.app.addframe.panels.TransactionTypePanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTypePanelTest {

    private TransactionTypePanel panel;
    private CategoryPanel mockCategoryPanel;

    @BeforeEach
    public void setUp() {
        mockCategoryPanel = new CategoryPanel(800, true);
        panel = new TransactionTypePanel(new AddExpenseFrame("abc", new User(10, "abc", "abc", new BigDecimal(10)), 500, 500),mockCategoryPanel, 800);
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