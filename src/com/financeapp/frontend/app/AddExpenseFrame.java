package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class AddExpenseFrame extends BaseFrame {
    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
    }

    @Override
    protected void addGuiComponents() {
        add(createAddExpenseLabel());
        addAmountEnteringComponents();
        addCategoryComponents();
        addDescriptionComponents();
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
        add(createAmountTextField());
        add(createAmountInstructionLabel());
    }

    private void addCategoryComponents()
    {
        add(createCategoryLabel());
        add(createCategoryComboBox());
    }

    private void addDescriptionComponents()
    {
        add(createDescriptionLabel());
        add(createDescriptionTextField());
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
            categories, 5, 240, getWidth() - 10, 40, 30
        );
    }

    private String[] createCategoriesArray()
    {
        return new String[] {

        };
    }

    private JLabel createDescriptionLabel()
    {
        return UIComponentFactory.createLabel(
                "Description", 5, 300, getWidth()-10, 40, 30, SwingConstants.LEFT
        );
    }

    private  JTextField createDescriptionTextField()
    {
        return UIComponentFactory.createTextField(
                5, 340, getWidth()-10, 40, 20, true
        );
    }
}
