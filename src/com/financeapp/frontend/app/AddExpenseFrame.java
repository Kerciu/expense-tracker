package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ItemEvent;

public class AddExpenseFrame extends BaseFrame {
    private String amountEntered;
    private String categorySelected;
    private String descriptionProvided;
    private boolean isExpense;

    private JComboBox<String> categoryComboBox;
    private JCheckBox expenseCheckBox;
    private JCheckBox incomeCheckBox;
    private JTextArea descriptionTextArea;
    private JLabel charsRemainingLabel;

    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
        isExpense = false;
    }

    @Override
    protected void addGuiComponents() {
        add(createSeparator());
        add(createAddExpenseLabel());
        addAmountEnteringComponents();
        addCheckBoxes();
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

    private JSeparator createSeparator()
    {
        return UIComponentFactory.createSeparator(15, 70, getWidth() - 30, 10);
    }

    private void addAmountEnteringComponents()
    {
        add(createAmountLabel());
        JTextField textField = createAmountTextField();
        amountEntered = textField.getText();
        add(textField);
    }

    private void addCheckBoxes()
    {
        createTypeCheckingCheckBoxes();
        add(expenseCheckBox);
        add(incomeCheckBox);
    }

    private void addCategoryComponents()
    {
        add(createCategoryLabel());
        categoryComboBox = createCategoryComboBox();
        add(categoryComboBox);
        updateCategories();
        categorySelected = (String) categoryComboBox.getSelectedItem();
    }

    private void addDescriptionComponents()
    {
        add(createDescriptionLabel());
        JTextArea descriptionTextArea = createDescriptionTextArea();
        add(descriptionTextArea);
        add(createDescriptionCharsRemainingLabel());

        if (charsRemainingLabel != null) {
            updateCharsRemainingLabel();
        }
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
                "Expense", expenseCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, false
        );
        incomeCheckBox = UIComponentFactory.createCheckBox(
                "Income", incomeCheckBoxX, centerY, checkBoxWidth, checkBoxHeight, 20, true
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
        if (categoryComboBox != null) {
            String[] categories = isExpense ? createExpenseCategoriesArray() : createIncomeCategoriesArray();
            categoryComboBox.setModel(new DefaultComboBoxModel<>(categories));
            categorySelected = (String) categoryComboBox.getSelectedItem();
        }
    }

    private JLabel createDescriptionLabel()
    {
        return UIComponentFactory.createLabel(
                "Description", 5, 300, getWidth()-10, 40, 26, SwingConstants.LEFT
        );
    }

    private  JTextArea createDescriptionTextArea()
    {
        descriptionTextArea = UIComponentFactory.createTextArea(
                5, 340, getWidth()-10, 120, 20
        );

        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBorder(new LineBorder(Color.GRAY, 1, true));

        int maxChars = 100;
        descriptionTextArea.setDocument(new LimitedDocument(maxChars));
        descriptionTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(AddExpenseFrame.this::updateCharsRemainingLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(AddExpenseFrame.this::updateCharsRemainingLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(AddExpenseFrame.this::updateCharsRemainingLabel);
            }
        });

        return descriptionTextArea;
    }

    private JLabel createDescriptionCharsRemainingLabel()
    {
        int charsRemaining = 100 - descriptionTextArea.getText().length();

        charsRemainingLabel =  UIComponentFactory.createLabel(
                charsRemaining+ " characters remaining",
                getWidth()/2, 460,
                getWidth() / 2 - 10,30,
                14, SwingConstants.RIGHT
        );

        return charsRemainingLabel;
    }

    private void updateCharsRemainingLabel()
    {
        if (charsRemainingLabel != null) {
            int maxChars = 100;
            int charsUsed = descriptionTextArea.getDocument().getLength();
            int charsRemaining = maxChars - charsUsed;
            charsRemainingLabel.setText(charsRemaining + " characters remaining");
        }

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
