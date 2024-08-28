package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
        add(createDateTextField(width));
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
        MaskFormatter maskFormatter = null;

        try {

            maskFormatter = new MaskFormatter("####-##-##");
            maskFormatter.setPlaceholderCharacter('_');
            maskFormatter.setValueClass(LocalDate.class);

        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        dateEnteringTextField = UIComponentFactory.createFormattedTextField(
                maskFormatter, 5, 40, width - 10, 40, 20, true
        );
        dateEnteringTextField.setColumns(10);

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
