package com.expenseTracker.frontend.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {
    private String placeholderText;
    private boolean centerAligned;

    public PlaceholderTextField() {
        this(null, false);
    }

    public PlaceholderTextField(String placeholderText) {
        this.placeholderText = placeholderText;
        this.centerAligned = false;
    }

    public PlaceholderTextField(String placeholderText, boolean centerAligned) {
        this.placeholderText = placeholderText;
        this.centerAligned = centerAligned;
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

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(super.getDisabledTextColor());

        FontMetrics fontMetrics = g2.getFontMetrics(getFont());
        int textWidth = fontMetrics.stringWidth(placeholderText);
        int textHeight = fontMetrics.getAscent();
        int x;
        int y = (getHeight() + textHeight) / 2 - fontMetrics.getDescent() + 4;

        if (centerAligned) {
            x = (getWidth() - textWidth) / 2;
        } else {
            x = getInsets().left;
        }

        g2.drawString(placeholderText, x, y);
        g2.dispose();
    }

    public boolean isCenterAligned() {
        return centerAligned;
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public void setCenterAligned(boolean centerAligned) {
        this.centerAligned = centerAligned;
        repaint();
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
    }
}
