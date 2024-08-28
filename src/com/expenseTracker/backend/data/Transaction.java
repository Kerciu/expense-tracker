package com.expenseTracker.backend.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private final int id;
    private final int userId;
    private BigDecimal amount;
    private LocalDate date;
    private String type;
    private String category;
    private String description;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Transaction(int id, int userId, BigDecimal amount, String type, String category, String description)
    {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.date = LocalDate.now();
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Transaction(int id, int userId, BigDecimal amount, LocalDate date, String type, String category, String description)
    {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
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

    public LocalDate getDate()
    {
        return date;
    }

    public String getFormattedDate()
    {
        return date.format(DATE_FORMATTER);
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

    public void setDate(LocalDate date)
    {
        this.date = date;
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
