package com.expenseTracker.frontend.app.addFrame.panels;

import com.expenseTracker.frontend.app.utils.AmountDocumentFilter;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import javax.swing.text.PlainDocument;

public class AmountPanel extends JPanel {
    private JTextField amountEnteringTextField;

    public AmountPanel(int width) {
        setLayout(null);
        addAmountEnteringComponents(width);
    }

    public JTextField getAmountEnteringTextField() {
        return amountEnteringTextField;
    }

    private void addAmountEnteringComponents(int width)
    {
        add(createAmountLabel(width));
        amountEnteringTextField = createAmountTextField(width);
        add(amountEnteringTextField);
    }

    private JLabel createAmountLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Amount", 5, 0, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createAmountTextField(int width)
    {
        JTextField amountTextField =  UIComponentFactory.createTextField(
                5, 40, width - 10, 40, 20, true
        );

        PlainDocument plainDocument = (PlainDocument) amountTextField.getDocument();
        plainDocument.setDocumentFilter(new AmountDocumentFilter());

        return amountTextField;
    }

    public String getAmountText() {
        return amountEnteringTextField.getText();
    }

    public void setAmountText(String amountText) {
        amountEnteringTextField.setText(amountText);
    }

    public void clear() {
        amountEnteringTextField.setText("");
    }
}
