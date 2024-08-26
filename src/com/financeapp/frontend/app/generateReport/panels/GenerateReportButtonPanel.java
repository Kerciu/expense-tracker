package com.financeapp.frontend.app.generateReport.panels;

import com.financeapp.frontend.app.generateReport.frame.GenerateReportFrame;
import com.financeapp.frontend.app.mainFrame.MainFrame;
import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReportButtonPanel extends JPanel{
    private GenerateReportFrame source;

    public GenerateReportButtonPanel(GenerateReportFrame source, int width) {
        this.source = source;

        setLayout(null);
        addButtons(width);
    }

    private void addButtons(int width)
    {
        add(createGoBackButton(width));
        add(createGenerateButton(width));
    }

    private JButton createGoBackButton(int width)
    {
        JButton button = UIComponentFactory.createButton(
                "Go Back", 5, 0, (width - 10) / 2, 40, 30
        );
        button.addActionListener(createGoBackButtonActionListener());
        return button;
    }

    private JButton createGenerateButton(int width)
    {
        int offset = (width - 10) / 2;
        JButton button = UIComponentFactory.createButton(
                "Generate", offset+ 5, 0, offset, 40, 30
        );
        button.addActionListener(createGenerateButtonActionListener());
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

    private ActionListener createGenerateButtonActionListener()
    {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
    }

    private void clearAllTheFieldsUponAdding()
    {
        // TODO
    }
}
