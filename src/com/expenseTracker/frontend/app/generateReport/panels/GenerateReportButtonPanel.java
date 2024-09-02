package com.expenseTracker.frontend.app.generateReport.panels;

import com.expenseTracker.backend.fileExporters.CSVExporter;
import com.expenseTracker.backend.fileExporters.PDFExporter;
import com.expenseTracker.backend.fileExporters.TXTExporter;
import com.expenseTracker.backend.fileExporters.XLSXExporter;
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
                String documentsDirectory = workingDirectory + File.separator + "documents";
                File documentsDir = new File(documentsDirectory);

                if (!documentsDir.exists()) {
                    documentsDir.mkdirs();
                }

                JTextField csvTextField = source.getCsvReportTypePanel().getFileNameTextField();
                JTextField pdfTextField = source.getPdfReportTypePanel().getFileNameTextField();
                JTextField xlsxTextField = source.getXlsxReportTypePanel().getFileNameTextField();
                JTextField txtTextField = source.getTxtReportTypePanel().getFileNameTextField();

                JCheckBox csvCheckBox = source.getCsvReportTypePanel().getCheckBox();
                JCheckBox pdfCheckBox = source.getPdfReportTypePanel().getCheckBox();
                JCheckBox xlsxCheckBox = source.getXlsxReportTypePanel().getCheckBox();
                JCheckBox txtCheckBox = source.getTxtReportTypePanel().getCheckBox();

                int fileCounter = 0;
                String filePath = documentsDirectory + File.separator;
                if (csvCheckBox.isSelected() && !csvTextField.getText().isEmpty()) {
                    String csvFilePath = filePath + source.getCsvReportTypePanel().getFileNameTextField().getText() + ".csv";
                    new CSVExporter(csvFilePath, source.getUser()).exportFile();
                    ++fileCounter;
                }
                if (pdfCheckBox.isSelected() && !pdfTextField.getText().isEmpty()) {
                    String pdfFilePath = filePath + source.getPdfReportTypePanel().getFileNameTextField().getText() + ".pdf";
                    new PDFExporter(pdfFilePath, source.getUser()).exportFile();
                    ++fileCounter;
                }
                if (xlsxCheckBox.isSelected() && !xlsxTextField.getText().isEmpty()) {
                    String xlsxFilePath = filePath + source.getXlsxReportTypePanel().getFileNameTextField().getText() + ".xlsx";
                    new XLSXExporter(xlsxFilePath, source.getUser()).exportFile();
                    ++fileCounter;
                }
                if (txtCheckBox.isSelected() && !txtTextField.getText().isEmpty()) {
                    String txtFilePath = filePath + source.getTxtReportTypePanel().getFileNameTextField().getText() + ".txt";
                    new TXTExporter(txtFilePath, source.getUser()).exportFile();
                    ++fileCounter;
                }

                clearAllTheFieldsUponAdding(csvTextField, pdfTextField, xlsxTextField, txtTextField);

                if (fileCounter > 0) {
                    JOptionPane.showMessageDialog(source, "Successfully created "+fileCounter+" files in /documents/ directory.");
                } else {
                    JOptionPane.showMessageDialog(source, "No file extensions selected!");
                }
            }
        };
    }

    private void clearAllTheFieldsUponAdding(JTextField... textFields)
    {
        for (JTextField textField : textFields) {
            textField.setText("");
        }
    }
}
