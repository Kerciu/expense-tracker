package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;

public class DatePanel extends JPanel {
    private JTextField dateEnteringTextField;

    public DatePanel(int width) {
        setLayout(null);
        addDateEnteringComponents(width);
    }

    public JTextField getDateEnteringTextField() {
        return dateEnteringTextField;
    }

    private void addDateEnteringComponents(int width)
    {
        add(createDateLabel(width));
        dateEnteringTextField = createDateTextField(width);
        add(dateEnteringTextField);
    }

    private JLabel createDateLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Date", 5, 0, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createDateTextField(int width)
    {
        return UIComponentFactory.createTextField(
                5, 40, width - 10, 40, 20, true
        );
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
