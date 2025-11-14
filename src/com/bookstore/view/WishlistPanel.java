package com.bookstore.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL; // For URL in resource loading
public class WishlistPanel extends JPanel {
    public WishlistPanel(MainFrame frame) {
        // ---------- Réglages de base du panneau ----------
        setLayout(null); // Positionnement manuel
        setBackground(new Color(245, 245, 220)); // Beige background to match theme
        // ---------- Titre principal ----------
        JLabel title = new JLabel("♡ Wishlist");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(new Color(101, 67, 33)); // Saddle brown text
        title.setBounds(50, 30, 300, 30);
        add(title);
        // Book data with filenames
        String[][] books = {
                {"Harry Potter pack", "J.K. Rowling", "12000 DZD", "potter.jpg"}, // Add this image to src/images/
                {"To Kill a Mockingbird", "Harper Lee", "2000 DZD", "mockingbird.jpg"},
                {"We have always lived in castle", "Shirley Jackson", "2900 DZD", "we castle.jpg"} // Add this image to src/images/
        };
        // Positions for three books horizontally
        int startX = 100;
        int bookWidth = 200;
        int bookHeight = 280;
        int spacing = 160; // Adjust for frame size (360-100=260, 620-360=260; average 160 spacing)
        for (int i = 0; i < books.length; i++) {
            String[] b = books[i];
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(null);
            bookPanel.setBackground(Color.WHITE);
            bookPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
            bookPanel.setBounds(startX + (i * spacing), 120, bookWidth, bookHeight);
            add(bookPanel);
            // Make bookPanel clickable to navigate to BOOKS
            bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    frame.navigateTo("BOOKS");
                }
            });
            bookPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            // Image label
            JLabel imgLabel = new JLabel();
            imgLabel.setBounds(25, 20, 150, 150);
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            // Load image from resources
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource("/imagess/" + b[3]);
                if (imageUrl != null) {
                    icon = new ImageIcon(imageUrl);
                }
            } catch (Exception e) {
                System.out.println("Image not found: " + b[3]);
            }
            if (icon != null && icon.getIconWidth() > 0) {
                // Scale image to fit (150x150 label)
                Image scaledImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaledImage));
                imgLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
            } else {
                // Fallback text
                imgLabel.setText("(Image)");
                imgLabel.setBackground(new Color(245, 245, 220)); // Beige fallback
                imgLabel.setOpaque(true);
                imgLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
            }
            bookPanel.add(imgLabel);
            // Title
            JLabel nameLabel = new JLabel(b[0], SwingConstants.CENTER);
            nameLabel.setFont(new Font("Serif", Font.BOLD, 13));
            nameLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
            nameLabel.setBounds(10, 180, 180, 20);
            bookPanel.add(nameLabel);
            // Author
            JLabel authorLabel = new JLabel("Author: " + b[1], SwingConstants.CENTER);
            authorLabel.setFont(new Font("Serif", Font.ITALIC, 12));
            authorLabel.setForeground(new Color(139, 69, 19)); // Darker brown
            authorLabel.setBounds(10, 200, 180, 20);
            bookPanel.add(authorLabel);
            // Price
            JLabel priceLabel = new JLabel(b[2], SwingConstants.CENTER);
            priceLabel.setFont(new Font("Serif", Font.BOLD, 13));
            priceLabel.setForeground(new Color(34, 139, 34)); // Forest green
            priceLabel.setBounds(10, 230, 180, 20);
            bookPanel.add(priceLabel);
        }
        // ---------- Back button ----------
        JButton backBtn = new JButton("← Back to Home");
        backBtn.setBounds(650, 450, 150, 30);
        backBtn.setFont(new Font("Serif", Font.PLAIN, 14));
        backBtn.setBackground(new Color(220, 220, 200)); // Light beige button
        backBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        backBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        backBtn.addActionListener(e -> frame.navigateTo("HOME"));
        add(backBtn);
    }
}