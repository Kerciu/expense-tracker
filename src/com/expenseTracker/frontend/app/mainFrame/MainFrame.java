package com.expenseTracker.frontend.app.mainFrame;

import com.expenseTracker.backend.data.User;
import com.expenseTracker.backend.db.MySQLConnector;
import com.expenseTracker.frontend.app.utils.BaseFrame;
import com.expenseTracker.frontend.app.generateReport.frame.GenerateReportFrame;
import com.expenseTracker.frontend.app.SettingsFrame;
import com.expenseTracker.frontend.app.addFrame.frame.AddExpenseFrame;
import com.expenseTracker.frontend.app.transactionHistory.frame.TransactionHistoryFrame;
import com.expenseTracker.frontend.authentication.LoginFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrame extends BaseFrame implements ActionListener {
    public MainFrame(User user)
    {
        super("Expense tracker", user, 420, 600);
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
        String welcomeText = "<html><body style='text-align:center'><b>Welcome " + user.getUsername() + "!</b><br>What would you like to do today?</body></html>";
        JLabel welcomeLabel = UIComponentFactory.createLabel(welcomeText, 0, 20, getWidth() - 10, 50, 18, SwingConstants.CENTER);
        add(welcomeLabel);
    }

    private void addBalanceLabel() {
        JLabel balanceLabel = UIComponentFactory.createLabel("Current Balance", 0, 80, getWidth() - 10, 30, 22, SwingConstants.CENTER);
        add(balanceLabel);
    }

    private void addBalanceTextField() {
        try {
            MySQLConnector.updateUserBalanceFromTransactions(user);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        JTextField userBalanceTextField = UIComponentFactory.createTextField(
                user.getBalance().toString(),
                15, 120, getWidth() - 50, 40, 28, false
        );
        userBalanceTextField.setHorizontalAlignment(SwingConstants.CENTER);
        add(userBalanceTextField);
    }

    private void addDepositButton() {
        JButton depositButton = UIComponentFactory.createButton("Add Transaction", 5, 180, getWidth() - 10, 40, 22);
        depositButton.addActionListener(this);
        add(depositButton);
    }

    private void addTransactionsHistoryButton() {
        JButton transactionsHistoryButton = UIComponentFactory.createButton("Transactions History", 5, 250, getWidth() - 10, 40, 22);
        transactionsHistoryButton.addActionListener(this);
        add(transactionsHistoryButton);
    }

    private void addGenerateReportButton() {
        JButton generateReportButton = UIComponentFactory.createButton("Generate Report", 5, 320, getWidth() - 10, 40, 22);
        generateReportButton.addActionListener(this);
        add(generateReportButton);
    }

    private void addSettingsButton() {
        JButton generateReportButton = UIComponentFactory.createButton("Settings", 5, 390, getWidth() - 10, 40, 22);
        generateReportButton.addActionListener(this);
        add(generateReportButton);
    }

    private void addLogoutButton() {
        JButton logoutButton = UIComponentFactory.createButton("Logout", 5, 500, getWidth() - 10, 40, 22);
        logoutButton.addActionListener(this);
        add(logoutButton);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String buttonClicked = e.getActionCommand();

        if (buttonClicked.equalsIgnoreCase("Logout"))
        {
            MainFrame.this.dispose();
            new LoginFrame().setVisible(true);
        }
        else if (buttonClicked.equalsIgnoreCase("Add Transaction"))
        {
            MainFrame.this.dispose();
            new AddExpenseFrame("Add Transaction", user, 420, 600).setVisible(true);
        }
        else if (buttonClicked.equalsIgnoreCase("Transactions History"))
        {
            MainFrame.this.dispose();
            new TransactionHistoryFrame("Transactions History", user, 420, 600).setVisible(true);
        }
        else if (buttonClicked.equalsIgnoreCase("Generate Report"))
        {
            MainFrame.this.dispose();
            new GenerateReportFrame("Generate Report", user, 420, 600).setVisible(true);
        }
        else if (buttonClicked.equalsIgnoreCase("Settings"))
        {
            MainFrame.this.dispose();
            new SettingsFrame("Settings", user, 420, 600).setVisible(true);
        }
    }
}
