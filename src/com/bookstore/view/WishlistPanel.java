package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import com.bookController.WishlistController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class WishlistPanel extends JPanel {

    private BookController bookController;
    private WishlistController wishlistController;

    public WishlistPanel(MainFrame frame) {
        bookController = frame.getBookController();
        wishlistController = frame.getWishlistController(); // Get the WishlistController from MainFrame

        setLayout(new BorderLayout(30, 30));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- HEADER ---
        RoundedPanel header = new RoundedPanel(12);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);

        JLabel title = new JLabel("♡ Wishlist", SwingConstants.LEFT);
        title.setFont(new Font("Serif", Font.BOLD, 36));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.WEST);

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightIcons.setBackground(Color.WHITE);

        JButton searchBtn = Theme.createIconButton("🔍", e -> frame.navigateTo("SEARCH"));
        searchBtn.setFont(new Font("Serif", Font.PLAIN, 28));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.setBackground(Color.WHITE);
        searchBtn.setPreferredSize(new Dimension(50, 50));

        JButton cartBtn = Theme.createIconButton("🛒", e -> frame.navigateTo("CART"));
        cartBtn.setFont(new Font("Serif", Font.PLAIN, 28));
        cartBtn.setForeground(Color.BLACK);
        cartBtn.setBackground(Color.WHITE);
        cartBtn.setPreferredSize(new Dimension(50, 50));

        rightIcons.add(searchBtn);
        rightIcons.add(cartBtn);
        header.add(rightIcons, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // --- Initial content ---
        rebuildWishlistContent(frame);
    }

    // Rebuilds the wishlist dynamically
    public void rebuildWishlistContent(MainFrame frame) {
        // Remove old content except header
        Component[] components = getComponents();
        for (Component comp : components) {
            if (comp != null && comp != getComponent(0)) { // 0 = header
                remove(comp);
            }
        }

        // --- BOOKS CONTAINER ---
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.X_AXIS));
        booksContainer.setBackground(Color.WHITE);
        booksContainer.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        booksContainer.add(Box.createHorizontalGlue());

        List<Book> wishlistBooks = wishlistController.getAllBooks(); // Get books from WishlistController
        for (Book b : wishlistBooks) {
            RoundedPanel bookPanel = createBookCard(b, frame);
            booksContainer.add(bookPanel);
            booksContainer.add(Box.createHorizontalStrut(30));
        }
        booksContainer.add(Box.createHorizontalGlue());

        JScrollPane scroll = new JScrollPane(booksContainer,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);

        add(scroll, BorderLayout.CENTER);

        // --- FOOTER ---
        RoundedPanel footer = new RoundedPanel(12);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);

        JButton backBtn = Theme.createThemedButton("← Back to Home", 16, e -> frame.navigateTo("HOME"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        backBtn.setPreferredSize(new Dimension(180, 50));

        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    // Creates a book card for the wishlist
    private RoundedPanel createBookCard(Book b, MainFrame frame) {
        RoundedPanel bookPanel = new RoundedPanel(16);
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        bookPanel.setPreferredSize(new Dimension(220, 350));
        bookPanel.setMaximumSize(new Dimension(220, 350));
        bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.showBookDetails(b); // Opens book details
            }
        });

        JLabel imgLabel = Theme.createBookImageLabel(b.getImagePath(), 180, 180);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        bookPanel.add(Box.createVerticalStrut(25));
        bookPanel.add(imgLabel);
        bookPanel.add(Box.createVerticalStrut(20));

        JLabel nameLabel = new JLabel(b.getTitle(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 18));
        nameLabel.setForeground(Color.BLACK);
        bookPanel.add(nameLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor(), SwingConstants.CENTER);
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        authorLabel.setForeground(Color.BLACK);
        bookPanel.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD", SwingConstants.CENTER);
        priceLabel.setFont(new Font("Serif", Font.BOLD, 16));
        priceLabel.setForeground(Theme.GREEN);
        bookPanel.add(priceLabel);

        return bookPanel;
    }
}
