package com.financeapp.frontend.authentication;

import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterFrame extends AuthenticationBaseFrame {

    public RegisterFrame()
    {
        super("Finance App - Sign Up");
    }
    @Override
    protected void addGuiComponents() {
        add(UIComponentFactory.createLabel("Finance Manager", 0, 20, getWidth(), 40, 32, SwingConstants.CENTER));
        add(UIComponentFactory.createLabel("Username", 20, 120, getWidth() - 30, 24, 20, SwingConstants.LEFT));
        add(UIComponentFactory.createLabel("New Password", 20, 220, getWidth() - 30, 24, 20, SwingConstants.LEFT));
        add(UIComponentFactory.createLabel("Re-enter Password", 20, 320, getWidth() - 30, 24, 20, SwingConstants.LEFT));

        add(UIComponentFactory.createTextField(20, 160, getWidth() - 50, 40, 28, true));
        add(UIComponentFactory.createPasswordField(20, 260, getWidth() - 50, 40, 28));
        add(UIComponentFactory.createPasswordField(20, 360, getWidth() - 50, 40, 28));

        add(UIComponentFactory.createButton("Register", 20, 460, getWidth() - 50, 40, 20));
        add(createLoginLabel());
    }

    private JLabel createLoginLabel()
    {
        JLabel label = UIComponentFactory.createLabel("Already have an account? Click here to sign in!", 0, 510, getWidth() - 10, 30, 16, SwingConstants.CENTER);
        label.addMouseListener(
                createMouseListener()
        );

        return label;
    }

    private MouseAdapter createMouseListener()
    {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFrame.this.dispose();
                new LoginFrame().setVisible(true);
            }
        };
    }

}
