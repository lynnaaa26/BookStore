package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.List;

public class SearchPanel extends JPanel {
    private BookController bookController;

    public SearchPanel(MainFrame frame) {
        bookController = frame.getBookController();
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // North: Search and categories
        RoundedPanel northPanel = createNorthPanel(frame);
        add(northPanel, BorderLayout.NORTH);

        // Center: Best sellers
        RoundedPanel centerPanel = createCenterPanel(frame);
        add(centerPanel, BorderLayout.CENTER);

        // Footer: Back button
        RoundedPanel footer = createFooter(frame);
        add(footer, BorderLayout.SOUTH);
    }

    private RoundedPanel createNorthPanel(MainFrame frame) {
        RoundedPanel northPanel = new RoundedPanel(8);
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBackground(Color.WHITE);
        northPanel.setOpaque(true);

        // Search bar
        RoundedPanel searchPanel = new RoundedPanel(6);
        searchPanel.setLayout(new BorderLayout(10, 0));
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JTextField searchField = new JTextField("Search books...");
        searchField.setFont(new Font("Serif", Font.PLAIN, 14));
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        JButton searchBtn = Theme.createThemedButton("🔍", 16, e -> frame.navigateTo("SEARCH"));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.setBackground(Color.WHITE);
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        northPanel.add(searchPanel);
        northPanel.add(Box.createVerticalStrut(10));

        // Categories
        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        catPanel.setBackground(Color.WHITE);
        JLabel catTitle = new JLabel("Find your next favorite story...");
        catTitle.setFont(new Font("Serif", Font.BOLD, 16));
        catTitle.setForeground(Color.BLACK);
        catPanel.add(catTitle);

        String[] categories = {"Classics", "Horror", "History", "Fiction", "Self-help", "Romance", "Religion", "Biography", "Science", "Adventure"};
        for (String c : categories) {
            JButton catBtn = Theme.createThemedButton(c, 11, e -> frame.navigateTo("BOOKS"));
            catBtn.setForeground(Color.BLACK);
            catBtn.setBackground(Color.WHITE);
            catBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            catBtn.setPreferredSize(new Dimension(95, 25));
            catPanel.add(catBtn);
        }
        northPanel.add(catPanel);

        return northPanel;
    }

    private RoundedPanel createCenterPanel(MainFrame frame) {
        RoundedPanel centerPanel = new RoundedPanel(12);
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setOpaque(true);

        JLabel bestLabel = new JLabel("★ Best Sellers of the Month ★");
        bestLabel.setFont(new Font("Serif", Font.BOLD, 20));
        bestLabel.setForeground(Color.BLACK);
        bestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bestLabel);
        centerPanel.add(Box.createVerticalStrut(15));

        List<Book> allBooks = bookController.getAllBooks();
        List<Book> bestsellers = allBooks.subList(0, Math.min(3, allBooks.size()));
        JPanel booksRow = new JPanel(new GridLayout(1, 3, 10, 0));
        booksRow.setBackground(Color.WHITE);
        booksRow.setOpaque(true);
        for (Book b : bestsellers) {
            RoundedPanel bookPanel = createBestsellerItem(b, frame);
            booksRow.add(bookPanel);
        }
        centerPanel.add(booksRow);
        centerPanel.add(Box.createVerticalStrut(20));

        return centerPanel;
    }

    private RoundedPanel createBestsellerItem(Book b, MainFrame frame) {
        RoundedPanel bookPanel = new RoundedPanel(8);
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.setPreferredSize(new Dimension(120, 220));
        bookPanel.setMaximumSize(new Dimension(120, 220));
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        JLabel imagePlaceholder = Theme.createBookImageLabel(b.getImagePath(), 100, 140);
        imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(imagePlaceholder);
        bookPanel.add(Box.createVerticalStrut(5));

        JLabel titleLabel = new JLabel(b.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 12));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 11));
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 11));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(priceLabel);

      

        JButton addBtn = Theme.createThemedButton("Add", 10, e -> frame.navigateTo("CART"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Theme.GREEN);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(addBtn);

        return bookPanel;
    }

    private RoundedPanel createFooter(MainFrame frame) {
        RoundedPanel footer = new RoundedPanel(8);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);
        JButton backBtn = Theme.createThemedButton("← Back to Home", 14, e -> frame.navigateTo("HOME"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        footer.add(backBtn);
        return footer;
    }
}