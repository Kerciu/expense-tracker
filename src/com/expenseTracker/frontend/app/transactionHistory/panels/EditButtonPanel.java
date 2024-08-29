package com.expenseTracker.frontend.app.transactionHistory.panels;

import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.backend.utils.TransactionFlowFilter;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditButtonPanel extends JPanel {
    private EditDialog source;

    public EditButtonPanel(EditDialog source, int width) {
        this.source = source;

        setLayout(null);
        addButtons(width);
    }

    private void addButtons(int width)
    {
        add(createCancelButton(width));
        add(createSaveButton(width));
    }

    private JButton createCancelButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Cancel", (width - 10) / 2 + 5, 0, (width - 10) / 2, 40, 30
        );
        button.addActionListener(e -> source.dispose());
        return button;
    }

    private JButton createSaveButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Save", 5, 0, (width - 10) / 2, 40, 30
        );
        button.addActionListener(createSaveButtonActionListener());
        return button;
    }


    private ActionListener createSaveButtonActionListener() {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int transactionId = source.getHistoryCard().getId();

                if (!TransactionFlowFilter.validateAmountEntered(source.getAmountPanel().getAmountEnteringTextField().getText())) {
                    JOptionPane.showMessageDialog(source, "Amount entered must be a positive number!");
                    return;
                }
                BigDecimal amount = TransactionFlowFilter.filterAmountEntered(source.getAmountPanel().getAmountEnteringTextField().getText());

                String type = (source.getTransactionTypePanel().getIsExpense() ? "Expense" : "Income");


                if (!TransactionFlowFilter.validateDateEntered(source.getDatePanel().getDateText())) {
                    JOptionPane.showMessageDialog(source, "Date entered must be in 'yyyy-MM-dd' format!");
                    return;
                }

                LocalDate date = LocalDate.parse(source.getDatePanel().getDateText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                String category = (String) source.getCategoryPanel().getCategoryComboBox().getSelectedItem();
                category = category != null ? category : "Other";
                String description = source.getDescriptionPanel().getDescriptionTextArea().getText();

                try {
                    MySQLConnector.updateTransactionCard(transactionId, amount, type, date, category, description);
                    JOptionPane.showMessageDialog(source, "Transaction updated successfully!");
                    source.getSource().getSource().refreshTransactionFrame();
                    source.getSource().refreshCards();
                    source.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(source, "An error occurred while updating the transaction: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }
}
