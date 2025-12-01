package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.URL;
import java.util.List;

public class WishlistPanel extends JPanel {
    private BookController bookController;

    public WishlistPanel(MainFrame frame) {
        bookController = frame.getBookController();
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        JLabel title = new JLabel("♡ Wishlist", SwingConstants.LEFT);
        title.setFont(new Font("Serif", Font.BOLD, 20));
        title.setForeground(Color.BLACK);
        header.add(title, BorderLayout.WEST);

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightIcons.setBackground(Color.WHITE);
        JButton searchBtn = Theme.createIconButton("🔍", e -> frame.navigateTo("SEARCH"));
        searchBtn.setForeground(Color.BLACK);
        searchBtn.setBackground(Color.WHITE);
        JButton cartBtn = Theme.createIconButton("🛒", e -> frame.navigateTo("CART"));
        cartBtn.setForeground(Color.BLACK);
        cartBtn.setBackground(Color.WHITE);
        rightIcons.add(searchBtn);
        rightIcons.add(cartBtn);
        header.add(rightIcons, BorderLayout.EAST);
        add(header, BorderLayout.NORTH);

        // Books container
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.X_AXIS));
        booksContainer.setBackground(Color.WHITE);
        booksContainer.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        List<Book> allBooks = bookController.getAllBooks();
        // Demo books; replace with actual wishlist logic
        List<Book> wishlistBooks = allBooks.subList(0, Math.min(3, allBooks.size()));
        for (Book b : wishlistBooks) {
            RoundedPanel bookPanel = createBookCard(b, frame);
            booksContainer.add(bookPanel);
            booksContainer.add(Box.createHorizontalStrut(20));
        }

        JScrollPane scroll = new JScrollPane(booksContainer, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        add(scroll, BorderLayout.CENTER);

        // Footer
        RoundedPanel footer = new RoundedPanel(8);
        footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
        footer.setBackground(Color.WHITE);
        JButton backBtn = Theme.createThemedButton("← Back to Home", 14, e -> frame.navigateTo("HOME"));
        backBtn.setForeground(Color.BLACK);
        backBtn.setBackground(Color.WHITE);
        backBtn.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        footer.add(backBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private RoundedPanel createBookCard(Book b, MainFrame frame) {
        RoundedPanel bookPanel = new RoundedPanel(12);
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        bookPanel.setPreferredSize(new Dimension(200, 280));
        bookPanel.setMaximumSize(new Dimension(200, 280));
        bookPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        JLabel imgLabel = Theme.createBookImageLabel(b.getImagePath(), 150, 150);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(Box.createVerticalStrut(20));
        bookPanel.add(imgLabel);
        bookPanel.add(Box.createVerticalStrut(20));

        JLabel nameLabel = new JLabel(b.getTitle(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Serif", Font.BOLD, 13));
        nameLabel.setForeground(Color.BLACK);
        bookPanel.add(nameLabel);

        JLabel authorLabel = new JLabel("Author: " + b.getAuthor(), SwingConstants.CENTER);
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 12));
        authorLabel.setForeground(Color.BLACK);
        bookPanel.add(authorLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD", SwingConstants.CENTER);
        priceLabel.setFont(new Font("Serif", Font.BOLD, 13));
        priceLabel.setForeground(Theme.GREEN);
        bookPanel.add(priceLabel);

        return bookPanel;
    }
}