package com.financeapp.main;

import com.financeapp.frontend.app.MainFrame;
import com.financeapp.frontend.authentication.LoginFrame;
import com.financeapp.frontend.authentication.RegisterFrame;
import com.mysql.cj.log.Log;

import javax.swing.*;

public class MainClass {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }
}