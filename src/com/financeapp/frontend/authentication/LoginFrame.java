package com.financeapp.frontend.authentication;

import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public class LoginFrame extends AuthenticationBaseFrame{
    public LoginFrame()
    {
        super("Finance App - Sign In");
    }

    @Override
    protected void addGuiComponents() {
        add(createLabel("Finance Manager", LabelType.CENTER_TEXT));
        add(createLabel("Username", LabelType.USERNAME));
        add(createLabel("Password", LabelType.PASSWORD));
        add(createTextField());
        add(createPasswordField());
        add(createLoginButton());
        add(createRegisterLabel());
    }

    private JButton createLoginButton() {
        return UIComponentFactory.createButton("Login", 20, 460, super.getWidth() - 50, 40, 20);
    }

    private JLabel createRegisterLabel() {
        return UIComponentFactory.createLabel(
                "No account yet? Click here to sign up!",
                0, 510, super.getWidth() - 10, 30, 20, SwingConstants.CENTER
        );
    }
}
