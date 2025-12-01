package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import com.bookController.CartController;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Optional;

public class HomePanel extends JPanel {
    private BookController bookController;

    public HomePanel(MainFrame mainFrame) {
        bookController = mainFrame.getBookController();
        setLayout(new BorderLayout(10, 10)); // Reduced gaps
        setBackground(Color.WHITE); // White background
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Reduced overall padding

        // Header
        JPanel header = createHeader(mainFrame);
        add(header, BorderLayout.NORTH);

        // Content: Use BoxLayout for responsive horizontal split
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(Color.WHITE); // White background
        contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10)); // Minor right padding

        // Left panel (featured books) - Bigger space: max width 800px, grouped by genre, 3 books per row, scrollable
        JPanel leftPanel = createLeftPanel(mainFrame);
        leftPanel.setMaximumSize(new Dimension(800, Integer.MAX_VALUE)); // Wider for big place
        leftPanel.setPreferredSize(new Dimension(700, 600)); // Base size
        leftPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPanel.add(leftPanel);
        contentPanel.add(Box.createHorizontalStrut(30)); // Increased gap to move bestsellers away

        // Right panel (bestsellers) - Small place: narrower, 3 books in one row
        JPanel rightPanel = createRightPanel(mainFrame);
        rightPanel.setMinimumSize(new Dimension(300, 300)); // Smaller height
        rightPanel.setMaximumSize(new Dimension(350, Integer.MAX_VALUE)); // Narrower
        rightPanel.setPreferredSize(new Dimension(350, 350)); // Compact
        rightPanel.setAlignmentY(Component.TOP_ALIGNMENT);
        contentPanel.add(rightPanel);

        // Add glue to absorb extra space
        contentPanel.add(Box.createHorizontalGlue());

        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createHeader(MainFrame mainFrame) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 10, 0)); // Reduced top/bottom padding

        JLabel header = new JLabel(" 📖 Story time ★", SwingConstants.LEFT);
        header.setFont(new Font("Serif", Font.BOLD, 26));
        header.setForeground(Color.BLACK);
        topPanel.add(header, BorderLayout.WEST);

        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightButtonsPanel.setOpaque(false);
        JButton search = Theme.createThemedButton("🔍", 16, e -> mainFrame.navigateTo("SEARCH"));
        search.setForeground(Color.BLACK);
        search.setBackground(Color.WHITE);
        JButton wishlistButton = Theme.createThemedButton("❤️", 16, e -> mainFrame.navigateTo("WISHLIST"));
        wishlistButton.setForeground(Color.BLACK);
        wishlistButton.setBackground(Color.WHITE);
        JButton cartButton = Theme.createThemedButton("🛒", 16, e -> mainFrame.navigateTo("CART"));
        cartButton.setForeground(Color.BLACK);
        cartButton.setBackground(Color.WHITE);
        rightButtonsPanel.add(search);
        rightButtonsPanel.add(wishlistButton);
        rightButtonsPanel.add(cartButton);
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);

        return topPanel;
    }

    private JPanel createLeftPanel(MainFrame mainFrame) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(Color.WHITE); // White background
        leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Reduced padding

        JLabel subHeader = new JLabel("Find your next favorite story !");
        subHeader.setFont(new Font("Serif", Font.BOLD, 16));
        subHeader.setForeground(Color.BLACK);
        subHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(subHeader);
        leftPanel.add(Box.createVerticalStrut(5)); // Reduced strut

        List<Book> allBooks = bookController.getAllBooks();
        // Simulate genre grouping: Assume first 3 Classics, next 3 Fiction, next 3 Horror (adjust based on model)
        List<Book> classics = allBooks.subList(0, Math.min(3, allBooks.size()));
        List<Book> fiction = allBooks.subList(3, Math.min(6, allBooks.size()));
        List<Book> horror = allBooks.subList(6, Math.min(9, allBooks.size()));

        // Scrollable container for books
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS));
        booksContainer.setBackground(Color.WHITE); // White background

        // Classics row with genre label
        JLabel classicsLabel = new JLabel("Classics"); // No Romance
        classicsLabel.setFont(new Font("Serif", Font.BOLD, 14));
        classicsLabel.setForeground(Color.BLACK);
        classicsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        classicsLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Space above/below genre
        booksContainer.add(classicsLabel);

        JPanel classicsRow = new JPanel(new GridLayout(1, 3, 10, 5)); // 3 books in one line
        classicsRow.setBackground(Color.WHITE); // White background
        for (Book b : classics) {
            String category = "Classics";
            RoundedPanel bookPanel = createBookPanel(b, category, mainFrame);
            classicsRow.add(bookPanel);
        }
        booksContainer.add(classicsRow);

        // Fiction row with genre label
        JLabel fictionLabel = new JLabel("Fiction");
        fictionLabel.setFont(new Font("Serif", Font.BOLD, 14));
        fictionLabel.setForeground(Color.BLACK);
        fictionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fictionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Space above/below genre
        booksContainer.add(fictionLabel);

        JPanel fictionRow = new JPanel(new GridLayout(1, 3, 10, 5)); // 3 books in one line
        fictionRow.setBackground(Color.WHITE); // White background
        for (Book b : fiction) {
            String category = "Fiction";
            RoundedPanel bookPanel = createBookPanel(b, category, mainFrame);
            fictionRow.add(bookPanel);
        }
        booksContainer.add(fictionRow);

        // Horror row with genre label (or next genre)
        JLabel horrorLabel = new JLabel("Horror");
        horrorLabel.setFont(new Font("Serif", Font.BOLD, 14));
        horrorLabel.setForeground(Color.BLACK);
        horrorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        horrorLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0)); // Space above/below genre
        booksContainer.add(horrorLabel);

        JPanel horrorRow = new JPanel(new GridLayout(1, 3, 10, 5)); // 3 books in one line
        horrorRow.setBackground(Color.WHITE); // White background
        for (Book b : horror) {
            String category = "Horror";
            RoundedPanel bookPanel = createBookPanel(b, category, mainFrame);
            horrorRow.add(bookPanel);
        }
        booksContainer.add(horrorRow);

        booksContainer.add(Box.createVerticalStrut(15)); // Increased space between lines of books

        JScrollPane scrollPane = new JScrollPane(booksContainer);
        scrollPane.setPreferredSize(new Dimension(700, 500)); // Fixed viewport for efficiency
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getViewport().setOpaque(false);
        leftPanel.add(scrollPane);

        // Arrow button to show more books (e.g., navigate to full catalog)
        JButton moreBooksBtn = new JButton("View More →"); // Arrow for "more"
        moreBooksBtn.setFont(new Font("Serif", Font.BOLD, 14));
        moreBooksBtn.setBackground(Color.LIGHT_GRAY);
        moreBooksBtn.setForeground(Color.BLACK);
        moreBooksBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        moreBooksBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        moreBooksBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        moreBooksBtn.addActionListener(e -> mainFrame.navigateTo("SEARCH")); // Or custom "MORE" panel
        leftPanel.add(Box.createVerticalStrut(5));
        leftPanel.add(moreBooksBtn);

        // Add vertical glue
        leftPanel.add(Box.createVerticalGlue());

        return leftPanel;
    }

    private JPanel createRightPanel(MainFrame mainFrame) {
        RoundedPanel rightPanel = new RoundedPanel(8); // Rounded for modern feel
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE); // White background
        rightPanel.setBorder(BorderFactory.createTitledBorder(null, "Best sellers of the Month", 0, 0, new Font("Serif", Font.BOLD, 14), Color.BLACK));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
            rightPanel.getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Consistent padding
        ));

        JLabel bestSellersTitle = new JLabel("Best Sellers of the Month!");
        bestSellersTitle.setFont(new Font("Serif", Font.BOLD, 16));
        bestSellersTitle.setForeground(Color.BLACK);
        bestSellersTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(bestSellersTitle);
        rightPanel.add(Box.createVerticalStrut(5)); // Reduced strut

        List<Book> allBooks = bookController.getAllBooks();
        List<Book> bestsellers = allBooks.subList(0, Math.min(3, allBooks.size())); // Keep minimal: 3 books

        // Single row for small space
        JPanel booksRow = new JPanel(new GridLayout(1, 3, 5, 0)); // 3 books in one line
        booksRow.setBackground(Color.WHITE); // White background
        booksRow.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Top padding for row
        for (Book b : bestsellers) {
            RoundedPanel item = createBestsellerItem(b, mainFrame); // Use RoundedPanel
            booksRow.add(item);
        }
        rightPanel.add(booksRow);

        // Add vertical glue to fill space without stretching cards
        rightPanel.add(Box.createVerticalGlue());

        return rightPanel;
    }

    private RoundedPanel createBookPanel(Book b, String category, MainFrame mainFrame) {
        RoundedPanel bookPanel = new RoundedPanel(6); // Subtle rounding
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.WHITE); // White background
        bookPanel.setBorder(BorderFactory.createTitledBorder(null, category, 0, 0, new Font("Serif", Font.BOLD, 12), Color.BLACK));
        bookPanel.setBorder(BorderFactory.createCompoundBorder( // Compact border
            BorderFactory.createLineBorder(Color.BLACK, 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8) // Reduced padding
        ));
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainFrame.navigateTo("BOOKS");
            }
        });

        JLabel imageLabel = Theme.createBookImageLabel(b.getImagePath(), 100, 140);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(imageLabel);
        bookPanel.add(Box.createVerticalStrut(3)); // Minimal spacing

        JLabel title = new JLabel(b.getTitle());
        title.setFont(new Font("Serif", Font.BOLD, 14));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(title);

        JLabel price = new JLabel(b.getPrice() + " DZD");
        price.setFont(new Font("Serif", Font.PLAIN, 12));
        price.setForeground(Theme.GREEN);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(price);

        JButton addBtn = Theme.createThemedButton("Add to Cart", 11, e -> {
            mainFrame.getCartController().addToCart(b, 1);
            int choice = JOptionPane.showConfirmDialog(this, "Added " + b.getTitle() + " to cart!\nGo to Cart now?", "Success", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                mainFrame.navigateTo("CART");
            }
        });
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Theme.GREEN);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(addBtn);

        return bookPanel;
    }

    private RoundedPanel createBestsellerItem(Book b, MainFrame mainFrame) {
        RoundedPanel item = new RoundedPanel(6);
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setBackground(Color.WHITE); // White background
        item.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        item.setPreferredSize(new Dimension(100, 180)); // Slightly smaller for compact row
        item.setMaximumSize(new Dimension(100, 180));
        item.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainFrame.navigateTo("BOOKS");
            }
        });
        item.setBorder(BorderFactory.createCompoundBorder( // Compact border
            item.getBorder(),
            BorderFactory.createEmptyBorder(5, 5, 5, 5) // Reduced padding
        ));

        JLabel imagePlaceholder = Theme.createBookImageLabel(b.getImagePath(), 80, 120); // Smaller image for tight space
        imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(imagePlaceholder);
        item.add(Box.createVerticalStrut(3)); // Minimal

        JLabel titleLabel = new JLabel(b.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 10)); // Smaller font
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(titleLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 9)); // Smaller
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 10));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(priceLabel);

        

        JButton addBtn = Theme.createThemedButton("Add", 9, e -> { // Smaller button
            mainFrame.getCartController().addToCart(b, 1);
            int choice = JOptionPane.showConfirmDialog(this, "Added " + b.getTitle() + " to cart!\nGo to Cart now?", "Success", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                mainFrame.navigateTo("CART");
            }
        });
        addBtn.setForeground(Color.WHITE);
        addBtn.setBackground(Theme.GREEN);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(addBtn);

        return item;
    }

    private ImageIcon loadBookImage(String path) {
        try {
            URL imageUrl = getClass().getResource(path);
            if (imageUrl != null) {
                return new ImageIcon(imageUrl);
            }
        } catch (Exception e) {
            System.out.println("Image not found: " + path);
        }
        return null;
    }
}