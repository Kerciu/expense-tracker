package com.expenseTracker.frontend.app.generateReport.panels;

import com.expenseTracker.backend.writers.CSVExporter;
import com.expenseTracker.backend.writers.TXTExporter;
import com.expenseTracker.frontend.app.generateReport.frame.GenerateReportFrame;
import com.expenseTracker.frontend.app.mainFrame.MainFrame;
import com.expenseTracker.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
                String workingDirectory = System.getProperty("user.dir");
                JCheckBox csvCheckBox = source.getCsvReportTypePanel().getCheckBox();
                JCheckBox pdfCheckBox = source.getPdfReportTypePanel().getCheckBox();
                JCheckBox xlsxCheckBox = source.getXlsxReportTypePanel().getCheckBox();
                JCheckBox txtCheckBox = source.getTxtReportTypePanel().getCheckBox();

                String filePath = workingDirectory + File.separator;
                if (csvCheckBox.isSelected()) {
                    filePath += source.getCsvReportTypePanel().getFileNameTextField().getText() + ".csv";
                    System.out.println("File path: " + filePath);
                    new CSVExporter(filePath, source.getUser()).exportFile();
                    System.out.println("Successfully exported csv file");
                }
                if (pdfCheckBox.isSelected()) {
                    return;
                }
                if (xlsxCheckBox.isSelected()) {
                    return;
                }
                if (txtCheckBox.isSelected()) {
                    filePath += source.getTxtReportTypePanel().getFileNameTextField().getText() + ".txt";
                    System.out.println("File path: " + filePath);
                    new TXTExporter(filePath, source.getUser()).exportFile();
                    System.out.println("Successfully exported txt file");
                }
            }
        };
    }

    private void clearAllTheFieldsUponAdding()
    {
        // TODO
    }
}
