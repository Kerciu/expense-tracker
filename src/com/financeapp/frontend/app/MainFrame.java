package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;

import javax.swing.*;

public class MainFrame extends BaseFrame {
    public MainFrame(User user)
    {
        super("Finance Manager", user, 800, 600);
    }

    @Override
    protected void addGuiComponents() {
        add(createWelcomeMessage());
        add(createBalancePanel());
    }

    private JLabel createWelcomeMessage()
    {
        return new JLabel();
    }

    private JLabel createBalancePanel()
    {
        return new JLabel();
    }
}
