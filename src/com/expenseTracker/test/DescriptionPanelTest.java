package com.expenseTracker.test;

import com.expenseTracker.frontend.app.addFrame.panels.DescriptionPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.*;

public class DescriptionPanelTest {

    private DescriptionPanel panel;

    @BeforeEach
    public void setUp() {
        panel = new DescriptionPanel(800);
    }

    @Test
    public void testDescriptionPanelInitialization() {
        assertNotNull(panel.getDescriptionTextArea());
        assertNotNull(panel.getCharsRemainingLabel());
    }

    @Test
    public void testCharsRemainingLabel() {
        JTextArea textArea = panel.getDescriptionTextArea();
        JLabel charsRemainingLabel = panel.getCharsRemainingLabel();
        textArea.setText("Test");
        assertTrue(charsRemainingLabel.getText().contains("96 characters remaining"));
    }
}