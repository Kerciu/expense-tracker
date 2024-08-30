package com.expenseTracker.backend.utils;

import java.time.LocalDate;

public class DateBoundPair {
    private LocalDate first;
    private LocalDate second;

    public DateBoundPair(LocalDate first, LocalDate second)
    {
        this.first = first;
        this.second = second;
    }

    public LocalDate getFirst() {
        return first;
    }

    public LocalDate getSecond() {
        return second;
    }

    public void setFirst(LocalDate first) {
        this.first = first;
    }

    public void setSecond(LocalDate second) {
        this.second = second;
    }
}
