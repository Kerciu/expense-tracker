package com.expenseTracker.frontend.app.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class AmountDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        if (string == null) {
            return;
        }

        if (isValid(fb, offset, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }
        if (isValid(fb, offset, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValid(FilterBypass fb, int offset, String text) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(currentText).replace(offset, offset + text.length(), text).toString();

        return !isInvalidLeadingZero(newText) && !hasMoreThanOneDot(newText)
                && !hasMoreThanTwoDecimalPlaces(newText) && allCharsAreDigitsOrDot(newText);
    }

    private boolean isInvalidLeadingZero(String text)
    {
        return text.matches("^0[^.]");
    }

    private boolean hasMoreThanOneDot(String text)
    {
        return text.chars().filter(c -> c == '.').count() > 1;
    }

    private boolean hasMoreThanTwoDecimalPlaces(String text)
    {
        int dotIndex = text.indexOf('.');
        return dotIndex != -1 && text.length() - dotIndex - 1 > 2;
    }

    private boolean allCharsAreDigitsOrDot(String text)
    {
        return text.matches("[0-9.]*");
    }
}
