package com.financeapp.backend.utils;

import com.financeapp.backend.data.Transaction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class UserBalanceAggregator {
    private enum SumType {
        EXPENSE, INCOME
    }

    public static BigDecimal getSummedUserExpenses(List<Transaction> transactionList)
    {
        return getSummedBalance(transactionList, SumType.EXPENSE);
    }

    public static BigDecimal getSummedUserIncome(List<Transaction> transactionList)
    {
        return getSummedBalance(transactionList, SumType.INCOME);
    }

    public static BigDecimal getSummedBalance(List<Transaction> transactionList, SumType type)
    {
        BigDecimal totalBalance = BigDecimal.ZERO;
        for (Transaction transaction : transactionList)
        {
            if (transaction.getType().equalsIgnoreCase(
                type == SumType.EXPENSE ? "Expense" : "Income"
            )) {
                totalBalance = totalBalance.add(transaction.getAmount());
            }
        }

        return totalBalance.setScale(2, RoundingMode.FLOOR);
    }
}
