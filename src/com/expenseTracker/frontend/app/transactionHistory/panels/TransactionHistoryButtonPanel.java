package com.expenseTracker.frontend.app.transactionHistory.panels;

import com.expenseTracker.frontend.app.mainFrame.MainFrame;
import com.expenseTracker.frontend.app.transactionHistory.frame.TransactionHistoryFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransactionHistoryButtonPanel extends JPanel{
    private TransactionHistoryFrame source;

    public TransactionHistoryButtonPanel(TransactionHistoryFrame source, int width) {
        this.source = source;

        setLayout(null);
        addButtons(width);
    }

    private void addButtons(int width)
    {
        add(createGoBackButton(width));
    }

    private JButton createGoBackButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Go Back", 10, 0, width - 20, 40, 25
        );
        button.addActionListener(createGoBackButtonActionListener());
        return button;
    }

    private ActionListener createGoBackButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source.dispose();
                new MainFrame(source.getUser()).setVisible(true);
            }
        };
    }
}
