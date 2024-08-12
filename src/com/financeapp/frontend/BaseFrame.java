package com.financeapp.frontend;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    public BaseFrame(String title)
    {
        initializeComponents(title);
    }

    private void initializeComponents(String title)
    {
        setTitle(title);
        setSize(420, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    protected abstract void addGuiComponents();
}
