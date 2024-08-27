package com.expenseTracker.frontend.authentication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class PlaceholderTextField extends JTextField {
    private String placeholderText;

    public PlaceholderTextField(String placeholderText)
    {
        this.placeholderText = placeholderText;
        addPlaceholderBehaviour();
    }

    public String getPlaceholderText() {
        return placeholderText;
    }

    public void setPlaceholderText(String placeholderText) {
        this.placeholderText = placeholderText;
    }

    private void addPlaceholderBehaviour()
    {
        this.setText(placeholderText);
        this.setForeground(Color.GRAY);

        this.addFocusListener(
                createFocusListener()
        );
    }

    private FocusAdapter createFocusListener()
    {
        return new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (getText().equals(placeholderText)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholderText);
                    setForeground(Color.GRAY);
                }
            }
        };
    }
}
