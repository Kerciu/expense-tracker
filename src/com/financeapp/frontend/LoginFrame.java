package com.financeapp.frontend;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends BaseFrame{
    private enum LabelType {CENTER_TEXT, USERNAME, PASSWORD};
    private enum TextFieldType {};

    public LoginFrame()
    {
        super("Finance App - Sign In");
    }

    @Override
    protected void addGuiComponents() {
        Component financeManager = add(createLabel("Finance Manager", LabelType.CENTER_TEXT));
        add(createLabel("Username", LabelType.USERNAME));
        add(createLabel("Password", LabelType.PASSWORD));
    }

    private JLabel createLabel(String text, LabelType type)
    {
        JLabel label = new JLabel(text);
        addLabelAttributes(label, type);
        return label;
    }

    private JTextField createTextField(TextFieldType type)
    {
        JTextField textField = new JTextField();
        addTextFieldAttributes(textField, type);
        return textField;
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
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
            case PASSWORD -> {
                label.setBounds(0, 20, super.getWidth(), 40);
                label.setFont(new Font("Dialog", Font.BOLD, 32));
                label.setHorizontalAlignment(SwingConstants.CENTER);
            }
        }
    }

    private void addTextFieldAttributes(JTextField textField, TextFieldType type)
    {

    }
}
