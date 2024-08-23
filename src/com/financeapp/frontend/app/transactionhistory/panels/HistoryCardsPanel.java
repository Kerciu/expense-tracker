package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryCardsPanel extends JPanel {
    private ResultSet resultSet;

    public HistoryCardsPanel(User user)
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        try {
            fetchTransactionHistoryInformation(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addTransactionHistoryCards();
    }

    private void addTransactionHistoryCards()
    {

    }

    private void fetchTransactionHistoryInformation(User user) throws SQLException
    {
        resultSet = MySQLConnector.getTransactionHistoryDetailsForCards(user);
    }
}
