package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.math.BigDecimal;

public class AddExpenseFrame extends BaseFrame {
    private String amountEntered;
    private String categorySelected;
    private String descriptionProvided;

    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
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
        add(createAmountInstructionLabel());
    }

    private void addCategoryComponents()
    {
        add(createCategoryLabel());
        JComboBox<String> comboBox = createCategoryComboBox();
        categorySelected = (String) comboBox.getSelectedItem();
        add(comboBox);
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
                "Amount", 5, 80, getWidth()-10, 40, 30, SwingConstants.LEFT
        );
    }

    private JTextField createAmountTextField()
    {
        return UIComponentFactory.createTextField(
                5, 120, getWidth() - 10, 40, 20, true
        );
    }

    private JLabel createAmountInstructionLabel()
    {
        String text = "* (negative - expense, positive - income)";
        return UIComponentFactory.createLabel(
                text, 5, 160, getWidth() - 10, 40, 14, SwingConstants.LEFT
        );
    }

    private JLabel createCategoryLabel()
    {
        return UIComponentFactory.createLabel(
                "Category", 5, 200, getWidth() - 10, 40, 30, SwingConstants.LEFT
        );
    }

    private JComboBox<String> createCategoryComboBox()
    {
        String[] categories = createCategoriesArray();
        return  UIComponentFactory.createStringComboBox(
            categories, 5, 240, getWidth() - 10, 40, 20
        );
    }

    private String[] createCategoriesArray()
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

    private JLabel createDescriptionLabel()
    {
        return UIComponentFactory.createLabel(
                "Description", 5, 300, getWidth()-10, 40, 30, SwingConstants.LEFT
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
        JButton button= UIComponentFactory.createButton(
                "Go Back", 5, 500, (getWidth() - 10) / 2, 40, 30
        );
        return button;
    }

    private JButton createAddButton()
    {
        int offset = (getWidth() - 10) / 2; // 205px
        JButton button= UIComponentFactory.createButton(
                "Add", offset+ 5, 500, offset, 40, 30
        );

        return button;
    }
}
