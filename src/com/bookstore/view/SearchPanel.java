package com.bookstore.view;

import com.bookController.BookController;
import com.bookstore.model.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SearchPanel extends JPanel {

    private BookController bookController;

    // NEW: fields used by search logic
    private JTextField searchField;
    private JPanel resultsPanel;

    public SearchPanel(MainFrame frame) {
        bookController = frame.getBookController();

        setLayout(new BorderLayout(40, 40)); // Slightly reduced gaps
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40)); // Slightly reduced border

        // North: Search and categories
        RoundedPanel northPanel = createNorthPanel(frame);
        add(northPanel, BorderLayout.NORTH);

        // Center: Best sellers + search results
        RoundedPanel centerPanel = createCenterPanel(frame);
        add(centerPanel, BorderLayout.CENTER);

        // Footer: Back button
        RoundedPanel footer = createFooter(frame);
        add(footer, BorderLayout.SOUTH);
    }

    private RoundedPanel createNorthPanel(MainFrame frame) {
        RoundedPanel northPanel = new RoundedPanel(16); // Medium rounding
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        northPanel.setBackground(Color.WHITE);
        northPanel.setOpaque(true);

        // Search bar
        RoundedPanel searchPanel = new RoundedPanel(12); // Medium rounding
        searchPanel.setLayout(new BorderLayout(20, 0)); // Medium gap
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Medium thickness

        // use field, not local variable
        searchField = new JTextField("Search books...");
        searchField.setFont(new Font("Serif", Font.PLAIN, 20)); // Medium font
        searchField.setBackground(Color.WHITE);
        searchField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Medium thickness

        JButton searchBtn = Theme.createThemedButton("🔍", 26, e -> {
            String q = searchField.getText().trim();
            List<Book> books;
            if (q.isEmpty() || q.equals("Search books...")) {
                books = bookController.getAllBooks();
            } else {
                books = bookController.searchBooks(q);
            }

            // optional debug:
            // System.out.println("Search = '" + q + "', results = " + books.size());

            showSearchResults(books, frame);
        });

        searchBtn.setForeground(Color.BLACK);
        searchBtn.setBackground(Color.WHITE);

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchBtn, BorderLayout.EAST);
        northPanel.add(searchPanel);
        northPanel.add(Box.createVerticalStrut(25)); // Medium strut

        // Categories
        JPanel catPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 12)); // Medium gaps
        catPanel.setBackground(Color.WHITE);

        JLabel catTitle = new JLabel("Find your next favorite story...");
        catTitle.setFont(new Font("Serif", Font.BOLD, 25)); // Medium font
        catTitle.setForeground(Color.BLACK);
        catPanel.add(catTitle);

        String[] categories = {
                "Classics", "Horror", "History", "Fiction", "Self-help",
                "Romance", "Religion", "Biography", "Science", "Adventure"
        };

        for (String c : categories) {
            JButton catBtn = Theme.createThemedButton(c, 18, e -> frame.navigateTo("BOOKS")); // Medium font
            catBtn.setForeground(Color.BLACK);
            catBtn.setBackground(Color.WHITE);
            catBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Medium thickness
            catBtn.setPreferredSize(new Dimension(145, 45)); // Medium button
            catPanel.add(catBtn);
        }

        northPanel.add(catPanel);
        return northPanel;
    }
    private RoundedPanel createCenterPanel(MainFrame frame) {
        RoundedPanel centerPanel = new RoundedPanel(20); // Medium rounding
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setOpaque(true);

        JLabel bestLabel = new JLabel("★ Best Sellers of the Month ★");
        bestLabel.setFont(new Font("Serif", Font.BOLD, 32)); // Medium font
        bestLabel.setForeground(Color.BLACK);
        bestLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(bestLabel);
        centerPanel.add(Box.createVerticalStrut(35)); // Medium strut

        List<Book> allBooks = bookController.getAllBooks();
        List<Book> bestsellers = allBooks.subList(0, Math.min(4, allBooks.size())); // Adjusted for Mrs Dowly

        JPanel booksRow = new JPanel(new GridLayout(1, 4, 25, 0)); // Medium gap between books
        booksRow.setBackground(Color.WHITE);
        booksRow.setOpaque(true);

        for (Book b : bestsellers) {
            RoundedPanel bookPanel = createBestsellerItem(b, frame);
            booksRow.add(bookPanel);
        }

        centerPanel.add(booksRow);
        centerPanel.add(Box.createVerticalStrut(40)); // Medium strut

        // NEW: results area for searches
        centerPanel.add(Box.createVerticalStrut(20));
        resultsPanel = new JPanel();
        resultsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 15));
        resultsPanel.setBackground(Color.WHITE);
        centerPanel.add(resultsPanel);

        return centerPanel;
    }

    private RoundedPanel createBestsellerItem(Book b, MainFrame frame) {
        RoundedPanel bookPanel = new RoundedPanel(16); // Medium rounding
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Medium thickness
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.setPreferredSize(new Dimension(180, 300)); // Medium overall size
        bookPanel.setMaximumSize(new Dimension(180, 300));
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        JLabel imagePlaceholder = Theme.createBookImageLabel(b.getImagePath(), 160, 220); // Medium image
        imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(imagePlaceholder);
        bookPanel.add(Box.createVerticalStrut(12)); // Medium strut

        JLabel titleLabel = new JLabel(b.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16)); // Medium font
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 14)); // Medium font
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 14)); // Medium font
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(priceLabel);

        JButton addBtn = Theme.createThemedButton("Add", 14, e -> frame.navigateTo("CART")); // Medium font
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.LIGHT_GRAY);
        addBtn.setPreferredSize(new Dimension(90, 40)); // Medium button
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(addBtn);

        return bookPanel;
    }
    private RoundedPanel createFooter(MainFrame frame) {
        RoundedPanel footer = new RoundedPanel(16); // Medium rounding
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);

        JButton backBtn = Theme.createThemedButton("← Back to Home", 18, e -> frame.navigateTo("HOME")); // Medium font
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Medium thickness
        backBtn.setPreferredSize(new Dimension(180, 45)); // Medium button
        footer.add(backBtn);

        return footer;
    }

    // NEW: draw search results in resultsPanel
    private void showSearchResults(List<Book> books, MainFrame frame) {
        if (resultsPanel == null) {
            return;
        }
        resultsPanel.removeAll();

        for (Book b : books) {
            JPanel card = createResultCard(b, frame);
            resultsPanel.add(card);
        }

        resultsPanel.revalidate();
        resultsPanel.repaint();
    }

    // NEW: card used for each search result
    private JPanel createResultCard(Book b, MainFrame frame) {
        RoundedPanel card = new RoundedPanel(12);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        card.setPreferredSize(new Dimension(160, 260));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel imageLabel = Theme.createBookImageLabel(b.getImagePath(), 140, 180);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(imageLabel);

        card.add(Box.createVerticalStrut(5));

        JLabel titleLabel = new JLabel("<html><center>" + b.getTitle() + "</center></html>");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 14));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 12));
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 12));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priceLabel);

        JButton addBtn = Theme.createThemedButton("Add", 12, e -> frame.navigateTo("CART"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Color.LIGHT_GRAY);
        addBtn.setPreferredSize(new Dimension(80, 35));
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(addBtn);

        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        return card;
    }
}