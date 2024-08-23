package com.financeapp.frontend.app.addframe.panels;

import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;
import com.financeapp.frontend.app.MainFrame;
import com.financeapp.frontend.app.addframe.AddExpenseFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;

public class AddExpenseFrameButtonPanel extends JPanel {
    private AddExpenseFrame source;
    private final User user;
    private final JTextField amountEnteringTextField;
    private final JTextArea descriptionTextArea;
    private final JComboBox<String> categoryComboBox;

    public AddExpenseFrameButtonPanel(AddExpenseFrame source, int width) {
        this.source = source;
        this.user = source.getUser();
        this.amountEnteringTextField = source.getAmountPanel().getAmountEnteringTextField();
        this.descriptionTextArea = source.getDescriptionPanel().getDescriptionTextArea();
        this.categoryComboBox = source.getCategoryPanel().getCategoryComboBox();

        setLayout(null);
        addButtons(width);
    }

    private void addButtons(int width)
    {
        add(createGoBackButton(width));
        add(createAddButton(width));
    }

    private JButton createGoBackButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Go Back", 5, 0, (width - 10) / 2, 40, 30
        );
        button.addActionListener(createGoBackButtonActionListener());
        return button;
    }

    private JButton createAddButton(int width)
    {
        int offset = (width - 10) / 2; // 205px
        JButton button = UIComponentFactory.createButton(
                "Add", offset+ 5, 0, offset, 40, 30
        );
        button.addActionListener(createAddTransactionActionListener());
        return button;
    }

    private ActionListener createGoBackButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source.dispose();
                new MainFrame(user).setVisible(true);
            }
        };
    }

    private ActionListener createAddTransactionActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int userId = user.getId();
                if (!validateAmountEntered(amountEnteringTextField.getText())) {
                    JOptionPane.showMessageDialog(source, "Amount entered must be a positive number!");
                    return;
                }
                BigDecimal amount = filterAmountEntered(amountEnteringTextField.getText());

                String type = (source.getTransactionTypePanel().getIsExpense() ? "Expense" : "Income");

                System.out.println("isExpense: " + source.getTransactionTypePanel().getIsExpense());

                String category = (String) categoryComboBox.getSelectedItem();
                category = category != null ? category : "Other";
                String description = descriptionTextArea.getText();

                try {
                    MySQLConnector.insertTransactionIntoDatabase(userId, amount, type, category, description);
                    clearAllTheFieldsUponAdding();
                    JOptionPane.showMessageDialog(source, "Transaction added successfully!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(source, "An error occurred while adding the transaction: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }

    private void clearAllTheFieldsUponAdding()
    {
        amountEnteringTextField.setText("");
        descriptionTextArea.setText("");
    }

    private boolean validateAmountEntered(String amountEntered) {
        if (amountEntered == null || amountEntered.isEmpty())
            return false;

        try {
            BigDecimal amount = new BigDecimal(amountEntered);
            return amount.compareTo(BigDecimal.ZERO) > 0;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    private BigDecimal filterAmountEntered(String amountEntered) {
        if (amountEntered == null || amountEntered.isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            BigDecimal amount = new BigDecimal(amountEntered);
            return amount.setScale(2, RoundingMode.FLOOR);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
