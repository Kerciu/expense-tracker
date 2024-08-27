package com.expenseTracker.test;

import com.expenseTracker.frontend.authentication.LoginFrame;
import com.expenseTracker.frontend.authentication.RegisterFrame;
import org.junit.Test;

import javax.swing.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AuthenticationFramesTest {
    @Test
    public void testLoginFrameComponents() {
        LoginFrame loginFrame = new LoginFrame();

        assertEquals("Finance App - Sign In", loginFrame.getTitle());

        boolean hasTextField = false;
        boolean hasPasswordField = false;
        boolean hasLoginButton = false;

        for (Component component : loginFrame.getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                hasTextField = true;
            }
            if (component instanceof JPasswordField) {
                hasPasswordField = true;
            }
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals("Login")) {
                    hasLoginButton = true;
                }
            }
        }

        assertTrue(hasTextField);
        assertTrue(hasPasswordField);
        assertTrue(hasLoginButton);
    }

    @Test
    public void testRegisterFrameComponents() {
        RegisterFrame registerFrame = new RegisterFrame();

        assertEquals("Finance App - Sign Up", registerFrame.getTitle());

        boolean hasTextField = false;
        boolean hasPasswordField = false;
        boolean hasReenterPasswordField = false;
        boolean hasRegisterButton = false;

        for (Component component : registerFrame.getContentPane().getComponents()) {
            if (component instanceof JTextField) {
                hasTextField = true;
            }
            if (component instanceof JPasswordField) {
                if (!hasPasswordField) {
                    hasPasswordField = true;
                } else {
                    hasReenterPasswordField = true;
                }
            }
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.getText().equals("Register")) {
                    hasRegisterButton = true;
                }
            }
        }

        assertTrue(hasTextField);
        assertTrue(hasPasswordField);
        assertTrue(hasReenterPasswordField);
        assertTrue(hasRegisterButton);
    }
}
