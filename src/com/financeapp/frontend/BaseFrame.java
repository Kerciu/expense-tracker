package com.financeapp.frontend;

import javax.swing.*;
import java.awt.*;

public abstract class BaseFrame extends JFrame {
    protected enum LabelType {CENTER_TEXT, USERNAME, PASSWORD, REENTER_PASSWORD};

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

    protected JLabel createLabel(String text, LabelType type)
    {
        JLabel label = new JLabel(text);
        addLabelAttributes(label, type);
        return label;
    }

    protected JTextField createTextField()
    {
        JTextField textField = new JTextField();
        addTextFieldAttributes(textField);
        return textField;
    }

    protected JPasswordField createPasswordField()
    {
        JPasswordField passwordField = new JPasswordField();
        addPasswordFieldAttributes(passwordField);
        return passwordField;
    }

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
        }
    }

    protected void addCenterTextAttributes(JLabel label)
    {
        label.setBounds(0, 20, super.getWidth(), 40);
        label.setFont(new Font("Dialog", Font.BOLD, 32));
        label.setHorizontalAlignment(SwingConstants.CENTER);
    }

    protected void addUsernameLabelAttributes(JLabel label)
    {
        label.setBounds(20, 120, super.getWidth() - 30, 24);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
    }

    protected void addPasswordLabelAttributes(JLabel label)
    {
        label.setBounds(20, 280, super.getWidth() - 30, 24);
        label.setFont(new Font("Dialog", Font.PLAIN, 20));
    }

    protected void addTextFieldAttributes(JTextField textField)
    {
        textField.setBounds(20, 160, super.getWidth() - 50, 40);
        textField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

    protected void addPasswordFieldAttributes(JPasswordField passwordField)
    {
        passwordField.setBounds(20, 320, super.getWidth() - 50, 40);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 28));
    }

}
