package com.bookstore.view;

import javax.swing.*;

import java.awt.*;

public class BookPanel extends JPanel {

    public BookPanel(MainFrame frame) {

        setLayout(new BorderLayout());

        setBackground(Color.WHITE);

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(Color.WHITE);

        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add back button to header

        JButton backButton = new JButton("← Back");

        backButton.setFont(new Font("Serif", Font.PLAIN, 14));

        backButton.setFocusable(false);

        backButton.setBorderPainted(false);

        backButton.setContentAreaFilled(false);

        backButton.setOpaque(false);

        backButton.addActionListener(e -> frame.navigateTo("HOME"));

        header.add(backButton, BorderLayout.WEST);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));

        logoPanel.setBackground(Color.WHITE);

        JLabel logoIcon = new JLabel("\uD83D\uDCD6");

        logoIcon.setFont(new Font("Serif", Font.PLAIN, 28));

        JLabel logoText = new JLabel("Story time ★");

        logoText.setFont(new Font("Serif", Font.BOLD, 26));

        logoPanel.add(logoIcon);

        logoPanel.add(logoText);

        header.add(logoPanel, BorderLayout.CENTER);

        // Add cart and wishlist icons to header

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));

        rightIcons.setBackground(Color.WHITE);

        JButton cartIcon = new JButton("🛒");

        cartIcon.setFont(new Font("Serif", Font.PLAIN, 20));

        cartIcon.setFocusable(false);

        cartIcon.setBorderPainted(false);

        cartIcon.setContentAreaFilled(false);

        cartIcon.setOpaque(false);

        cartIcon.addActionListener(e -> frame.navigateTo("CART"));

        JButton wishlistIcon = new JButton("❤️");

        wishlistIcon.setFont(new Font("Serif", Font.PLAIN, 20));

        wishlistIcon.setFocusable(false);

        wishlistIcon.setBorderPainted(false);

        wishlistIcon.setContentAreaFilled(false);

        wishlistIcon.setOpaque(false);

        wishlistIcon.addActionListener(e -> frame.navigateTo("WISHLIST"));

        rightIcons.add(wishlistIcon);

        rightIcons.add(cartIcon);

        header.add(rightIcons, BorderLayout.EAST);

        JPanel content = new JPanel(new BorderLayout());

        content.setBackground(Color.WHITE);

        JPanel mainBookPanel = new JPanel(new BorderLayout(20, 0));

        mainBookPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        mainBookPanel.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel();

        imagePanel.setPreferredSize(new Dimension(150, 200));

        imagePanel.setBackground(Color.LIGHT_GRAY);

        mainBookPanel.add(imagePanel, BorderLayout.WEST);

        JPanel infoPanel = new JPanel();

        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

        infoPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("The Yellow Wallpaper");

        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        JLabel priceLabel = new JLabel("1000 DZD");

        priceLabel.setFont(new Font("Serif", Font.PLAIN, 16));

        priceLabel.setForeground(new Color(80, 80, 80));

        JTextArea desc = new JTextArea(

            "“The Yellow Wallpaper” is written in journal entries by a woman who is on vacation with her husband to a big house after giving birth to their daughter. The woman is suffering from postpartum hysteria and secretly wonders if her husband is why she is not getting better."

        );

        desc.setLineWrap(true);

        desc.setWrapStyleWord(true);

        desc.setEditable(false);

        desc.setOpaque(false);

        desc.setFont(new Font("Serif", Font.PLAIN, 14));

        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));

        // Boutons

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JButton addToCart = new JButton("ADD TO CART");

        addToCart.addActionListener(e -> frame.navigateTo("CART"));

        addToCart.setFont(new Font("Serif", Font.BOLD, 12));

        JButton addToWishlist = new JButton("ADD TO WISHLIST");

        addToWishlist.addActionListener(e -> frame.navigateTo("WISHLIST"));

        addToWishlist.setFont(new Font("Serif", Font.BOLD, 12));

        addToCart.setBackground(new Color(100, 100, 100));

        addToCart.setForeground(Color.WHITE);

        addToWishlist.setBackground(new Color(60, 160, 60));

        addToWishlist.setForeground(Color.WHITE);

        buttonPanel.setBackground(Color.WHITE);

        buttonPanel.add(addToCart);

        buttonPanel.add(addToWishlist);

        infoPanel.add(titleLabel);

        infoPanel.add(Box.createVerticalStrut(5));

        infoPanel.add(priceLabel);

        infoPanel.add(Box.createVerticalStrut(10));

        infoPanel.add(desc);

        infoPanel.add(Box.createVerticalStrut(15));

        infoPanel.add(buttonPanel);

        mainBookPanel.add(infoPanel, BorderLayout.CENTER);

        // === More Books ===

        JPanel moreBooksPanel = new JPanel();

        moreBooksPanel.setLayout(new BorderLayout());

        moreBooksPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        moreBooksPanel.setBackground(Color.WHITE);

        JLabel moreLabel = new JLabel("More Books");

        moreLabel.setFont(new Font("Serif", Font.BOLD, 18));

        JPanel booksList = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));

        booksList.setBackground(Color.WHITE);

        String[] books = {

            "To Kill a Mockingbird\n1500 DZD",

            "We Have Always Lived in the Castle \n1000 DZD",

            "Mrs Dalloway \n1200 DZD",

            "Frankenstein: The 1818 TEXT \n2000 DZD",

            "Fahrenheit \n2000 DZD"

        };

        for (String b : books) {

            JPanel bookCard = createBookCard(b, frame);

            booksList.add(bookCard);

        }

        moreBooksPanel.add(moreLabel, BorderLayout.NORTH);

        moreBooksPanel.add(new JScrollPane(booksList,

                JScrollPane.VERTICAL_SCROLLBAR_NEVER,

                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.CENTER);

        content.add(mainBookPanel, BorderLayout.NORTH);

        content.add(moreBooksPanel, BorderLayout.CENTER);

        add(header, BorderLayout.NORTH);

        add(content, BorderLayout.CENTER);

    }

    private static JButton createHeaderButton(String text) {

        JButton b = new JButton(text);

        b.setFont(new Font("Serif", Font.PLAIN, 18));

        b.setFocusable(false);

        b.setBorderPainted(false);

        b.setContentAreaFilled(false);

        b.setOpaque(false);

        b.setPreferredSize(new Dimension(40, 30));

        return b;

    }

    private JPanel createBookCard(String title, MainFrame frame) {

        JPanel card = new JPanel();

        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));

        card.setPreferredSize(new Dimension(150, 230));

        card.setBackground(Color.WHITE);

        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        card.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {

                // For now, navigate to same BookPanel; in future, make dynamic

                frame.navigateTo("BOOKS");

            }

        });

        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        JPanel img = new JPanel();

        img.setPreferredSize(new Dimension(130, 160));

        img.setBackground(Color.LIGHT_GRAY);

        img.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("<html><center>" + title.replaceAll("\n", "<br/>") + "</center></html>");

        label.setFont(new Font("Serif", Font.PLAIN, 13));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(10));

        card.add(img);

        card.add(Box.createVerticalStrut(10));

        card.add(label);

        return card;

    }

}