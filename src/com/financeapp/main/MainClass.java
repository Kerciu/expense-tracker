package com.financeapp.main;

import com.financeapp.frontend.LoginFrame;
import com.financeapp.frontend.RegisterFrame;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegisterFrame().setVisible(true);
            }
        });
    }
}