package com.financeapp.frontend.app;

import javax.swing.*;

public abstract class FinanceAppGUI extends JFrame {
    public FinanceAppGUI(String title) {
        initializeComponents(title);
    }

    private void initializeComponents(String title) {
        setTitle(title);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    protected abstract void addGuiComponents();
}
