package com.financeapp.frontend.authentication;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends BaseFrame {

    public RegisterFrame()
    {
        super("Finance App - Sign Up");
    }
    @Override
    protected void addGuiComponents() {
        add(createLabel("Finance Manager", LabelType.CENTER_TEXT));
        add(createLabel("Username", LabelType.USERNAME));
        add(createLabel("New Password", LabelType.PASSWORD));
        add(createLabel("Re-enter Password", LabelType.REENTER_PASSWORD));
        add(createTextField());
        add(createPasswordField());
        add(createReenterPasswordField());
        add(createRegisterButton());
        add(createLoginLabel());
    }

    private JPasswordField createReenterPasswordField()
    {
        JPasswordField rePasswordField = new JPasswordField();
        addReenterPasswordFieldAttributes(rePasswordField);
        return rePasswordField;
    }

    @Override
    protected void addLabelAttributes(JLabel label, LabelType type)
    {
        switch (type){
            case CENTER_TEXT -> {
                addCenterTextAttributes(label);
            }
            case USERNAME -> {
                addUsernameLabelAttributes(label);
            }
            case PASSWORD -> {
                addPasswordLabelAttributes(label);
            }
            case REENTER_PASSWORD -> {
                addReenterPasswordLabelAttributes(label);
            }
        }
    }

    @Override
    protected void addPasswordLabelAttributes(JLabel passwordLabel)
    {
        passwordLabel.setBounds(20, 220, super.getWidth() - 30, 24);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
    }

    @Override
    protected void addPasswordFieldAttributes(JPasswordField passwordField)
    {
        passwordField.setBounds(20, 260, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    private void addReenterPasswordLabelAttributes(JLabel reenterLabel)
    {
        reenterLabel.setBounds(20, 320, super.getWidth() - 30, 24);
        reenterLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
    }

    private void addReenterPasswordFieldAttributes(JPasswordField rePasswordField)
    {
        rePasswordField.setBounds(20, 360, super.getWidth() - 50, 40);
        rePasswordField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    private JButton createRegisterButton()
    {
        JButton registerButton = new JButton("Register");
        addRegisterButtonAttributes(registerButton);
        return registerButton;
    }

    private void addRegisterButtonAttributes(JButton registerButton)
    {
        registerButton.setBounds(20, 460, super.getWidth() - 50, 40);
        registerButton.setFont(new Font("Dialog", Font.BOLD, 20));
        registerButton.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private JLabel createLoginLabel()
    {
        String text = "Already have an account? Click here to sign in!";
        JLabel loginLabel = new JLabel(text);
        addLoginLabelAttributes(loginLabel);
        return loginLabel;
    }

    private void addLoginLabelAttributes (JLabel loginLabel)
    {
        loginLabel.setBounds(0, 510, super.getWidth() - 10, 30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 16));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
}
