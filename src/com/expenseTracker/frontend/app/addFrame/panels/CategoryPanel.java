package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;

public class CategoryPanel extends JPanel {
    private JComboBox<String> categoryComboBox;

    public CategoryPanel(int width, boolean isExpense) {
        setLayout(null);
        addCategoryComponents(width, isExpense);
    }

    public JComboBox<String> getCategoryComboBox() {
        return categoryComboBox;
    }

    private void addCategoryComponents(int width, boolean isExpense)
    {
        add(createCategoryLabel(width));
        categoryComboBox = createCategoryComboBox(width, isExpense);
        add(categoryComboBox);
        updateCategories(isExpense);
    }

    private JLabel createCategoryLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Category", 5, 0, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JComboBox<String> createCategoryComboBox(int width, boolean isExpense)
    {
        String[] categories = isExpense ? createExpenseCategoriesArray() : createIncomeCategoriesArray();
        return  UIComponentFactory.createStringComboBox(
                categories, 5, 40, width - 10, 40, 20
        );
    }

    private String[] createExpenseCategoriesArray()
    {
        return new String[] {
                "Food and Drinks",
                "Transport",
                "Accommodation",
                "Entertainment",
                "Health and Beauty",
                "Education",
                "Gifts and Donations",
                "Travel",
                "Insurances",
                "House and Garden",
                "Technology",
                "Other"
        };
    }

    private String[] createIncomeCategoriesArray()
    {
        return new String[]{
                "Salary", "Freelance", "Investments", "Rent Income",
                "Gifts", "Other"
        };
    }

    public void updateCategories(boolean isExpense)
    {
            String[] categories = isExpense ? createExpenseCategoriesArray() : createIncomeCategoriesArray();
            categoryComboBox.setModel(new DefaultComboBoxModel<>(categories));
    }
}
