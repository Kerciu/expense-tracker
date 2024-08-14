package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    private User user;

    public BaseFrame(String title, int width, int height) {
        initializeComponents(title, width, height);
    }

    public BaseFrame(String title, User user, int width, int height) {
        this.user = user;
        initializeComponents(title, width, height);
    }

    private void initializeComponents(String title, int width, int height) {
        setTitle(title);
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);
        addGuiComponents();
    }

    protected abstract void addGuiComponents();
}
