package com.expenseTracker.backend.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    public static boolean validateDateEntered(String dateEntered) {
        if (dateEntered == null || dateEntered.length() != 10)
            return false;

        try {
            LocalDate localDate = LocalDate.parse(dateEntered, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        }
        catch (DateTimeParseException e) {
            return false;
        }
    }
}
