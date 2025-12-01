package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import com.bookController.CartController;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.util.List;
import java.util.Optional;

public class BookPanel extends JPanel {
    private BookController bookController;

    public BookPanel(MainFrame frame) {
        bookController = frame.getBookController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Header
        RoundedPanel header = createHeader(frame);
        add(header, BorderLayout.NORTH);

        // Content
        RoundedPanel content = new RoundedPanel(12);
        content.setLayout(new BorderLayout(10, 5));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Main book section
        RoundedPanel mainBookSection = new RoundedPanel(12);
        mainBookSection.setLayout(new BorderLayout(15, 0));
        mainBookSection.setBackground(Color.WHITE);
        mainBookSection.add(createMainBookContent(frame), BorderLayout.CENTER);
        content.add(mainBookSection, BorderLayout.NORTH);

        // More books section
        RoundedPanel moreBooksSection = new RoundedPanel(12);
        moreBooksSection.setBackground(Color.WHITE);
        moreBooksSection.setLayout(new BorderLayout());
        moreBooksSection.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        moreBooksSection.add(createMoreBooksContent(frame), BorderLayout.CENTER);
        content.add(moreBooksSection, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);
    }

    private RoundedPanel createHeader(MainFrame frame) {
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

        // Back button
        JButton backButton = Theme.createIconButton("← Back", e -> frame.navigateTo("HOME"));
        header.add(backButton, BorderLayout.WEST);
        backButton.setForeground(Color.BLACK);

        // Logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        logoPanel.setBackground(Color.WHITE);
        JLabel logoIcon = new JLabel("\uD83D\uDCD6");
        logoIcon.setFont(new Font("Serif", Font.PLAIN, 28));
        logoIcon.setForeground(Color.BLACK);
        JLabel logoText = new JLabel("Story time ★");
        logoText.setFont(new Font("Serif", Font.BOLD, 26));
        logoText.setForeground(Color.BLACK);
        logoPanel.add(logoIcon);
        logoPanel.add(logoText);
        header.add(logoPanel, BorderLayout.CENTER);

        // Right icons
        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightIcons.setBackground(Color.WHITE);
        JButton wishlistIcon = Theme.createIconButton("❤️", e -> frame.navigateTo("WISHLIST"));
        wishlistIcon.setForeground(Color.BLACK);
        JButton cartIcon = Theme.createIconButton("🛒", e -> frame.navigateTo("CART"));
        cartIcon.setForeground(Color.BLACK);
        rightIcons.add(wishlistIcon);
        rightIcons.add(cartIcon);
        header.add(rightIcons, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainBookContent(MainFrame frame) {
        JPanel mainBookPanel = new JPanel(new BorderLayout(15, 0));
        mainBookPanel.setBackground(Color.WHITE);

        Optional<Book> optionalMainBook = bookController.getBookById(1);
        Book mainBook = optionalMainBook.orElseGet(() -> new Book(
                1, "The Yellow Wallpaper", "Charlotte Perkins Gilman", 1000, "/imagess/yellow.jpg"));

        // Image panel
        RoundedPanel imagePanel = new RoundedPanel(8);
        imagePanel.setPreferredSize(new Dimension(200, 280));
        imagePanel.setLayout(new BorderLayout());
        JLabel mainImageLabel = Theme.createBookImageLabel(mainBook.getImagePath(), 200, 280);
        mainImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainImageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Optional zoom or details
            }
        });
        imagePanel.add(mainImageLabel, BorderLayout.CENTER);
        mainBookPanel.add(imagePanel, BorderLayout.WEST);

        // Info panel
        RoundedPanel infoPanel = new RoundedPanel(8);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel(mainBook.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLACK);
        infoPanel.add(titleLabel);

        JLabel priceLabel = new JLabel(mainBook.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 16));
        priceLabel.setForeground(Theme.GREEN);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);

        JTextArea desc = new JTextArea(
                "“The Yellow Wallpaper” is written in journal entries by a woman who is on vacation with her husband to a big house after giving birth to their daughter. The woman is suffering from postpartum hysteria and secretly wonders if her husband is why she is not getting better."
        );
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setFont(new Font("Serif", Font.PLAIN, 14));
        desc.setForeground(Color.BLACK);
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        infoPanel.add(Box.createVerticalStrut(8));
        infoPanel.add(desc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        buttonPanel.setBackground(Color.WHITE);

        JButton addToCart = new JButton("ADD TO CART");
        addToCart.setBackground(Color.LIGHT_GRAY);
        addToCart.setForeground(Color.BLACK);
        addToCart.setFocusPainted(false);
        addToCart.setFont(new Font("Serif", Font.BOLD, 12));
        addToCart.addActionListener(e -> {
            frame.getCartController().addToCart(mainBook, 1);
            frame.refreshCartPanel();
            int choice = JOptionPane.showConfirmDialog(this,
                    "Added " + mainBook.getTitle() + " to cart!\nGo to Cart now?",
                    "Success", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (choice == JOptionPane.YES_OPTION) {
                frame.navigateTo("CART");
            }
        });

        JButton addToWishlist = new JButton("ADD TO WISHLIST");
        addToWishlist.setBackground(new Color(0, 128, 0)); // green
        addToWishlist.setForeground(Color.WHITE);
        addToWishlist.setFocusPainted(false);
        addToWishlist.setFont(new Font("Serif", Font.BOLD, 12));
        addToWishlist.addActionListener(e -> frame.navigateTo("WISHLIST"));

        buttonPanel.add(addToCart);
        buttonPanel.add(addToWishlist);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(buttonPanel);

        mainBookPanel.add(infoPanel, BorderLayout.CENTER);
        return mainBookPanel;
    }

    private JPanel createMoreBooksContent(MainFrame frame) {
        RoundedPanel moreBooksPanel = new RoundedPanel(12);
        moreBooksPanel.setLayout(new BorderLayout());
        moreBooksPanel.setBackground(Color.WHITE);

        JLabel moreLabel = new JLabel("More Books");
        moreLabel.setFont(new Font("Serif", Font.BOLD, 18));
        moreLabel.setForeground(Color.BLACK);
        moreBooksPanel.add(moreLabel, BorderLayout.NORTH);

        JPanel booksList = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 8));
        booksList.setBackground(Color.WHITE);
        List<Book> allBooks = bookController.getAllBooks();
        List<Book> moreBooks = allBooks.subList(1, Math.min(7, allBooks.size()));
        for (Book b : moreBooks) {
            RoundedPanel bookCard = createBookCard(b, frame);
            booksList.add(bookCard);
        }

        JScrollPane scroll = new JScrollPane(booksList, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        moreBooksPanel.add(scroll, BorderLayout.CENTER);

        return moreBooksPanel;
    }

    private RoundedPanel createBookCard(Book b, MainFrame frame) {
        RoundedPanel card = new RoundedPanel(8);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(140, 220));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        JLabel imgLabel = Theme.createBookImageLabel(b.getImagePath(), 120, 150);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(5));
        card.add(imgLabel);
        card.add(Box.createVerticalStrut(5));

        JLabel label = new JLabel("<html><center>" + b.getTitle().replaceAll("\n", "<br/>") +
                "<br/>" + b.getPrice() + " DZD" + "</center></html>");
        label.setFont(new Font("Serif", Font.BOLD, 12)); // Bold title in card
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(label);

        return card;
    }
}
