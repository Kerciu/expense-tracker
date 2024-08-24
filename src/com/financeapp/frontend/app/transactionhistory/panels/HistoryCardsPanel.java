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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addTransactionHistoryCards();
    }

    public User getUser() {
        return user;
    }

    private void addTransactionHistoryCards()
    {
        add(new HistoryCard(this, new BigDecimal(50), "Expense", "Food", "I bought kebab and went clubbing after it"));
        add(new HistoryCard(this, new BigDecimal(120), "Income", "Investing", "I invested in crypto"));
        add(new HistoryCard(this, new BigDecimal(170), "Expense", "Transport", "I went to Berlin for a weekendwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww"));
    }

    private void fetchTransactionHistoryInformation(User user) throws SQLException
    {
        resultSet = MySQLConnector.getTransactionHistoryDetailsForCards(user);
    }
}
