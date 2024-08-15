package com.financeapp.frontend.authentication;

import com.financeapp.backend.data.User;
import com.financeapp.backend.db.MySQLConnector;
import com.financeapp.frontend.app.MainFrame;
import com.financeapp.frontend.components.UIComponentFactory;
import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends AuthenticationBaseFrame{
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    public LoginFrame()
    {
        super("Finance App - Sign In");
    }

    @Override
    protected void addGuiComponents() {
        add(createLabel("Finance Manager", LabelType.CENTER_TEXT));
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
        return UIComponentFactory.createLabel(
                "No account yet? Click here to sign up!",
                0, 510, super.getWidth() - 10, 30, 20, SwingConstants.CENTER
        );
    }
}
