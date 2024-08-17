package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;

public class AddExpenseFrame extends BaseFrame {
    private String amountEntered;
    private String categorySelected;
    private String descriptionProvided;
    private boolean isExpense;

    private JComboBox<String> categoryComboBox;
    private JCheckBox expenseCheckBox;
    private JCheckBox incomeCheckBox;

    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
        isExpense = true;
    }

    @Override
    protected void addGuiComponents() {
        add(createAddExpenseLabel());
        addAmountEnteringComponents();
        addCategoryComponents();
        addDescriptionComponents();
        addButtons();
    }

    private JLabel createAddExpenseLabel()
    {
        return UIComponentFactory.createLabel(
                "Add new transaction", 0, 20, getWidth() - 10, 50, 30, SwingConstants.CENTER
        );
    }

    private void addAmountEnteringComponents()
    {
        add(new JSeparator());
        add(createAmountLabel());
        JTextField textField = createAmountTextField();
        amountEntered = textField.getText();
        add(textField);
        createTypeCheckingCheckBoxes();
        add(expenseCheckBox);
        add(incomeCheckBox);
    }

    private void addCategoryComponents()
    {
        add(createCategoryLabel());
        categoryComboBox = createCategoryComboBox();
        categorySelected = (String) categoryComboBox.getSelectedItem();
        add(categoryComboBox);
    }

    private void addDescriptionComponents()
    {
        add(createDescriptionLabel());
        JTextArea descriptionTextArea = createDescriptionTextArea();
        descriptionProvided = descriptionTextArea.getText();
        add(descriptionTextArea);
        add(createDescriptionTextArea());
    }

    private void addButtons()
    {
        add(createGoBackButton());
        add(createAddButton());
    }

    private JLabel createAmountLabel()
    {
        return UIComponentFactory.createLabel(
                "Amount", 5, 80, getWidth()-10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createAmountTextField()
    {
        return UIComponentFactory.createTextField(
                5, 120, getWidth() - 10, 40, 20, true
        );
    }

    private void createTypeCheckingCheckBoxes()
    {
        int checkBoxWidth = 140;
        int checkBoxHeight = 30;
        int gap = 10;
        int offset = 20;

        int totalWidth = getWidth() - 2 * gap;
        int centerY = 160;

        int expenseCheckBoxX = (totalWidth / 2) - checkBoxWidth - gap + offset;
        int incomeCheckBoxX = (totalWidth / 2) + gap + offset;

        expenseCheckBox = UIComponentFactory.createCheckBox(
                "Expense", expenseCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, true
        );
        incomeCheckBox = UIComponentFactory.createCheckBox(
                "Income", incomeCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, false
        );

        expenseCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = true;
                incomeCheckBox.setSelected(false);
                updateCategories();
            }
        });

        incomeCheckBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                isExpense = false;
                expenseCheckBox.setSelected(false);
                updateCategories();
            }
        });
    }

    private JLabel createCategoryLabel()
    {
        return UIComponentFactory.createLabel(
                "Category", 5, 200, getWidth() - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JComboBox<String> createCategoryComboBox()
    {
        String[] categories = isExpense ? createExpenseCategoriesArray() : createIncomeCategoriesArray();
        return  UIComponentFactory.createStringComboBox(
            categories, 5, 240, getWidth() - 10, 40, 20
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

    private void updateCategories()
    {
        String[] categories = isExpense ? createExpenseCategoriesArray() : createIncomeCategoriesArray();
        categoryComboBox.setModel(new DefaultComboBoxModel<>(categories));
        categorySelected = (String) categoryComboBox.getSelectedItem();
    }

    private JLabel createDescriptionLabel()
    {
        return UIComponentFactory.createLabel(
                "Description", 5, 300, getWidth()-10, 40, 26, SwingConstants.LEFT
        );
    }

    private  JTextArea createDescriptionTextArea()
    {
        JTextArea textArea= UIComponentFactory.createTextArea(
                5, 340, getWidth()-10, 120, 20
        );

        textArea.setLineWrap(true);
        textArea.setBorder(BorderFactory.createEmptyBorder());

        return textArea;
    }

    private JButton createGoBackButton()
    {
        JButton button = UIComponentFactory.createButton(
                "Go Back", 5, 500, (getWidth() - 10) / 2, 40, 30
        );
        return button;
    }

    private JButton createAddButton()
    {
        int offset = (getWidth() - 10) / 2; // 205px
        JButton button = UIComponentFactory.createButton(
                "Add", offset+ 5, 500, offset, 40, 30
        );

        return button;
    }
}
