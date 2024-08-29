package com.expenseTracker.frontend.app.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class DateDocumentFilter extends DocumentFilter {

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException
    {
        if (string == null) {
            return;
        }

        if (calculateNewLengthWhenInserting(fb, string) <= 10 && isValid(fb, offset, string)) {
            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        if (text == null) {
            return;
        }
        if (calculateNewLengthWhenDeleting(fb, length,text) <= 10 && isValid(fb, offset, text)) {
            super.replace(fb, offset, length, text, attrs);
        }
    }

    @Override
    public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
        super.remove(fb, offset, length);
    }

    private boolean isValid(FilterBypass fb, int offset, String text) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        String newText = new StringBuilder(currentText)
                .replace(offset, offset + text.length(), text)
                .toString();

        if (newText.length() > 10) return false;

        for (int i = 0; i < text.length(); ++i)
        {
            char character = text.charAt(i);
            int position = offset + i;

            if ((position == 4 || position == 7) && character != '-') return false;
            else if (position != 4 && position != 7 && !Character.isDigit(character)) return false;
        }

        return true;
    }

    private int calculateNewLengthWhenInserting(FilterBypass fb, String newText) throws BadLocationException {
        String currentText = fb.getDocument().getText(0, fb.getDocument().getLength());
        return currentText.length() + newText.length();
    }

    private int calculateNewLengthWhenDeleting(FilterBypass fb, int length, String newText) throws BadLocationException {
        return calculateNewLengthWhenInserting(fb, newText) - length;
    }
}
