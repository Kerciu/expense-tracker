package com.expenseTracker.frontend.app.transactionHistory.panels;

import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.backend.utils.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;

public class HistoryCard extends JPanel {
    private HistoryCardsPanel source;
    private int id;
    private BigDecimal amount;
    private String type;
    private LocalDate date;
    private String category;
    private String description;

    public HistoryCard(HistoryCardsPanel source, int id, BigDecimal amount, String type, LocalDate date, String category, String description)
    {
        this.id = id;
        this.source = source;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.category = category;
        this.description = description;

        initializeCardLook();
        addComponents();
    }

    private void initializeCardLook()
    {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        int cardWidth = source.getWidth() - 40;
        setPreferredSize(new Dimension(cardWidth, 100));
        setMaximumSize(new Dimension(cardWidth, 100));
    }

    private void addComponents() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(2, 5, 2, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        contentPanel.add(createCategoryLabel(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(createButtonPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(createAmountLabel(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(createDateLabel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(createDescriptionTextArea(), gbc);

        add(contentPanel, BorderLayout.CENTER);
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

    private JLabel createDateLabel()
    {
        String text = date.toString();
        JLabel dateLabel = new JLabel(text);
        dateLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
        return dateLabel;
    }

    private JTextArea createDescriptionTextArea()
    {
        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Dialog", Font.PLAIN, 12));
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setPreferredSize(new Dimension(380, 40));
        textArea.setMaximumSize(new Dimension(380, 40));
        return textArea;
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setPreferredSize( new Dimension(80, 24));

        buttonPanel.add(createEditButton());
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonPanel.add(createDeleteButton());

        return buttonPanel;
    }

    private JButton createDeleteButton()
    {
        String deleteButtonIconPath = "/resources/assets/images/delete.png";
        JButton button = new JButton(IconLoader.loadIcon(deleteButtonIconPath));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(24, 24));
        button.setMinimumSize(new Dimension(24, 24));
        button.setMaximumSize(new Dimension(24, 24));
        button.addActionListener(createDeleteButtonActionListener());
        return button;
    }

    private JButton createEditButton()
    {
        String editButtonIconPath = "/resources/assets/images/edit.png";
        JButton button = new JButton(IconLoader.loadIcon(editButtonIconPath));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(24, 24));
        button.setMinimumSize(new Dimension(24, 24));
        button.setMaximumSize(new Dimension(24, 24));
        button.addActionListener(createEditButtonActionListener());
        return button;
    }

    private ActionListener createDeleteButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(source, "Do you really want to delete this card?");
                switch(result) {
                    case JOptionPane.YES_OPTION -> {
                        try {

                        MySQLConnector.deleteTransactionHistoryCard(id);
                        JOptionPane.showMessageDialog(source, "Card deleted successfully!");

                        source.removeCard(HistoryCard.this);

                        } catch(SQLException e1) {
                            e1.printStackTrace();
                            JOptionPane.showMessageDialog(source, "Error occurred while deleting a card!");
                        }
                     }
                    case JOptionPane.NO_OPTION, JOptionPane.CANCEL_OPTION, JOptionPane.CLOSED_OPTION -> {
                        // do nothing
                    }
                }
            }
        };
    }

    private ActionListener createEditButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EditDialog(source, HistoryCard.this).setVisible(true);
                source.refreshCards();
            }
        };
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDate() { return date; }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }
}
