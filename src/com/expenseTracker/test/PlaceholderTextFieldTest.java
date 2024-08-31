package com.expenseTracker.test;

import com.expenseTracker.frontend.authentication.PlaceholderTextField;
import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import static org.junit.Assert.assertEquals;

public class PlaceholderTextFieldTest {
    @Test
    public void testPlaceholderInitialization() {
        PlaceholderTextField textField = new PlaceholderTextField("Enter your username..");

        assertEquals("Enter your username..", textField.getText());
    }

    @Test
    public void testPlaceholderDisappearsOnFocus() {
        PlaceholderTextField textField = new PlaceholderTextField("Enter your username..");

        FocusListener focusListener = new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                assertEquals("", textField.getText());
                assertEquals(Color.BLACK, textField.getForeground());
            }
        };

        textField.addFocusListener(focusListener);

        textField.requestFocusInWindow();

        SwingUtilities.invokeLater(() -> {
            assertEquals("", textField.getText());
            assertEquals(Color.BLACK, textField.getForeground());
        });
    }

    @Test
    public void testPlaceholderReappearsOnFocusLost() {
        PlaceholderTextField textField = new PlaceholderTextField("Enter your username..");

        FocusEvent gainFocusEvent = new FocusEvent(textField, FocusEvent.FOCUS_GAINED);
        textField.dispatchEvent(gainFocusEvent);

        FocusEvent lostFocusEvent = new FocusEvent(textField, FocusEvent.FOCUS_LOST);
        textField.dispatchEvent(lostFocusEvent);

        assertEquals("Enter your username..", textField.getText());
        assertEquals(textField.getForeground(), Color.GRAY);
    }
}
