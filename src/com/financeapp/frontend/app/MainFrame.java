package com.financeapp.frontend.app;

import com.financeapp.backend.data.User;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends BaseFrame {
    public MainFrame(User user)
    {
        super("Finance Manager", user, 420, 600);
    }

    @Override
    protected void addGuiComponents() {
        addWelcomeLabel();
        addBalanceLabel();
        addBalanceTextField();
        addDepositButton();
        addTransactionsHistoryButton();
        addGenerateReportButton();
        addSettingsButton();
        addLogoutButton();
    }

    private void addWelcomeLabel() {
        String welcomeText = "<html><body style='text-align:center'><b>Welcome " + user.getUsername() + "!</b><br>What would you like to do?</body></html>";
        JLabel welcomeLabel = UIComponentFactory.createLabel(welcomeText, 0, 20, getWidth() - 10, 40, 18, SwingConstants.CENTER);
        add(welcomeLabel);
    }

    private void addBalanceLabel() {
        JLabel balanceLabel = UIComponentFactory.createLabel("Current Balance", 0, 80, getWidth() - 10, 30, 22, SwingConstants.CENTER);
        add(balanceLabel);
    }

    private void addBalanceTextField() {
        JTextField userBalanceTextField = UIComponentFactory.createTextField(
                user.getBalance() + " " + getCurrentCurrency(),
                15, 120, getWidth() - 50, 40, 28, false
        );
        userBalanceTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(userBalanceTextField);
    }

    private void addDepositButton() {
        JButton depositButton = UIComponentFactory.createButton("Add Expense", 5, 180, getWidth() - 10, 30, 22);
        add(depositButton);
    }

    private void addTransactionsHistoryButton() {
        JButton withdrawButton = UIComponentFactory.createButton("Transactions History", 5, 250, getWidth() - 10, 30, 22);
        add(withdrawButton);
    }

    private void addGenerateReportButton() {
        JButton transactionsHistoryButton = UIComponentFactory.createButton("Generate Report", 5, 320, getWidth() - 10, 30, 22);
        add(transactionsHistoryButton);
    }

    private void addSettingsButton() {
        JButton generateReportButton = UIComponentFactory.createButton("Settings", 5, 390, getWidth() - 10, 30, 22);
        add(generateReportButton);
    }

    private void addLogoutButton() {
        JButton logoutButton = UIComponentFactory.createButton("Logout", 5, 500, getWidth() - 10, 30, 22);
        add(logoutButton);
    }

    private String getCurrentCurrency()
    {
        // for now, TODO fetch currency converter API
        return "USD";
    }
}
