package com.expenseTracker.frontend.authentication;

import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class RegisterFrame extends AuthenticationBaseFrame {
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JPasswordField reenterPasswordField;

    public RegisterFrame()
    {
        super("Expense Tracker - Sign Up");
    }
    @Override
    protected void addGuiComponents() {
        add(createLabel("Expense Tracker", LabelType.CENTER_TEXT));
        add(createIconLabel());
        add(UIComponentFactory.createLabel("Username", 20, 120, getWidth() - 30, 24, 20, SwingConstants.LEFT));
        add(UIComponentFactory.createLabel("New Password", 20, 220, getWidth() - 30, 24, 20, SwingConstants.LEFT));
        add(UIComponentFactory.createLabel("Re-enter Password", 20, 320, getWidth() - 30, 24, 20, SwingConstants.LEFT));

        usernameTextField = UIComponentFactory.createTextField(20, 160, getWidth() - 50, 40, 28, true);
        passwordField = UIComponentFactory.createPasswordField(20, 260, getWidth() - 50, 40, 28);
        reenterPasswordField = UIComponentFactory.createPasswordField(20, 360, getWidth() - 50, 40, 28);

        add(usernameTextField);
        add(passwordField);
        add(reenterPasswordField);

        add(createRegisterButton());
        add(createLoginLabel());
    }

    private JLabel createLoginLabel()
    {
        JLabel label = UIComponentFactory.createLabel("Already have an account? Click here to sign in!", 0, 510, getWidth() - 10, 30, 16, SwingConstants.CENTER);
        label.addMouseListener(
            createLoginLabelMouseListener()
        );

        return label;
    }

    private JButton createRegisterButton()
    {
        JButton button = UIComponentFactory.createButton("Register", 20, 460, getWidth() - 50, 40, 20);
        button.addActionListener(
            createRegisterButtonActionListener()
        );

        return button;
    }

    private ActionListener createRegisterButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String reenteredPassword = String.valueOf(reenterPasswordField.getPassword());

                if (!validateUserInput(username, password, reenteredPassword)) {
                    JOptionPane.showMessageDialog(
                            RegisterFrame.this,
                            "Invalid username and/or passwords!"
                    );
                    return;
                }
                try {
                    if (MySQLConnector.registerNewUser(username, password))
                    {
                        RegisterFrame.this.dispose();
                        LoginFrame loginFrame = new LoginFrame();
                        loginFrame.setVisible(true);
                        JOptionPane.showMessageDialog(loginFrame, "Signed Up Successfully!");
                    }
                    else {
                        JOptionPane.showMessageDialog(RegisterFrame.this, "Username Already Taken!");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(
                            RegisterFrame.this,
                            "An error occurred while trying to register. Please try again later.",
                            "Database Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }
            }
        };
    }

    private MouseAdapter createLoginLabelMouseListener()
    {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterFrame.this.dispose();
                new LoginFrame().setVisible(true);
            }
        };
    }

    private boolean validateUserInput(String username, String password, String reenteredPassword)
    {
        boolean lengthIsCorrect = !username.isEmpty() && !password.isEmpty() && !reenteredPassword.isEmpty();
        boolean usernameLenIsCorrect = username.length() > 5 && password.length() > 5 && reenteredPassword.length() > 5;
        boolean passwordsMatches = password.equals(reenteredPassword);

        return lengthIsCorrect && usernameLenIsCorrect && passwordsMatches;
    }
}
