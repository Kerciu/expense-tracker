package com.expenseTracker.frontend.app.utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument {
    private int maxChars;
    private String charsAllowed;

    public LimitedDocument(int maxChars) {
        this(maxChars, null);
    }

    public LimitedDocument(int maxChars, String charsAllowed) {

        this.maxChars = maxChars;
        this.charsAllowed = charsAllowed;
    }

    @Override
    public void insertString(int offset, String string, AttributeSet attributeSet) throws BadLocationException
    {
        if (string == null) return;

        if (string.equalsIgnoreCase("\n")) return;

        if (charsAllowed != null && !charsAllowed.isEmpty()) {
            for (char c : string.toCharArray()) {
                if (charsAllowed.indexOf(c) == -1) {
                    return;
                }
            }
        }

        if (getLength() + string.length() <= maxChars) {
            super.insertString(offset, string, attributeSet);
        }
    }
}
