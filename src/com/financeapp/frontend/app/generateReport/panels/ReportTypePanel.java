package com.financeapp.frontend.app.generateReport.panels;

import com.financeapp.frontend.components.UIComponentFactory;

import javax.swing.*;
import java.awt.*;

public class ReportTypePanel extends JPanel {
    private String extensionName;
    private JCheckBox checkBox;

    public ReportTypePanel(String extensionName, int width)
    {
        this.extensionName = extensionName;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Checkbox
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        add(createFileToGenerateCheckBox(), gbc);

        // Label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createFileExtensionLabel(width), gbc);

        // TextArea
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(createFileNameTextField(width), gbc);
    }

    private void addComponents(int width)
    {
        add(createFileExtensionLabel(width));
        add(createFileNameTextField(width));
        add(createFileToGenerateCheckBox());
    }

    private JTextField createFileNameTextField(int width)
    {
        return UIComponentFactory.createTextField(
                20, 20, (width - 30), 30, 20, true
        );
    }

    private JCheckBox createFileToGenerateCheckBox()
    {
        checkBox = UIComponentFactory.createCheckBox(
                "", 5, 35, 15, 15, 0, false
        );
        return checkBox;
    }

    private JLabel createFileExtensionLabel(int width)
    {
        return UIComponentFactory.createLabel(
                "Generate " + extensionName +" file",
                20, 0, (width - 30), 30, 24,
                SwingConstants.LEFT
        );
    }

    public String getExtensionName() {
        return extensionName;
    }

    public JCheckBox getCheckBox() {
        return checkBox;
    }
}
