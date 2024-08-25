package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.backend.db.MySQLConnector;
import com.financeapp.backend.utils.TransactionFlowFilter;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;

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

                System.out.println("isExpense: " + source.getTransactionTypePanel().getIsExpense());

                String category = (String) source.getCategoryPanel().getCategoryComboBox().getSelectedItem();
                category = category != null ? category : "Other";
                String description = source.getDescriptionPanel().getDescriptionTextArea().getText();

                try {
                    MySQLConnector.updateTransactionCard(transactionId, amount, type, category, description);
                    JOptionPane.showMessageDialog(source, "Transaction updated successfully!");
                    source.getSource().updatePanel();
                    source.dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(source, "An error occurred while updating the transaction: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
    }
}
