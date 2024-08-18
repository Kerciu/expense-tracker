package com.financeapp.backend.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Transaction {
    private final int id;
    private final int userId;
    private BigDecimal amount;
    private String type;
    private String category;
    private String description;

    public Transaction(int id, int userId, BigDecimal amount, String type, String category, String description)
    {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount.setScale(2, RoundingMode.FLOOR);
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
