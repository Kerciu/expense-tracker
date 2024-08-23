package com.financeapp.frontend.app.transactionhistory.panels;

import javax.swing.*;
import java.math.BigDecimal;

public class HistoryCard extends JPanel {
    private BigDecimal amount;
    private String type;
    private String category;
    private String description;

    public HistoryCard(BigDecimal amount, String type, String category, String description)
    {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
    }
}
