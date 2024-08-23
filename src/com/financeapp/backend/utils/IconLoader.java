package com.financeapp.backend.utils;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;

public class IconLoader {
    public static ImageIcon loadIcon(String filePath)
    {
        try {
            InputStream inputStream = IconLoader.class.getResourceAsStream(filePath);
            if (inputStream == null) {
                System.err.println("Icon not found: " + filePath);
                return null;
            }
            
            return new ImageIcon(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
