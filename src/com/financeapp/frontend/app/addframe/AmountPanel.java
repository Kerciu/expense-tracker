package com.financeapp.frontend.app.addframe;

import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

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
                "Amount", 5, 80, width - 10, 40, 26, SwingConstants.LEFT
        );
    }

    private JTextField createAmountTextField(int width)
    {
        return UIComponentFactory.createTextField(
                5, 120, width - 10, 40, 20, true
        );
    }

    public String getAmountText() {
        return amountEnteringTextField.getText();
    }

    public void clear() {
        amountEnteringTextField.setText("");
    }
}
