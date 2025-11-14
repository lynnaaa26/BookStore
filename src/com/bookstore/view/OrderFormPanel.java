package com.bookstore.view;
import javax.swing.*;
import java.awt.*;

public class OrderFormPanel extends JPanel {
    public OrderFormPanel(MainFrame frame) {
        setLayout(null);
        setBackground(new Color(245, 245, 220)); // Beige background
        // ---------- HEADER ----------
        JLabel logo = new JLabel("📖 Story time★");
        logo.setFont(new Font("Serif", Font.ITALIC, 20));
        logo.setForeground(new Color(101, 67, 33)); // Saddle brown
        logo.setBounds(40, 20, 200, 30);
        add(logo);
        JButton backBtn = new JButton("← Back");
        backBtn.setFocusPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setBorderPainted(false);
        backBtn.setFont(new Font("Serif", Font.PLAIN, 14));
        backBtn.setForeground(new Color(101, 67, 33)); // Saddle brown
        backBtn.setBounds(40, 60, 100, 25);
        backBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        backBtn.addActionListener(e -> frame.navigateTo("CART")); // Back to cart
        add(backBtn);
        // --- Icônes à droite (Search, Wishlist, Cart) ---
        JButton searchBtn = new JButton("🔍");
        searchBtn.setFocusPainted(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false);
        searchBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        searchBtn.setForeground(new Color(101, 67, 33)); // Saddle brown
        searchBtn.setBounds(800, 25, 50, 30);
        searchBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        searchBtn.addActionListener(e -> frame.navigateTo("SEARCH"));
        add(searchBtn);
        JButton favBtn = new JButton("♡");
        favBtn.setFocusPainted(false);
        favBtn.setContentAreaFilled(false);
        favBtn.setBorderPainted(false);
        favBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        favBtn.setForeground(new Color(101, 67, 33)); // Saddle brown
        favBtn.setBounds(845, 25, 50, 30);
        favBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        favBtn.addActionListener(e -> frame.navigateTo("WISHLIST"));
        add(favBtn);
        JButton cartBtn = new JButton("🛒");
        cartBtn.setFocusPainted(false);
        cartBtn.setContentAreaFilled(false);
        cartBtn.setBorderPainted(false);
        cartBtn.setFont(new Font("Serif", Font.PLAIN, 20));
        cartBtn.setForeground(new Color(101, 67, 33)); // Saddle brown
        cartBtn.setBounds(890, 25, 50, 30);
        cartBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cartBtn.addActionListener(e -> frame.navigateTo("CART"));
        add(cartBtn);
        // ---------- TITRE PRINCIPAL ----------
        JLabel title = new JLabel("YOUR ORDER");
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(new Color(101, 67, 33)); // Saddle brown
        title.setBounds(380, 130, 200, 30);
        add(title);
        // ---------- FORMULAIRE ----------
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(4, 2, 10, 15));
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
        formPanel.setBounds(300, 180, 350, 180);
        add(formPanel);
        JLabel firstNameLabel = new JLabel("FIRST NAME");
        firstNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        firstNameLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        JTextField firstNameField = new JTextField();
        firstNameField.setFont(new Font("Serif", Font.PLAIN, 12));
        firstNameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        JLabel lastNameLabel = new JLabel("LAST NAME");
        lastNameLabel.setFont(new Font("Serif", Font.BOLD, 12));
        lastNameLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        JTextField lastNameField = new JTextField();
        lastNameField.setFont(new Font("Serif", Font.PLAIN, 12));
        lastNameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        JLabel phoneLabel = new JLabel("PHONE NUMBER");
        phoneLabel.setFont(new Font("Serif", Font.BOLD, 12));
        phoneLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        JTextField phoneField = new JTextField("+213");
        phoneField.setFont(new Font("Serif", Font.PLAIN, 12));
        phoneField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        JLabel addressLabel = new JLabel("DELIVERY ADDRESS");
        addressLabel.setFont(new Font("Serif", Font.BOLD, 12));
        addressLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        JTextField addressField = new JTextField();
        addressField.setFont(new Font("Serif", Font.PLAIN, 12));
        addressField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        formPanel.add(firstNameLabel);
        formPanel.add(firstNameField);
        formPanel.add(lastNameLabel);
        formPanel.add(lastNameField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        formPanel.add(addressLabel);
        formPanel.add(addressField);
        // ---------- BOUTONS ----------
        JButton cancelBtn = new JButton("CANCEL");
        cancelBtn.setBackground(new Color(220, 220, 200)); // Light beige
        cancelBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        cancelBtn.setFocusPainted(false);
        cancelBtn.setFont(new Font("Serif", Font.BOLD, 13));
        cancelBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        cancelBtn.setBounds(350, 400, 120, 35);
        cancelBtn.addActionListener(e -> frame.navigateTo("CART"));
        add(cancelBtn);
        JButton checkoutBtn = new JButton("CHECKOUT");
        checkoutBtn.setBackground(new Color(220, 220, 200)); // Light beige
        checkoutBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        checkoutBtn.setFocusPainted(false);
        checkoutBtn.setFont(new Font("Serif", Font.BOLD, 13));
        checkoutBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        checkoutBtn.setBounds(500, 400, 120, 35);
        checkoutBtn.addActionListener(e -> {
            // TODO: Handle order confirmation (e.g., show success message or navigate to new panel)
            frame.navigateTo("HOME"); // Placeholder: back to home after checkout
        });
        add(checkoutBtn);
    }
}