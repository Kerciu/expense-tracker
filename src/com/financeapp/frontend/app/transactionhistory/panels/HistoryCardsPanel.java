package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryCardsPanel extends JPanel {
    private User user;
    private ResultSet resultSet;

    public HistoryCardsPanel(User user)
    {
        this.user = user;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            fetchTransactionHistoryInformation(user);
            addTransactionHistoryCards();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load transaction history.", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public User getUser() {
        return user;
    }

    private void addTransactionHistoryCards() throws SQLException {
        // amount, type, category, description
        while (resultSet.next()) {
            BigDecimal amount = resultSet.getBigDecimal("amount");
            String type = resultSet.getString("type");
            String category = resultSet.getString("category");
            String description = resultSet.getString("description");

            add(new HistoryCard(this, amount, type, category, description));
        }
    }

    private void fetchTransactionHistoryInformation(User user) throws SQLException
    {
        resultSet = MySQLConnector.getTransactionHistoryDetailsForCards(user);
    }
}
