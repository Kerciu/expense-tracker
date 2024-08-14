package com.financeapp.main;

import com.financeapp.frontend.app.MainFrame;
import com.financeapp.frontend.authentication.RegisterFrame;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}