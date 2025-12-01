package com.bookstore.view;

import javax.swing.*;
import java.awt.*;

public class Theme {
    // Colors
    public static final Color BEIGE = new Color(245, 245, 220);
    public static final Color SADDLE_BROWN = new Color(139, 69, 19);
    public static final Color DARK_BROWN = new Color(101, 67, 33);
    public static final Color LIGHT_BEIGE = new Color(255, 248, 220);
    public static final Color GREEN = new Color(0, 128, 0);
    public static final Color LIGHT_BORDER = new Color(200, 200, 200);

    // Button helpers
    public static JButton createThemedButton(String text, int fontSize, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setBackground(LIGHT_BEIGE);
        btn.setForeground(SADDLE_BROWN);
        btn.setFont(new Font("Serif", Font.PLAIN, fontSize));
        btn.addActionListener(action);
        return btn;
    }

    public static JButton createIconButton(String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setForeground(SADDLE_BROWN);
        btn.addActionListener(action);
        return btn;
    }

    // Image label helper
    public static JLabel createBookImageLabel(String imagePath, int width, int height) {
        JLabel label = new JLabel();
        java.net.URL imgUrl = Theme.class.getResource(imagePath);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(scaled));
        } else {
            label.setText("No Image");
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setPreferredSize(new Dimension(width, height));
        }
        return label;
    }

    // === New methods to fix your errors ===

    // Style delete button (red cross)
    public static void styleDeleteButton(JButton btn) {
        btn.setForeground(Color.RED);
        btn.setFont(new Font("Serif", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    // Style quantity buttons (+ / -)
    public static void styleQuantityButton(JButton btn) {
        btn.setBackground(LIGHT_BEIGE);
        btn.setForeground(SADDLE_BROWN);
        btn.setFont(new Font("Serif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(LIGHT_BORDER));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(30, 30));
    }
}
