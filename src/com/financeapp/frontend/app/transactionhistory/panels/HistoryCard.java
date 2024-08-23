package com.financeapp.frontend.app.transactionhistory.panels;

import com.financeapp.backend.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Objects;

public class HistoryCard extends JPanel {
    private BigDecimal amount;
    private String type;
    private String category;
    private String description;

    public HistoryCard(BigDecimal amount, String type, String category, String description)
    {
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    private void initializeCardLook()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setPreferredSize(new Dimension(400, 100));
    }

    private JLabel createCategoryLabel()
    {
        JLabel categoryLabel = new JLabel(category);
        categoryLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        return categoryLabel;
    }

    private JLabel createAmountLabel()
    {
        String text = (type.equalsIgnoreCase("Expense") ? " - " : " + ") + amount.toString();
        JLabel amountLabel = new JLabel(text);
        amountLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        return amountLabel;
    }

    private JTextArea createDescriptionTextArea()
    {
        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(380, 40));
        return textArea;
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize( new Dimension(80, 24));

        buttonPanel.add(createEditButton());
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(createDeleteButton());

        return buttonPanel;
    }

    private JButton createDeleteButton()
    {
        String deleteButtonIconPath = "assets/images/delete.png";
        JButton button = new JButton(IconLoader.loadIcon(deleteButtonIconPath));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.addActionListener(createDeleteButtonActionListener());
        return button;
    }

    private JButton createEditButton()
    {
        String editButtonIconPath = "assets/images/edit.png";
        JButton button = new JButton(IconLoader.loadIcon(editButtonIconPath));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.addActionListener(createEditButtonActionListener());
        return button;
    }

    private ActionListener createDeleteButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }

    private ActionListener createEditButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }
}
