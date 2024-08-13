package com.financeapp.frontend;

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
}
