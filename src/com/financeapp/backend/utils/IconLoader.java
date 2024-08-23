package com.financeapp.backend.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class IconLoader {
    public static ImageIcon loadIcon(String filePath)
    {
        try {
            BufferedImage bufferedImage = ImageIO.read(new File(filePath));
            return new ImageIcon(bufferedImage);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
