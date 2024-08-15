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

    public static JTextField createTextField(String text, int x, int y, int width, int height, int fontSize, boolean editable) {
        JTextField textField = new JTextField(text);
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setEditable(editable);
        return textField;
    }

    public static JButton createButton(String text, int x, int y, int width, int height, int fontSize) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Dialog", Font.PLAIN, fontSize));
        return button;
    }
}
