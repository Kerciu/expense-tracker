package com.expenseTracker.test;

import com.expenseTracker.frontend.app.addFrame.panels.CategoryPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryPanelTest {

    private CategoryPanel panel;

    @BeforeEach
    public void setUp() {
        panel = new CategoryPanel(800, true);
    }

    @Test
    public void testCategoryPanelInitialization() {
        assertNotNull(panel.getCategoryComboBox());
        assertEquals(12, panel.getCategoryComboBox().getItemCount()); // Number of expense categories
    }

    @Test
    public void testUpdateCategories() {
        panel.updateCategories(false); // Update for income
        assertEquals(6, panel.getCategoryComboBox().getItemCount()); // Number of income categories
    }
}
