package com.financeapp.frontend.app.transactionHistory.panels;

import com.financeapp.backend.data.Transaction;
import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class HistoryCardsPanel extends JPanel {
    private User user;

    public HistoryCardsPanel(User user)
    {
        this.user = user;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            addTransactionHistoryCards();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load transaction history.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User getUser() {
        return user;
    }

    private void addTransactionHistoryCards() throws SQLException {
        List<Transaction> transactions = fetchTransactionHistoryInformation(user);

        // amount, type, category, description
        for (Transaction t : transactions) {
            add(new HistoryCard(this, t.getId(), t.getAmount(), t.getType(), t.getCategory(), t.getDescription()));
        }

        updatePanel();
    }

    public void updatePanel()
    {
        revalidate();
        repaint();
    }

    public void removeCard(HistoryCard historyCard)
    {
        remove(historyCard);
        updatePanel();
    }

    private List<Transaction> fetchTransactionHistoryInformation(User user) throws SQLException
    {
        return MySQLConnector.getTransactionHistoryDetails(user);
    }
}
