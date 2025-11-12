package com.bookstore.view;

import javax.swing.*;
import java.awt.*;

public class WishlistPanel extends JPanel {

    public WishlistPanel(MainFrame frame) {

        // ---------- Réglages de base du panneau ----------
        setLayout(null); // Positionnement manuel
        setBackground(new Color(250, 250, 245)); // Couleur de fond claire

        // ---------- Titre principal ----------
        JLabel title = new JLabel("♡ Wishlist");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setBounds(50, 30, 300, 30);
        add(title);

        // ---------- Premier livre ----------
        JPanel book1 = new JPanel();
        book1.setLayout(null);
        book1.setBackground(Color.WHITE);
        book1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        book1.setBounds(100, 120, 200, 280);
        add(book1);

        JLabel img1 = new JLabel("(Image)", SwingConstants.CENTER);
        img1.setBounds(25, 20, 150, 150);
        img1.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        book1.add(img1);

        JLabel name1 = new JLabel("Harry Potter pack", SwingConstants.CENTER);
        name1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        name1.setBounds(10, 180, 180, 20);
        book1.add(name1);

        JLabel author1 = new JLabel("Auteur : J.K. Rowling", SwingConstants.CENTER);
        author1.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        author1.setBounds(10, 200, 180, 20);
        book1.add(author1);

        JLabel price1 = new JLabel("12000 DZD", SwingConstants.CENTER);
        price1.setFont(new Font("Segoe UI", Font.BOLD, 13));
        price1.setBounds(10, 230, 180, 20);
        book1.add(price1);

        // ---------- Deuxième livre ----------
        JPanel book2 = new JPanel();
        book2.setLayout(null);
        book2.setBackground(Color.WHITE);
        book2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        book2.setBounds(360, 120, 200, 280);
        add(book2);

        JLabel img2 = new JLabel("(image)", SwingConstants.CENTER);
        img2.setBounds(25, 20, 150, 150);
        img2.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        book2.add(img2);



        JLabel name2 = new JLabel("To Kill a Mockingbird", SwingConstants.CENTER);
        name2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        name2.setBounds(10, 180, 180, 20);
        book2.add(name2);

        JLabel author2 = new JLabel("Auteur : Harper Lee", SwingConstants.CENTER);
        author2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        author2.setBounds(10, 200, 180, 20);
        book2.add(author2);

        JLabel price2 = new JLabel("2000 DZD", SwingConstants.CENTER);
        price2.setFont(new Font("Segoe UI", Font.BOLD, 13));
        price2.setBounds(10, 230, 180, 20);
        book2.add(price2);

        // ---------- Troisième livre ----------
        JPanel book3 = new JPanel();
        book3.setLayout(null);
        book3.setBackground(Color.WHITE);
        book3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        book3.setBounds(620, 120, 200, 280);
        add(book3);

        JLabel img3 = new JLabel("(Image)", SwingConstants.CENTER);
        img3.setBounds(25, 20, 150, 150);
        img3.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        book3.add(img3);

        JLabel name3 = new JLabel("We have always lived in a castle", SwingConstants.CENTER);
        name3.setFont(new Font("Segoe UI", Font.BOLD, 12));
        name3.setBounds(10, 180, 180, 20);
        book3.add(name3);

        JLabel author3 = new JLabel("Auteur : Shirley Jackson", SwingConstants.CENTER);
        author3.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        author3.setBounds(10, 200, 180, 20);
        book3.add(author3);

        JLabel price3 = new JLabel("2900 DZD", SwingConstants.CENTER);
        price3.setFont(new Font("Segoe UI", Font.BOLD, 13));
        price3.setBounds(10, 230, 180, 20);
        book3.add(price3);
        
        
        
        JButton backBtn = new JButton("← Back to Home");
        backBtn.setBounds(650, 450, 150, 30);
        backBtn.addActionListener(e -> frame.navigateTo("HOME"));
        add(backBtn);
    }
}