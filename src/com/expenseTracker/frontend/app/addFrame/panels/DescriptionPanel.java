package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.app.utils.LimitedDocument;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class DescriptionPanel extends JPanel {
    private JTextArea descriptionTextArea;
    private JLabel charsRemainingLabel;

    public DescriptionPanel(int width)
    {
        setLayout(null);
        addDescriptionComponents(width);
    }

    public JTextArea getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public JLabel getCharsRemainingLabel() {
        return charsRemainingLabel;
    }

    private void addDescriptionComponents(int width)
    {
        add(createDescriptionLabel(width));
        JTextArea descriptionTextArea = createDescriptionTextArea(width);
        add(descriptionTextArea);
        add(createDescriptionCharsRemainingLabel(width));

        if (charsRemainingLabel != null) {
            updateCharsRemainingLabel();
        }
    }

    private JLabel createDescriptionLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Description", 5, 0, width-10, 40, 26, SwingConstants.LEFT
        );
    }

    private  JTextArea createDescriptionTextArea(int width)
    {
        descriptionTextArea = UIComponentFactory.createTextArea(
                5, 40, width-10, 120, 20
        );

        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        descriptionTextArea.setBorder(new LineBorder(Color.GRAY, 1, true));

        int maxChars = 100;
        descriptionTextArea.setDocument(new LimitedDocument(maxChars));
        descriptionTextArea.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(DescriptionPanel.this::updateCharsRemainingLabel);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(DescriptionPanel.this::updateCharsRemainingLabel);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(DescriptionPanel.this::updateCharsRemainingLabel);
            }
        });

        return descriptionTextArea;
    }

    private JLabel createDescriptionCharsRemainingLabel(int width)
    {
        int charsRemaining = 100 - descriptionTextArea.getText().length();

        charsRemainingLabel =  UIComponentFactory.createLabel(
                charsRemaining+ " characters remaining",
                width/2, 160,
                width / 2 - 10,30,
                14, SwingConstants.RIGHT
        );

        return charsRemainingLabel;
    }

    private void updateCharsRemainingLabel()
    {
        if (charsRemainingLabel != null) {
            int maxChars = 100;
            int charsUsed = descriptionTextArea.getDocument().getLength();
            int charsRemaining = maxChars - charsUsed;
            charsRemainingLabel.setText(charsRemaining + " characters remaining");
        }

    }
}
