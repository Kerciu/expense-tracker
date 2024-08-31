package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.backend.utils.ExpenseWrapper;
import com.expenseTracker.backend.utils.TransactionFlowFilter;
import com.expenseTracker.frontend.app.mainFrame.MainFrame;
import com.expenseTracker.frontend.app.addFrame.frame.AddExpenseFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddExpenseFrameButtonPanel extends JPanel {
    private AddExpenseFrame source;
    private final User user;
    private final JTextField amountEnteringTextField;
    private final JTextField dateEnteredTextField;
    private final JTextArea descriptionTextArea;
    private final JComboBox<String> categoryComboBox;

    public AddExpenseFrameButtonPanel(AddExpenseFrame source, int width) {
        this.source = source;
        this.user = source.getUser();
        this.amountEnteringTextField = source.getAmountPanel().getAmountEnteringTextField();
        this.dateEnteredTextField = source.getDatePanel().getDateEnteringTextField();
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
                if (!TransactionFlowFilter.validateAmountEntered(amountEnteringTextField.getText())) {
                    JOptionPane.showMessageDialog(source, "Amount entered must be a positive number!");
                    return;
                }
                BigDecimal amount = TransactionFlowFilter.filterAmountEntered(amountEnteringTextField.getText());

                String type = (ExpenseWrapper.isExpense() ? "Expense" : "Income");

                String category = (String) categoryComboBox.getSelectedItem();
                category = category != null ? category : "Other";

                String dateString = dateEnteredTextField.getText();

                if (!TransactionFlowFilter.validateDateEntered(dateString)) {
                    JOptionPane.showMessageDialog(source, "Date entered must be as 'yyyy-MM-dd' !");
                    return;
                }

                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                String description = descriptionTextArea.getText();

                try {
                    MySQLConnector.insertTransactionIntoDatabase(userId, amount, type, category, date, description);
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
        dateEnteredTextField.setText("");
    }
}
