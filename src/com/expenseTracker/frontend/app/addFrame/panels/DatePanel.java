package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.app.utils.DateDocumentFilter;
import com.expenseTracker.frontend.authentication.PlaceholderTextField;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

public class DatePanel extends JPanel {
    private PlaceholderTextField dateEnteringTextField;
    private JCheckBox todayCheckBox;

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
        dateEnteringTextField = new PlaceholderTextField("YYYY-MM-DD", true);
        dateEnteringTextField.setColumns(10);
        dateEnteringTextField.setFont(new Font("Dialog", Font.PLAIN, 20));
        dateEnteringTextField.setHorizontalAlignment(SwingConstants.CENTER);

        final int FIXED_WIDTH = 50;
        dateEnteringTextField.setPreferredSize(new Dimension(FIXED_WIDTH, dateEnteringTextField.getPreferredSize().height));

        PlainDocument plainDocument = (PlainDocument) dateEnteringTextField.getDocument();
        plainDocument.setDocumentFilter(new DateDocumentFilter());

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

    public JCheckBox getTodayCheckBox() {
        return todayCheckBox;
    }

    public void setDateText(String DateText) {
        dateEnteringTextField.setText(DateText);
    }

    public void clear() {
        dateEnteringTextField.setText("");
    }
}
