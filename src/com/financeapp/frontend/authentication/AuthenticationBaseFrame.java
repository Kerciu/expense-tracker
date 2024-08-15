package com.financeapp.frontend.authentication;

import com.financeapp.frontend.app.BaseFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;

public abstract class AuthenticationBaseFrame extends BaseFrame {
    protected enum LabelType {CENTER_TEXT, USERNAME, PASSWORD, REENTER_PASSWORD};

    public AuthenticationBaseFrame(String title)
    {
        super(title, 420, 600);
    }

    protected JLabel createLabel(String text, LabelType type) {
        switch (type) {
            case CENTER_TEXT -> {
                return UIComponentFactory.createLabel(text, 0, 20, super.getWidth(), 40, 32, SwingConstants.CENTER);
            }
            case USERNAME -> {
                return UIComponentFactory.createLabel(text, 20, 120, super.getWidth() - 30, 24, 20, SwingConstants.LEFT);
            }
            case PASSWORD, REENTER_PASSWORD -> {
                return UIComponentFactory.createLabel(text, 20, (type == LabelType.PASSWORD ? 220 : 320), super.getWidth() - 30, 24, 20, SwingConstants.LEFT);
            }
            default -> throw new IllegalArgumentException("Invalid LabelType");
        }
    }

    protected JTextField createTextField() {
        return UIComponentFactory.createTextField(20, 160, super.getWidth() - 50, 40, 28, true);
    }

    protected JPasswordField createPasswordField() {
        return UIComponentFactory.createPasswordField(20, 320, super.getWidth() - 50, 40, 28);
    }
}
