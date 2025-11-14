package com.bookstore.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL; // For resource loading

public class CartPanel extends JPanel {
    // Constructor expects a MainFrame
    public CartPanel(MainFrame frame) {
        setLayout(null);
        setBackground(new Color(245, 245, 220)); // Beige background
        // ---------- Titre principal ----------
        JLabel title = new JLabel("YOUR SHOPPING CART");
        title.setFont(new Font("Serif", Font.BOLD, 22));
        title.setForeground(new Color(101, 67, 33)); // Saddle brown text
        title.setBounds(50, 30, 400, 30);
        add(title);
        // ---------- En-têtes ----------
        JLabel bookHeader = new JLabel("Book");
        bookHeader.setFont(new Font("Serif", Font.BOLD, 14));
        bookHeader.setForeground(new Color(101, 67, 33));
        bookHeader.setBounds(100, 80, 200, 20);
        add(bookHeader);
        JLabel quantityHeader = new JLabel("Quantity");
        quantityHeader.setFont(new Font("Serif", Font.BOLD, 14));
        quantityHeader.setForeground(new Color(101, 67, 33));
        quantityHeader.setBounds(400, 80, 100, 20);
        add(quantityHeader);
        JLabel priceHeader = new JLabel("Price");
        priceHeader.setFont(new Font("Serif", Font.BOLD, 14));
        priceHeader.setForeground(new Color(101, 67, 33));
        priceHeader.setBounds(550, 80, 100, 20);
        add(priceHeader);
        // ---------- Livre 1 ----------
        JPanel book1 = createBookPanel("The Yellow Wallpaper", "Charlotte Perkins Gilman", "1000 DZD", "yellow.jpg", frame);
        book1.setBounds(80, 110, 700, 100);
        add(book1);
        // ---------- Livre 2 ----------
        JPanel book2 = createBookPanel("To Kill a Mockingbird", "Harper Lee", "1500 DZD", "mockingbird.jpg", frame);
        book2.setBounds(80, 230, 700, 100);
        add(book2);
        // ---------- Total ----------
        JLabel totalCart = new JLabel("Total cart : 2500 DZD");
        totalCart.setFont(new Font("Serif", Font.PLAIN, 14));
        totalCart.setForeground(new Color(139, 69, 19)); // Darker brown
        totalCart.setBounds(500, 370, 200, 25);
        add(totalCart);
        JLabel delivery = new JLabel("Delivery costs : 500 DZD");
        delivery.setFont(new Font("Serif", Font.PLAIN, 14));
        delivery.setForeground(new Color(139, 69, 19));
        delivery.setBounds(500, 400, 200, 25);
        add(delivery);
        JLabel total = new JLabel("Total : 3000 DZD");
        total.setFont(new Font("Serif", Font.BOLD, 15));
        total.setForeground(new Color(34, 139, 34)); // Green for total
        total.setBounds(500, 430, 200, 25);
        add(total);
        // ---------- Boutons ----------
        JButton continueBtn = new JButton("Continue Shopping");
        continueBtn.setBounds(400, 480, 180, 30);
        continueBtn.setFont(new Font("Serif", Font.PLAIN, 14));
        continueBtn.setBackground(new Color(220, 220, 200)); // Light beige
        continueBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        continueBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        continueBtn.addActionListener(e -> frame.navigateTo("HOME"));
        add(continueBtn);
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(600, 480, 120, 30);
        confirmBtn.setFont(new Font("Serif", Font.BOLD, 14));
        confirmBtn.setBackground(new Color(220, 220, 200)); // Light beige
        confirmBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        confirmBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        confirmBtn.addActionListener(e -> frame.navigateTo("ORDERFORM"));
        add(confirmBtn);
    }

    private JPanel createBookPanel(String title, String author, String price, String imageFile, MainFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
        // Make the whole panel clickable to view book details
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });
        panel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Image placeholder/label
        JLabel img = new JLabel();
        img.setBounds(10, 10, 80, 80);
        img.setHorizontalAlignment(SwingConstants.CENTER);
        // Load image from resources
        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/imagess/" + imageFile);
            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
            }
        } catch (Exception e) {
            System.out.println("Image not found: " + imageFile);
        }
        if (icon != null && icon.getIconWidth() > 0) {
            // Scale image to fit (80x80 label)
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            img.setIcon(new ImageIcon(scaledImage));
            img.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        } else {
            // Fallback
            img.setText("(Image)");
            img.setBackground(new Color(245, 245, 220)); // Beige fallback
            img.setOpaque(true);
            img.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        }
        panel.add(img);
        // Infos du livre
        JLabel name = new JLabel(title);
        name.setFont(new Font("Serif", Font.BOLD, 13));
        name.setForeground(new Color(101, 67, 33)); // Saddle brown
        name.setBounds(110, 10, 200, 20);
        panel.add(name);
        JLabel authorLabel = new JLabel("Author: " + author);
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 12));
        authorLabel.setForeground(new Color(139, 69, 19)); // Darker brown
        authorLabel.setBounds(110, 35, 250, 20);
        panel.add(authorLabel);
        // Quantité
        JButton minus = new JButton("-");
        minus.setFont(new Font("Serif", Font.BOLD, 12));
        minus.setBackground(new Color(220, 220, 200)); // Beige
        minus.setForeground(new Color(101, 67, 33));
        minus.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        minus.setBounds(400, 35, 45, 25);
        panel.add(minus);
        JLabel quantity = new JLabel("1", SwingConstants.CENTER);
        quantity.setFont(new Font("Serif", Font.BOLD, 12));
        quantity.setForeground(new Color(101, 67, 33));
        quantity.setBounds(450, 35, 30, 25);
        panel.add(quantity);
        JButton plus = new JButton("+");
        plus.setFont(new Font("Serif", Font.BOLD, 12));
        plus.setBackground(new Color(220, 220, 200)); // Beige
        plus.setForeground(new Color(101, 67, 33));
        plus.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        plus.setBounds(485, 35, 45, 25);
        panel.add(plus);
        // Prix à côté de la quantité
        JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Serif", Font.BOLD, 13));
        priceLabel.setForeground(new Color(34, 139, 34)); // Green for price
        priceLabel.setBounds(560, 35, 100, 25);
        panel.add(priceLabel);
        // Bouton supprimer
        JButton deleteBtn = new JButton("X");
        deleteBtn.setFont(new Font("Serif", Font.BOLD, 12));
        deleteBtn.setBackground(new Color(220, 0, 0)); // Red for delete
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBorder(BorderFactory.createLineBorder(new Color(139, 0, 0), 1)); // Dark red border
        deleteBtn.setBounds(640, 35, 45, 25);
        panel.add(deleteBtn);
        return panel;
    }
    // Remove standalone main; run from MainFrame
}