package com.expenseTracker.backend.utils;

public class ExpenseWrapper {
    private static boolean isExpense;

    public static boolean isExpense() {
        return isExpense;
    }

    public static void setIsExpense(boolean isExpense) {
        ExpenseWrapper.isExpense = isExpense;
    }
}
