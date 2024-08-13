package com.financeapp.frontend;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends BaseFrame{
    private enum LabelType {CENTER_TEXT, USERNAME, PASSWORD};

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

    private JLabel createLabel(String text, LabelType type)
    {
        JLabel label = new JLabel(text);
        addLabelAttributes(label, type);
        return label;
    }

    private JTextField createTextField()
    {
        JTextField textField = new JTextField();
        addTextFieldAttributes(textField);
        return textField;
    }

    private JPasswordField createPasswordField()
    {
        JPasswordField passwordField = new JPasswordField();
        addPasswordFieldAttributes(passwordField);
        return passwordField;
    }

    private JButton createLoginButton()
    {
        JButton loginButton = new JButton("Login");
        addLoginButtonAttributes(loginButton);
        return loginButton;
    }

    private JLabel createRegisterLabel()
    {
        String text = "No account yet? Click here to sign up!";
        JLabel registerLabel = new JLabel(text);
        addRegisterLabelAttributes(registerLabel);
        return registerLabel;
    }

    private void addLabelAttributes(JLabel label, LabelType type)
    {
        switch (type){
            case CENTER_TEXT -> {
                label.setBounds(0, 20, super.getWidth(), 40);
                label.setFont(new Font("Dialog", Font.BOLD, 32));
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
            case USERNAME -> {
                label.setBounds(20, 120, super.getWidth() - 30, 24);
                label.setFont(new Font("Dialog", Font.PLAIN, 20));
            }
            case PASSWORD -> {
                label.setBounds(20, 280, super.getWidth() - 30, 24);
                label.setFont(new Font("Dialog", Font.PLAIN, 20));
            }
        }
    }

    private void addTextFieldAttributes(JTextField textField)
    {
        textField.setBounds(20, 160, super.getWidth() - 50, 40);
        textField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    private void addPasswordFieldAttributes(JPasswordField passwordField)
    {
        passwordField.setBounds(20, 320, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    private void addLoginButtonAttributes(JButton loginButton)
    {
        loginButton.setBounds(20, 460, super.getWidth() - 50, 40);
        loginButton.setFont(new Font("Dialog", Font.BOLD, 20));
        loginButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void addRegisterLabelAttributes(JLabel registerLabel)
    {
        registerLabel.setBounds(0, 510, super.getWidth() - 10, 30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
