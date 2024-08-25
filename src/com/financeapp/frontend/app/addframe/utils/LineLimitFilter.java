package com.financeapp.frontend.app.addframe.utils;

import com.financeapp.backend.db.PasswordUtils;
import com.financeapp.frontend.app.LimitedDocument;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

public class LineLimitFilter extends DocumentFilter {
    private final int maxLines = 4;

    public LineLimitFilter() { }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attrs) throws BadLocationException
    {
        if (getLineCount(fb.getDocument()) < maxLines || string.equals("\n"))
        {
            super.insertString(fb, offset, string, attrs);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attrs) throws BadLocationException
    {
        if (getLineCount(fb.getDocument()) < maxLines || string.equals("\n"))
        {
            super.replace(fb, offset, length, string, attrs);
        }
    }

    private int getLineCount(Document document) throws BadLocationException
    {
        return document.getDefaultRootElement().getElementCount();
    }
}
