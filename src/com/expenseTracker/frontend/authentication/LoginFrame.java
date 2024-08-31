package com.expenseTracker.frontend.authentication;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.frontend.app.mainFrame.MainFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginFrame extends AuthenticationBaseFrame{
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    public LoginFrame()
    {
        super("Expense Tracker - Sign In");
    }

    @Override
    protected void addGuiComponents() {
        add(createLabel("Expense Tracker", LabelType.CENTER_TEXT));
        add(createIconLabel());
        add(createLabel("Username", LabelType.USERNAME));
        add(createLabel("Password", LabelType.PASSWORD));

        usernameTextField = createTextField();
        add(usernameTextField);
        passwordField = createPasswordField();
        add(passwordField);

        add(createLoginButton());
        add(createRegisterLabel());
    }

    private JButton createLoginButton() {
        JButton loginButton = UIComponentFactory.createButton("Login", 20, 460, super.getWidth() - 50, 40, 20);
        loginButton.addActionListener(
                createLoginButtonActionListener()
        );

        return loginButton;
    }

    private ActionListener createLoginButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTextField.getText();
                String password = String.valueOf(passwordField.getPassword());

                User user = MySQLConnector.validateLogin(username, password);
                if (user != null) {
                    disposeLoginFrame(user);
                } else {
                    showFailureWindow();
                }
            }
        };
    }

    private void disposeLoginFrame(User user)
    {
        LoginFrame.this.dispose();
        MainFrame mainFrame = new MainFrame(user);
        mainFrame.setVisible(true);

        JOptionPane.showMessageDialog(mainFrame, "Signed In Succesfully!");
    }

        private void showFailureWindow()
    {
        JOptionPane.showMessageDialog(LoginFrame.this, "Username or Password does not match!");
    }

    private JLabel createRegisterLabel() {
        JLabel registerLabel = UIComponentFactory.createLabel(
                "No account yet? Click here to sign up!",
                0, 510, super.getWidth() - 10, 30, 20, SwingConstants.CENTER
        );
        registerLabel.addMouseListener(
                createRegisterLabelMouseListener()
        );

        return registerLabel;
    }

    private MouseAdapter createRegisterLabelMouseListener()
    {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginFrame.this.dispose();
                new RegisterFrame().setVisible(true);
            }
        };
    }
}
