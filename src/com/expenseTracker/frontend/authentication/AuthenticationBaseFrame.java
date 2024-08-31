package com.expenseTracker.frontend.authentication;

import com.expenseTracker.backend.utils.IconLoader;
import com.expenseTracker.frontend.app.utils.BaseFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public abstract class AuthenticationBaseFrame extends BaseFrame {
    protected enum LabelType {CENTER_TEXT, USERNAME, PASSWORD, REENTER_PASSWORD};

    public AuthenticationBaseFrame(String title)
    {
        super(title, 420, 600);
    }

    protected JLabel createLabel(String text, LabelType type) {
        switch (type) {
            case CENTER_TEXT -> {
                JLabel centerLabel = UIComponentFactory.createLabel(text, 30, 30, super.getWidth(), 40, 32, SwingConstants.CENTER);
                centerLabel.setFont(new Font("Dialog", Font.BOLD, 36));
                return centerLabel;
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

    protected JLabel createIconLabel()
    {
        String iconPath = "/resources/assets/images/expense-tracker-icon.png";
        ImageIcon imageIcon = IconLoader.loadIcon(iconPath);
        return UIComponentFactory.createImageLabel(imageIcon, 20, 20, 64, 64);
    }

    protected JTextField createTextField() {
        return UIComponentFactory.createTextField(20, 160, super.getWidth() - 50, 40, 28, true);
    }

    protected JPasswordField createPasswordField() {
        return UIComponentFactory.createPasswordField(20, 260, super.getWidth() - 50, 40, 28);
    }
}
