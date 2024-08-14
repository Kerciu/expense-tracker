package com.financeapp.frontend.authentication;

import javax.swing.*;
import java.awt.*;

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

    private JLabel createRegisterLabel()
    {
        String text = "No account yet? Click here to sign up!";
        JLabel registerLabel = new JLabel(text);
        addRegisterLabelAttributes(registerLabel);
        return registerLabel;
    }

    private void addRegisterLabelAttributes (JLabel registerLabel)
    {
        registerLabel.setBounds(0, 510, super.getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    protected JButton createLoginButton()
    {
        JButton loginButton = new JButton("Login");
        addLoginButtonAttributes(loginButton);
        return loginButton;
    }

    protected void addLoginButtonAttributes(JButton loginButton)
    {
        loginButton.setBounds(20, 460, super.getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
