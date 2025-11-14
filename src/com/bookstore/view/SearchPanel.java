package com.bookstore.view;
import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SearchPanel extends JPanel {
    public SearchPanel(MainFrame frame) {
        setBackground(new Color(245, 245, 220)); // beige background
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //--------- search bar + categories --------------
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS)); // vertical stack
        northPanel.setOpaque(false); 
        // search bar panel
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(new Color(245, 245, 220)); // beige
        searchPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // light beige border
        JTextField searchField = new JTextField("Search books...");
        searchField.setFont(new Font("Serif", Font.PLAIN, 14));
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        JButton searchBtn = new JButton("🔍");
        searchBtn.setFont(new Font("Serif", Font.PLAIN, 16));
        searchBtn.setBackground(new Color(220, 220, 200)); // light beige
        searchBtn.setForeground(new Color(101, 67, 33)); //  brown text
        searchBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // beige border
        searchBtn.addActionListener(e -> frame.navigateTo("SEARCH")); // simulate search or navigate
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        northPanel.add(searchPanel);
        northPanel.add(Box.createVerticalStrut(10)); // spacing
        // categories
        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        catPanel.setBackground(new Color(245, 245, 220)); // Beige
        JLabel catTitle = new JLabel("Find your next favorite story...");
        catTitle.setFont(new Font("Serif", Font.BOLD, 16));
        catTitle.setForeground(new Color(101, 67, 33)); // Saddle brown
        catPanel.add(catTitle);
        String[] categories = {"Classics", "Horror", "History", "Fiction", "Self-help",
                               "Romance", "Religion", "Biography", "Science", "Adventure"};
        for (String c : categories) {
            JButton catBtn = new JButton(c);
            catBtn.setPreferredSize(new Dimension(95, 25)); //
            catBtn.setFont(new Font("Serif", Font.BOLD, 11));
            catBtn.setBackground(new Color(220, 220, 200)); // Light beige
            catBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
            catBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
            catBtn.addActionListener(e -> {
                // Simulate search; for now, navigate to BOOKS
                frame.navigateTo("BOOKS");
            });
            catPanel.add(catBtn);
        }
        northPanel.add(catPanel);
        add(northPanel, BorderLayout.NORTH);
        //---------------- best sellers ---------------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setOpaque(false); // Transparent for beige bg
        JLabel bestLabel = new JLabel("★ Best Sellers of the Month ★");
        bestLabel.setFont(new Font("Serif", Font.BOLD, 20));
        bestLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        bestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bestLabel);
        centerPanel.add(Box.createVerticalStrut(15));
        // books
        String[][] bestsellers = {
                {"The Yellow Wallpaper", "Charlotte Perkins Gilman", "1000 DZD", "yellow.jpg"},
                {"To Kill A Mockingbird", "Harper Lee", "1500 DZD", "mockingbird.jpg"},
                {"Sense and Sensibility", "Jane Austen", "1300 DZD", "sense.jpg"}
        };
        JLabel bestSellersTitle = new JLabel("Best Sellers of the Month!");
        bestSellersTitle.setFont(new Font("Serif", Font.BOLD, 16));
        bestSellersTitle.setForeground(new Color(101, 67, 33)); // Saddle brown
        bestSellersTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bestSellersTitle);
        centerPanel.add(Box.createVerticalStrut(10));
        // Horizontal layout for three books
        JPanel booksRow = new JPanel(new GridLayout(1, 3, 10, 0));
        booksRow.setOpaque(false); // Transparent
        for (String[] b : bestsellers) {
            JPanel bookPanel = new JPanel();
            bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
            bookPanel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
            bookPanel.setBackground(Color.WHITE);
            bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.setPreferredSize(new Dimension(120, 220));
            bookPanel.setMaximumSize(new Dimension(120, 220));
            // Make bookPanel clickable
            bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    frame.navigateTo("BOOKS");
                }
            });
            bookPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            // image placeholder
            JLabel imagePlaceholder = new JLabel();
            imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
            imagePlaceholder.setPreferredSize(new Dimension(100, 140));
            imagePlaceholder.setMaximumSize(new Dimension(100, 140));
            imagePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
            // Load image
            ImageIcon icon = null;
            try {
                URL imageUrl = getClass().getResource("/imagess/" + b[3]); // Fixed path: /images/
                if (imageUrl != null) {
                    icon = new ImageIcon(imageUrl);
                }
            } catch (Exception e) {
                System.out.println("Image not found: " + b[3]);
            }
            if (icon != null && icon.getIconWidth() > 0) {
                Image scaledImage = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
                imagePlaceholder.setIcon(new ImageIcon(scaledImage));
                imagePlaceholder.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
            } else {
                imagePlaceholder.setText("No Image");
                imagePlaceholder.setBackground(new Color(245, 245, 220)); // Beige fallback
                imagePlaceholder.setOpaque(true);
                imagePlaceholder.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
            }
            bookPanel.add(imagePlaceholder);
            bookPanel.add(Box.createVerticalStrut(5));
            JLabel titleLabel = new JLabel(b[0]);
            titleLabel.setFont(new Font("Serif", Font.BOLD, 12));
            titleLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(titleLabel);
            JLabel authorLabel = new JLabel("Author: " + b[1]);
            authorLabel.setFont(new Font("Serif", Font.ITALIC, 11));
            authorLabel.setForeground(new Color(139, 69, 19)); // Darker brown
            authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(authorLabel);
            JLabel priceLabel = new JLabel(b[2]);
            priceLabel.setFont(new Font("Serif", Font.PLAIN, 11));
            priceLabel.setForeground(new Color(34, 139, 34)); // Green
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(priceLabel);
            JLabel ratingLabel = new JLabel("★★★★★");
            ratingLabel.setFont(new Font("Serif", Font.PLAIN, 11));
            ratingLabel.setForeground(new Color(255, 215, 0)); // Gold stars
            ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            bookPanel.add(ratingLabel);
            bookPanel.add(Box.createVerticalStrut(5));
            JButton addBtn = new JButton("Add");
            addBtn.setFont(new Font("Serif", Font.BOLD, 10)); //
            addBtn.setBackground(new Color(220, 220, 200)); // Light beige
            addBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
            addBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
            addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            addBtn.addActionListener(e -> frame.navigateTo("CART"));
            bookPanel.add(addBtn);
            booksRow.add(bookPanel);
        }
        centerPanel.add(booksRow);
        centerPanel.add(Box.createVerticalStrut(20));
        add(centerPanel, BorderLayout.CENTER);
        //----------------back button ----------------
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(new Color(245, 245, 220)); // beige
        JButton backBtn = new JButton("← Back to Home");
        backBtn.setFont(new Font("Serif", Font.PLAIN, 14));
        backBtn.setBackground(new Color(220, 220, 200)); // light beige
        backBtn.setForeground(new Color(101, 67, 33)); //  brown text
        backBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // beige border
        backBtn.addActionListener(e -> frame.navigateTo("HOME"));
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }
}