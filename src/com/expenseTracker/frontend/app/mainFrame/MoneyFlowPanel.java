package com.expenseTracker.frontend.app.mainFrame;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.backend.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import static com.expenseTracker.backend.utils.UserBalanceAggregator.*;

public class MoneyFlowPanel extends JPanel {
    private MainFrame source;

    public MoneyFlowPanel(MainFrame source)
    {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        List<Transaction> transactionList = null;
        try {
            transactionList = MySQLConnector.getTransactionHistoryDetails(source.getUser());
            gbc.gridx = 0;
            gbc.gridy = 0;
            add(createIconLabel("expense"), gbc);

            gbc.gridx = 1;
            add(createIconLabel("income"), gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            add(createMoneyFlowLabel(TransactionType.EXPENSE, transactionList), gbc);

            gbc.gridx = 1;
            add(createMoneyFlowLabel(TransactionType.INCOME, transactionList), gbc);
        } catch(SQLException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Error loading data");
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            add(errorLabel, gbc);
        }
    }

    public JLabel createIconLabel(String iconName)
    {
        String filePath = "/resources/assets/images/"+iconName + "-icon.png";
        ImageIcon imageIcon = IconLoader.loadIcon(filePath);
        return new JLabel(imageIcon);
    }

    public JLabel createMoneyFlowLabel(TransactionType type, List<Transaction> transactionList) {
        String text = (type == TransactionType.EXPENSE
            ?
          " - " + getSummedUserExpenses(transactionList).toString()
            :
          " + " + getSummedUserIncome(transactionList).toString()
        );

        JLabel moneyFlowLabel = new JLabel(text);
        moneyFlowLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        moneyFlowLabel.setForeground(type == TransactionType.EXPENSE ? new Color(0x800000) : new Color(0x008000));
        moneyFlowLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return moneyFlowLabel;
    }
}
