package com.financeapp.frontend.app;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument {
    private int maxChars;

    public LimitedDocument(int maxChars) {
        this.maxChars = maxChars;
    }

    @Override
    public void insertString(int offset, String string, AttributeSet attributeSet) throws BadLocationException
    {
        if (string == null) return;

        if (getLength() + string.length() <= maxChars) {
            super.insertString(offset, string, attributeSet);
        }
    }
}
