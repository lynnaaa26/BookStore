package com.bookstore.view;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {

    // Constructor expects a MainFrame
 public CartPanel(MainFrame frame) {
    setLayout(null);
    setBackground(new Color(250, 250, 245));

        // ---------- Titre principal ----------
  JLabel title = new JLabel("YOUR SHOPPING CART");
   title.setFont(new Font("Segoe UI", Font.BOLD, 22));
   title.setBounds(50, 30, 400, 30);
   add(title);

        // ---------- En-têtes ----------
 JLabel bookHeader = new JLabel("Book");
    bookHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
   bookHeader.setBounds(100, 80, 200, 20);
        add(bookHeader);

        JLabel quantityHeader = new JLabel("Quantity");
        quantityHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        quantityHeader.setBounds(400, 80, 100, 20);
        add(quantityHeader);

        JLabel priceHeader = new JLabel("Price");
        priceHeader.setFont(new Font("Segoe UI", Font.BOLD, 14));
        priceHeader.setBounds(550, 80, 100, 20);
        add(priceHeader);

        // ---------- Livre 1 ----------
        JPanel book1 = createBookPanel("The Yellow Wallpaper", "Charlotte Perkins Gilman", "1000 DZD");
        book1.setBounds(80, 110, 700, 100);
        add(book1);

        // ---------- Livre 2 ----------
        JPanel book2 = createBookPanel("To Kill a Mockingbird", "Harper Lee", "1500 DZD");
        book2.setBounds(80, 230, 700, 100);
        add(book2);

        // ---------- Total ----------
        JLabel totalCart = new JLabel("Total cart : 2500 DZD");
        totalCart.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        totalCart.setBounds(500, 370, 200, 25);
        add(totalCart);

        JLabel delivery = new JLabel("Delivery costs : 500 DZD");
        delivery.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        delivery.setBounds(500, 400, 200, 25);
        add(delivery);

        JLabel total = new JLabel("Total : 3000 DZD");
        total.setFont(new Font("Segoe UI", Font.BOLD, 15));
        total.setBounds(500, 430, 200, 25);
        add(total);

        // ---------- Boutons ----------
        JButton continueBtn = new JButton("Continue Shopping");
        continueBtn.setBounds(400, 480, 180, 30);
        continueBtn.addActionListener(e -> frame.navigateTo("HOME"));
        
        add(continueBtn);

        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setBounds(600, 480, 120, 30);
        confirmBtn.addActionListener(e -> frame.navigateTo("ORDERFORM"));
        add(confirmBtn);
    }

    private JPanel createBookPanel(String title, String author, String price) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        // Image placeholder
        JLabel img = new JLabel("(Image)", SwingConstants.CENTER);
        img.setBounds(10, 10, 80, 80);
        img.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(img);

        // Infos du livre
        JLabel name = new JLabel(title);
        name.setFont(new Font("Segoe UI", Font.BOLD, 13));
        name.setBounds(110, 10, 200, 20);
        panel.add(name);

        JLabel authorLabel = new JLabel("Author: " + author);
        authorLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        authorLabel.setBounds(110, 35, 250, 20);
        panel.add(authorLabel);

        // Quantité
        JButton minus = new JButton("-");
        minus.setBounds(400, 35, 45, 25);
        panel.add(minus);

        JLabel quantity = new JLabel("1", SwingConstants.CENTER);
        quantity.setBounds(450, 35, 30, 25);
     panel.add(quantity);

     JButton plus = new JButton("+");
      plus.setBounds(485, 35, 45, 25);
      panel.add(plus);

      // Prix à côté de la quantité
     JLabel priceLabel = new JLabel(price);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
    priceLabel.setBounds(560, 35, 100, 25);
    panel.add(priceLabel);

        // Bouton supprimer
   JButton deleteBtn = new JButton("X");
  deleteBtn.setBounds(640, 35, 45, 25);
 panel.add(deleteBtn);

        return panel;
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }

} 
