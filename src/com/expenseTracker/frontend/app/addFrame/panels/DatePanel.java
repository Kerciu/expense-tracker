package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.LocalDate;

public class DatePanel extends JPanel {
    private JTextField dateEnteringTextField;
    private JCheckBox todayCheckBox;

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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add(createDateTextField(width), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;

        add(createTodayDateCheckBox(width), gbc);
    }

    private JLabel createDateLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Date", 5, 0, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createDateTextField(int width)
    {
        maskFormatter = null;

        try {

            maskFormatter = new MaskFormatter("# # # # - # # - # #");
            maskFormatter.setPlaceholderCharacter('_');
            maskFormatter.setValueClass(LocalDate.class);
            maskFormatter.setValidCharacters("0123456789");

        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        dateEnteringTextField = new JFormattedTextField(maskFormatter);
        dateEnteringTextField.setColumns(10);
        dateEnteringTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        dateEnteringTextField.setHorizontalAlignment(SwingConstants.LEFT);

        return dateEnteringTextField;
    }

    private JCheckBox createTodayDateCheckBox(int width)
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
                }
                else {
                    dateEnteringTextField.setText("");
                    dateEnteringTextField.setEditable(true);
                }
            }
        };
    }

    public JTextField getDateEnteringTextField() {
        return dateEnteringTextField;
    }

    public String getDateText() {
        return dateEnteringTextField.getText();
    }

    public void setDateText(String DateText) {
        dateEnteringTextField.setText(DateText);
    }

    public void clear() {
        dateEnteringTextField.setText("");
    }
}
