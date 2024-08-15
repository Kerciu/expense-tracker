package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    public MainFrame(User user)
    {
        super("Finance Manager", user, 800, 600);
    }

    @Override
    protected void addGuiComponents() {
        add(UIComponentFactory.createLabel(
                "<html><body style='text-align:center'><b>Welcome " + user.getUsername() + "!</b><br>What would you like to do?</body></html>",
                0, 20, getWidth() - 10, 40, 18, SwingConstants.CENTER
        ));
        add(UIComponentFactory.createLabel("Current Balance", 0, 80, getWidth() - 10, 30, 22, SwingConstants.CENTER));
        add(UIComponentFactory.createTextField(
                user.getBalance() + " " + getCurrentCurrency(),
                15, 120, getWidth() - 50, 40, 28, false
        ));
        add(UIComponentFactory.createButton("Deposit", 15, 180, getWidth() - 10, 30, 22));
        add(UIComponentFactory.createButton("Withdraw", 15, 250, getWidth() - 10, 30, 22));
        add(UIComponentFactory.createButton("Transactions History", 15, 320, getWidth() - 10, 30, 22));
        add(UIComponentFactory.createButton("Generate Report", 15, 390, getWidth() - 10, 30, 22));
        add(UIComponentFactory.createButton("Logout", 15, 500, getWidth() - 10, 30, 22));
    }

    private String getCurrentCurrency()
    {
        // for now, TODO fetch currency converter API
        return "USD";
    }
}
