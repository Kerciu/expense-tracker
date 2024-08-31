package com.expenseTracker.frontend.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {
    private String placeholderText;

    public PlaceholderTextField(String placeholderText) {
        this.placeholderText = placeholderText;
    }

    public PlaceholderTextField() {
        this.placeholderText = null;
    }

    @Override
    public String getText() {
        String text = super.getText();

        if (text.trim().isEmpty() && placeholderText != null) {
            text = placeholderText;
        }

        return text;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!super.getText().isEmpty() || placeholderText == null) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(super.getDisabledTextColor());
        g2.drawString(placeholderText, getInsets().left, g.getFontMetrics().getMaxAscent() + getInsets().top);
    }
}
