package com.expenseTracker.frontend.app.transactionHistory.panels;

import com.expenseTracker.backend.data.Transaction;
import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.frontend.app.transactionHistory.frame.TransactionHistoryFrame;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class HistoryCardsPanel extends JPanel {
    private TransactionHistoryFrame source;
    private User user;

    public HistoryCardsPanel(TransactionHistoryFrame source, User user)
    {
        this.source = source;
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
            add(new HistoryCard(this, t.getId(), t.getAmount(), t.getType(), t.getDate(), t.getCategory(), t.getDescription()));
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

    public void refreshCards()
    {
        removeAll();
        try {
            addTransactionHistoryCards();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load transaction history.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        updatePanel();
    }

    private List<Transaction> fetchTransactionHistoryInformation(User user) throws SQLException
    {
        return MySQLConnector.getTransactionHistoryDetails(user);
    }

    public TransactionHistoryFrame getSource() {
        return source;
    }
}
