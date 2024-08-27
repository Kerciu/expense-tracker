package com.expenseTracker.backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionFlowFilter {
    public static boolean validateAmountEntered(String amountEntered) {
        if (amountEntered == null || amountEntered.isEmpty())
            return false;

        try {
            BigDecimal amount = new BigDecimal(amountEntered);
            return amount.compareTo(BigDecimal.ZERO) > 0;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }

    public static BigDecimal filterAmountEntered(String amountEntered) {
        if (amountEntered == null || amountEntered.isEmpty()) {
            return BigDecimal.ZERO;
        }

        try {
            BigDecimal amount = new BigDecimal(amountEntered);
            return amount.setScale(2, RoundingMode.FLOOR);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }
}
