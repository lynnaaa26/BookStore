package com.bookstore.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL; // For resource loading

public class BookPanel extends JPanel {
    public BookPanel(MainFrame frame) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 220)); // Beige background
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(245, 245, 220)); // Beige
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // Add back button to header
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Serif", Font.PLAIN, 14));
        backButton.setForeground(new Color(101, 67, 33)); // Saddle brown
        backButton.setFocusable(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setOpaque(false);
        backButton.addActionListener(e -> frame.navigateTo("HOME"));
        header.add(backButton, BorderLayout.WEST);
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        logoPanel.setBackground(new Color(245, 245, 220)); // Beige
        JLabel logoIcon = new JLabel("\uD83D\uDCD6");
        logoIcon.setFont(new Font("Serif", Font.PLAIN, 28));
        logoIcon.setForeground(new Color(101, 67, 33));
        JLabel logoText = new JLabel("Story time ★");
        logoText.setFont(new Font("Serif", Font.BOLD, 26));
        logoText.setForeground(new Color(101, 67, 33));
        logoPanel.add(logoIcon);
        logoPanel.add(logoText);
        header.add(logoPanel, BorderLayout.CENTER);
        // Add cart and wishlist icons to header
        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightIcons.setBackground(new Color(245, 245, 220)); // Beige
        JButton cartIcon = new JButton("🛒");
        cartIcon.setFont(new Font("Serif", Font.PLAIN, 20));
        cartIcon.setForeground(new Color(101, 67, 33));
        cartIcon.setFocusable(false);
        cartIcon.setBorderPainted(false);
        cartIcon.setContentAreaFilled(false);
        cartIcon.setOpaque(false);
        cartIcon.addActionListener(e -> frame.navigateTo("CART"));
        JButton wishlistIcon = new JButton("❤️");
        wishlistIcon.setFont(new Font("Serif", Font.PLAIN, 20));
        wishlistIcon.setForeground(new Color(101, 67, 33));
        wishlistIcon.setFocusable(false);
        wishlistIcon.setBorderPainted(false);
        wishlistIcon.setContentAreaFilled(false);
        wishlistIcon.setOpaque(false);
        wishlistIcon.addActionListener(e -> frame.navigateTo("WISHLIST"));
        rightIcons.add(wishlistIcon);
        rightIcons.add(cartIcon);
        header.add(rightIcons, BorderLayout.EAST);
        JPanel content = new JPanel(new BorderLayout());
        content.setBackground(new Color(245, 245, 220)); // Beige
        JPanel mainBookPanel = new JPanel(new BorderLayout(20, 0));
        mainBookPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainBookPanel.setBackground(new Color(245, 245, 220)); // Beige
        // Main book image
        JLabel mainImageLabel = new JLabel();
        mainImageLabel.setPreferredSize(new Dimension(150, 200));
        mainImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Load main book image ("The Yellow Wallpaper")
        ImageIcon mainIcon = null;
        try {
            URL imageUrl = getClass().getResource("/imagess/yellow.jpg");
            if (imageUrl != null) {
                mainIcon = new ImageIcon(imageUrl);
            }
        } catch (Exception e) {
            System.out.println("Main image not found: yellow.jpg");
        }
        if (mainIcon != null && mainIcon.getIconWidth() > 0) {
            Image scaledMainImage = mainIcon.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
            mainImageLabel.setIcon(new ImageIcon(scaledMainImage));
            mainImageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        } else {
            mainImageLabel.setText("No Image");
            mainImageLabel.setBackground(new Color(245, 245, 220)); // Beige fallback
            mainImageLabel.setOpaque(true);
            mainImageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        }
        // Make main image clickable
        mainImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Refresh or handle zoom; for now, do nothing extra
            }
        });
        mainImageLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mainBookPanel.add(mainImageLabel, BorderLayout.WEST);
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        JLabel titleLabel = new JLabel("The Yellow Wallpaper");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        JLabel priceLabel = new JLabel("1000 DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        priceLabel.setForeground(new Color(34, 139, 34)); // Green for price
        JTextArea desc = new JTextArea(
            "“The Yellow Wallpaper” is written in journal entries by a woman who is on vacation with her husband to a big house after giving birth to their daughter. The woman is suffering from postpartum hysteria and secretly wonders if her husband is why she is not getting better."
        );
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setFont(new Font("Serif", Font.PLAIN, 14));
        desc.setForeground(new Color(139, 69, 19)); // Darker brown for desc
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        // Boutons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        JButton addToCart = new JButton("ADD TO CART");
        addToCart.addActionListener(e -> frame.navigateTo("CART"));
        addToCart.setFont(new Font("Serif", Font.BOLD, 12));
        addToCart.setBackground(new Color(220, 220, 200)); // Light beige
        addToCart.setForeground(new Color(101, 67, 33)); // Saddle brown text
        addToCart.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        JButton addToWishlist = new JButton("ADD TO WISHLIST");
        addToWishlist.addActionListener(e -> frame.navigateTo("WISHLIST"));
        addToWishlist.setFont(new Font("Serif", Font.BOLD, 12));
        addToWishlist.setBackground(new Color(220, 220, 200)); // Light beige
        addToWishlist.setForeground(new Color(101, 67, 33)); // Saddle brown text
        addToWishlist.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
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
        moreBooksPanel.setBackground(new Color(245, 245, 220)); // Beige
        JLabel moreLabel = new JLabel("More Books");
        moreLabel.setFont(new Font("Serif", Font.BOLD, 18));
        moreLabel.setForeground(new Color(101, 67, 33));
        JPanel booksList = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        booksList.setBackground(new Color(245, 245, 220)); // Beige
        // More books data with filenames (map titles to images)
        String[][] moreBooks = {
            {"To Kill a Mockingbird", "1500 DZD", "mockingbird.jpg"},
            {"Have Always Lived Castle", "1000 DZD", "we castle.jpg"}, 
            {"Mrs Dalloway", "1200 DZD", "d.jpg"}, 
            {"Frankenstein: The 1818 TEXT", "2000 DZD", "f.jpg"}, 
            {"Fahrenheit", "2000 DZD", "451.jpg"} 
        };
        for (String[] b : moreBooks) {
            JPanel bookCard = createBookCard(b[0], b[1], b[2], frame);
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

    private JPanel createBookCard(String title, String price, String imageFile, MainFrame frame) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(150, 230));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });
        card.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // Image panel/label
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(130, 160));
        imgLabel.setMaximumSize(new Dimension(130, 160));
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Load image
        ImageIcon icon = null;
        try {
            URL imageUrl = getClass().getResource("/imagess/" + imageFile);
            if (imageUrl != null) {
                icon = new ImageIcon(imageUrl);
            }
        } catch (Exception e) {
            System.out.println("More book image not found: " + imageFile);
        }
        if (icon != null && icon.getIconWidth() > 0) {
            Image scaledImage = icon.getImage().getScaledInstance(130, 160, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaledImage));
            imgLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        } else {
            imgLabel.setText("No Image");
            imgLabel.setBackground(new Color(245, 245, 220)); // Beige fallback
            imgLabel.setOpaque(true);
            imgLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        }
        card.add(Box.createVerticalStrut(10));
        card.add(imgLabel);
        card.add(Box.createVerticalStrut(10));
        JLabel label = new JLabel("<html><center>" + title.replaceAll("\n", "<br/>") + "<br/>" + price + "</center></html>");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        label.setForeground(new Color(101, 67, 33));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(label);
        return card;
    }
}