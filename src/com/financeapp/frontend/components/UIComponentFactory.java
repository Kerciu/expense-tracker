package com.financeapp.frontend.components;

import javax.swing.*;
import java.awt.*;

public class UIComponentFactory {
    public static JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, int alignment) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        label.setHorizontalAlignment(alignment);
        return label;
    }

    public static JTextField createTextField(int x, int y, int width, int height, int fontSize, boolean editable) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setEditable(editable);
        return textField;
    }

    public static JTextField createTextField(String text, int x, int y, int width, int height, int fontSize, boolean editable) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        textField.setHorizontalAlignment(SwingConstants.LEFT);
        textField.setEditable(editable);
        return textField;
    }

    public static JButton createButton(String text, int x, int y, int width, int height, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return button;
    }

    public static JPasswordField createPasswordField(int x, int y, int width, int height, int fontSize) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, height);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return passwordField;
    }

    public static JComboBox<String> createStringComboBox(String[] availableOptions, int x, int y, int width, int height, int fontSize)
    {
        JComboBox<String> comboBox = new JComboBox<>(availableOptions);
        comboBox.setBounds(x, y, width, height);
        comboBox.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return comboBox;
    }
}
