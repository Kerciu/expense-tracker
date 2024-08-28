package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DatePanel extends JPanel {
    private JFormattedTextField dateEnteringTextField;
    private JCheckBox todayCheckBox;
    private LocalDate dateEntered;

    private MaskFormatter maskFormatter;

    public DatePanel(int width) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(0, 5, 0, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;

        add(createDateLabel(width), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.NONE;

        add(createDateTextField(), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.WEST;

        add(createTodayDateCheckBox(), gbc);
    }

    private JLabel createDateLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Date", 5, 0, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createDateTextField()
    {
        maskFormatter = null;

        try {

            maskFormatter = new MaskFormatter("#### - ## - ##");
            maskFormatter.setPlaceholderCharacter('_');
            maskFormatter.setValueClass(LocalDate.class);
            maskFormatter.setValidCharacters("0123456789");

        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        dateEnteringTextField = new JFormattedTextField();
        dateEnteringTextField.setFormatterFactory(new DefaultFormatterFactory(maskFormatter));
        dateEnteringTextField.setColumns(10);
        dateEnteringTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        dateEnteringTextField.setHorizontalAlignment(SwingConstants.CENTER);

        final int FIXED_WIDTH = 50;
        dateEnteringTextField.setPreferredSize(new Dimension(FIXED_WIDTH, dateEnteringTextField.getPreferredSize().height));

        return dateEnteringTextField;
    }

    private JCheckBox createTodayDateCheckBox()
    {
        todayCheckBox = new JCheckBox("Today");
        todayCheckBox.setFont(new Font("Dialog", Font.PLAIN, 20));
        todayCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
        todayCheckBox.addItemListener(createCheckBoxItemListener());
        return todayCheckBox;
    }

    private ItemListener createCheckBoxItemListener()
    {
        return new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (todayCheckBox.isSelected())
                {
                    LocalDate todayDate = LocalDate.now();
                    dateEnteringTextField.setText(todayDate.toString());
                    dateEnteringTextField.setEditable(false);
                    dateEntered = todayDate;
                }
                else {
                    dateEnteringTextField.setText("");
                    dateEnteringTextField.setEditable(true);
                    dateEntered = null;
                }
            }
        };
    }

    public JTextField getDateEnteringTextField() {
        return dateEnteringTextField;
    }

    public JCheckBox getTodayCheckBox() {
        return todayCheckBox;
    }

    public LocalDate getDate() {
        return dateEntered;
    }

    public void setDateText(String DateText) {
        dateEnteringTextField.setText(DateText);
    }

    public void clear() {
        dateEnteringTextField.setText("");
    }
}
