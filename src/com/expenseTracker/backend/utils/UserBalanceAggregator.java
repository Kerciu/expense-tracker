package com.expenseTracker.backend.utils;

import com.expenseTracker.backend.data.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UserBalanceAggregator {
    public enum TransactionType {
        EXPENSE, INCOME
    }

    public static BigDecimal getSummedUserExpenses(List<Transaction> transactionList)
    {
        return getSummedBalance(transactionList, TransactionType.EXPENSE);
    }

    public static BigDecimal getSummedUserIncome(List<Transaction> transactionList)
    {
        return getSummedBalance(transactionList, TransactionType.INCOME);
    }

    private static BigDecimal getSummedBalance(List<Transaction> transactionList, TransactionType type)
    {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Transaction transaction : transactionList)
        {
            if (transaction.getType().equalsIgnoreCase(
                type == TransactionType.EXPENSE ? "Expense" : "Income"
            )) {
                totalBalance = totalBalance.add(transaction.getAmount());
            }
        }

        return totalBalance.setScale(2, RoundingMode.FLOOR);
    }
}
