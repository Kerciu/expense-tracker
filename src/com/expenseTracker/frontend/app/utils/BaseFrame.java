package com.expenseTracker.frontend.app.utils;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;

public abstract class BaseFrame extends JFrame {
    protected User user;

    public BaseFrame(String title, int width, int height) {
        initializeComponents(title, width, height);
    }

    public BaseFrame(String title, User user, int width, int height) {
        this.user = user;
        initializeComponents(title, width, height);
    }

    public User getUser() {
        return user;
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

    protected JSeparator createSeparator()
    {
        return UIComponentFactory.createSeparator(15, 50, getWidth() - 30, 10);
    }

    protected abstract void addGuiComponents();
}
