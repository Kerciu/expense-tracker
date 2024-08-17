package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class AddExpenseFrame extends BaseFrame {
    public AddExpenseFrame(String title, User user, int width, int height) {
        super(title, user, width, height);
    }

    @Override
    protected void addGuiComponents() {
        add(createAddExpenseLabel());
        add(createAmmountLabel());
    }

    private JLabel createAddExpenseLabel()
    {
        return UIComponentFactory.createLabel(
                "<html><br>Add Expense</html></br><hr>", 0, 20, getWidth() - 10, 40, 18, SwingConstants.CENTER
        );
    }

    private JLabel createAmmountLabel()
    {
        return UIComponentFactory.createLabel(
                "Ammount", 0, 60, getWidth()-10, 40, 18, SwingConstants.LEFT
        );
    }
}
