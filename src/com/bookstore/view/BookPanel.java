package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import com.bookController.CartController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookPanel extends JPanel {
    private BookController bookController;

    public BookPanel(MainFrame frame) {
        bookController = frame.getBookController();
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Header
        RoundedPanel header = createHeader(frame);
        add(header, BorderLayout.NORTH);

        // Content
        RoundedPanel content = new RoundedPanel(12);
        content.setLayout(new BorderLayout(10, 5));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Main book section (made even a bit smaller for more space below)
        RoundedPanel mainBookSection = new RoundedPanel(12);
        mainBookSection.setLayout(new BorderLayout(20, 0));
        mainBookSection.setBackground(Color.WHITE);
        mainBookSection.add(createMainBookContent(frame), BorderLayout.CENTER);
        content.add(mainBookSection, BorderLayout.NORTH);

        // More books section (now horizontal carousel with arrows for more space/visibility)
        RoundedPanel moreBooksSection = new RoundedPanel(12);
        moreBooksSection.setBackground(Color.WHITE);
        moreBooksSection.setLayout(new BorderLayout());
        moreBooksSection.setBorder(new EmptyBorder(80, 0, 0, 0)); // Even more top space (80px) for suggestions
        moreBooksSection.add(createMoreBooksContent(frame), BorderLayout.CENTER);
        content.add(moreBooksSection, BorderLayout.CENTER);

        add(content, BorderLayout.CENTER);
    }

    private RoundedPanel createHeader(MainFrame frame) {
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(5, 15, 5, 15));

        // Back button (icon-only for modern look)
        JButton backButton = new JButton("←");
        backButton.setPreferredSize(new Dimension(60, 50)); // Bigger size
        backButton.setFont(new Font("SansSerif", Font.BOLD, 24)); // Bigger and bolder font
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> frame.navigateTo("HOME"));
        header.add(backButton, BorderLayout.WEST);

        // Logo
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        logoPanel.setBackground(Color.WHITE);
        JLabel logoIcon = new JLabel("\uD83D\uDCD6");
        logoIcon.setFont(new Font("SansSerif", Font.PLAIN, 32)); // Modern sans-serif for professionalism
        JLabel logoText = new JLabel("Story time ★");
        logoText.setFont(new Font("SansSerif", Font.BOLD, 28));
        logoText.setForeground(Color.BLACK);
        logoPanel.add(logoIcon);
        logoPanel.add(logoText);
        header.add(logoPanel, BorderLayout.CENTER);

        // Right icons (spaced better)
        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightIcons.setBackground(Color.WHITE);
        JButton wishlistIcon = new JButton("♥"); // Use standard heart symbol
        wishlistIcon.setPreferredSize(new Dimension(50, 50)); // Bigger size
        wishlistIcon.setFont(new Font("SansSerif", Font.BOLD, 24)); // Standard font
        wishlistIcon.setFocusPainted(false);
        wishlistIcon.setContentAreaFilled(false);
        wishlistIcon.setForeground(Color.BLACK); // Make it black
        wishlistIcon.addActionListener(e -> frame.navigateTo("WISHLIST"));

        JButton cartIcon = new JButton("🛒");
        cartIcon.setPreferredSize(new Dimension(50, 50)); // Bigger size
        cartIcon.setFont(new Font("SansSerif", Font.BOLD, 24)); // Standard font
        cartIcon.setFocusPainted(false);
        cartIcon.setContentAreaFilled(false);
        cartIcon.setForeground(Color.BLACK); // Make it black
        cartIcon.addActionListener(e -> frame.navigateTo("CART"));

        rightIcons.add(wishlistIcon);
        rightIcons.add(cartIcon);
        header.add(rightIcons, BorderLayout.EAST);

        return header;
    }

    private JPanel createMainBookContent(MainFrame frame) {
        JPanel mainBookPanel = new JPanel(new BorderLayout(20, 0));
        mainBookPanel.setBackground(Color.WHITE);

        Optional<Book> optionalMainBook = bookController.getBookById(1);
        Book mainBook = optionalMainBook.orElseGet(() -> new Book(
                1, "The Yellow Wallpaper", "Charlotte Perkins Gilman", 1000, "/imagess/yellow.jpg"));

        // Image panel (slightly smaller again)
        RoundedPanel imagePanel = new RoundedPanel(12);
        Dimension scaledSize = new Dimension(220, 310); // Further reduced for more space below
        imagePanel.setPreferredSize(scaledSize);
        imagePanel.setMinimumSize(new Dimension(180, 280));
        imagePanel.setMaximumSize(scaledSize);
        imagePanel.setLayout(new BorderLayout());

        JLabel mainImageLabel = Theme.createBookImageLabel(mainBook.getImagePath(), scaledSize.width, scaledSize.height);
        mainImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                // Optional zoom or details
            }
        });
        imagePanel.add(mainImageLabel, BorderLayout.CENTER);
        mainBookPanel.add(imagePanel, BorderLayout.WEST);

        // Info panel (further compacted)
        RoundedPanel infoPanel = new RoundedPanel(12);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(12, 12, 12, 12)); // Reduced padding

        JLabel titleLabel = new JLabel(mainBook.getTitle());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20)); // Smaller font
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(titleLabel);

        JLabel authorLabel = new JLabel("by " + mainBook.getAuthor());
        authorLabel.setFont(new Font("SansSerif", Font.ITALIC, 13));
        authorLabel.setForeground(Color.BLACK);
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(Box.createVerticalStrut(4));
        infoPanel.add(authorLabel);

        JLabel priceLabel = new JLabel(mainBook.getPrice() + " DZD");
        priceLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(Box.createVerticalStrut(6));
        infoPanel.add(priceLabel);

        JTextArea desc = new JTextArea(
                "“The Yellow Wallpaper” is written in journal entries by a woman who is on vacation with her husband to a big house after giving birth to their daughter. The woman is suffering from postpartum hysteria and secretly wonders if her husband is why she is not getting better."
        );
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setFont(new Font("SansSerif", Font.BOLD, 13)); // Made bold
        desc.setForeground(Color.BLACK);
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Increased further for more lines (e.g., 6-7 lines on full width)
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(desc);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton addToCart = new JButton("ADD TO CART");
        addToCart.setFont(new Font("SansSerif", Font.BOLD, 14));
        addToCart.setPreferredSize(new Dimension(140, 40));
        addToCart.setFocusPainted(false);
        addToCart.setBackground(Color.LIGHT_GRAY);
        addToCart.setForeground(Color.BLACK);
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
        addToWishlist.setFont(new Font("SansSerif", Font.BOLD, 14));
        addToWishlist.setPreferredSize(new Dimension(140, 40));
        addToWishlist.setFocusPainted(false);
        addToWishlist.setBackground(new Color(0, 128, 0));
        addToWishlist.setForeground(Color.WHITE);
        addToWishlist.addActionListener(e -> frame.navigateTo("WISHLIST"));

        buttonPanel.add(addToCart);
        buttonPanel.add(addToWishlist);

        infoPanel.add(Box.createVerticalGlue());
        infoPanel.add(buttonPanel);

        mainBookPanel.add(infoPanel, BorderLayout.CENTER);
        return mainBookPanel;
    }

    private JPanel createMoreBooksContent(MainFrame frame) {
        RoundedPanel moreBooksPanel = new RoundedPanel(12);
        moreBooksPanel.setLayout(new BorderLayout());
        moreBooksPanel.setBackground(Color.WHITE);

        // Title
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setBorder(new EmptyBorder(0, 0, 15, 0));

        JLabel moreLabel = new JLabel("More Books");
        moreLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        moreLabel.setForeground(Color.BLACK);
        moreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(moreLabel, BorderLayout.CENTER);

        moreBooksPanel.add(titlePanel, BorderLayout.NORTH);

        // Horizontal books row
        JPanel booksRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 0)); // Horizontal line with gaps
        booksRow.setBackground(Color.WHITE);
        booksRow.setPreferredSize(new Dimension(Short.MAX_VALUE, 300)); // Fixed height for row

        List<Book> allBooks = bookController.getAllBooks();
        List<Book> moreBooks = allBooks.stream().skip(1).limit(15).collect(Collectors.toList()); // More books (15) for carousel

        for (Book b : moreBooks) {
            RoundedPanel bookCard = createBookCard(b, frame);
            booksRow.add(bookCard);
        }

        // Scroll pane for horizontal scrolling
        JScrollPane scroll = new JScrollPane(booksRow, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setBackground(Color.WHITE);
        scroll.setBorder(null);
        scroll.getViewport().setOpaque(false);
        scroll.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0)); // Hide scrollbar, use arrows
        scroll.getHorizontalScrollBar().setVisible(false);

        // Arrow buttons on sides (left at beginning, right at end)
        JButton leftArrow = new JButton("◀"); // Backward arrow at left
        leftArrow.setFont(new Font("SansSerif", Font.BOLD, 20));
        leftArrow.setFocusPainted(false);
        leftArrow.setContentAreaFilled(false);
        leftArrow.setForeground(Color.BLACK);
        leftArrow.setPreferredSize(new Dimension(40, 300)); // Tall to match row height
        leftArrow.setVerticalAlignment(SwingConstants.CENTER);
        leftArrow.addActionListener(e -> {
            JScrollBar bar = scroll.getHorizontalScrollBar();
            int newPos = Math.max(0, bar.getValue() - 200); // Scroll left by ~card width
            bar.setValue(newPos);
        });

        JButton rightArrow = new JButton("▶"); // Forward arrow at right
        rightArrow.setFont(new Font("SansSerif", Font.BOLD, 20));
        rightArrow.setFocusPainted(false);
        rightArrow.setContentAreaFilled(false);
        rightArrow.setForeground(Color.BLACK);
        rightArrow.setPreferredSize(new Dimension(40, 300)); // Tall to match row height
        rightArrow.setVerticalAlignment(SwingConstants.CENTER);
        rightArrow.addActionListener(e -> {
            JScrollBar bar = scroll.getHorizontalScrollBar();
            int newPos = Math.min(bar.getMaximum() - bar.getVisibleAmount(), bar.getValue() + 200); // Scroll right
            bar.setValue(newPos);
        });

        // Main container for scroll + arrows on sides
        JPanel carouselPanel = new JPanel(new BorderLayout());
        carouselPanel.setBackground(Color.WHITE);
        carouselPanel.add(leftArrow, BorderLayout.WEST);
        carouselPanel.add(scroll, BorderLayout.CENTER);
        carouselPanel.add(rightArrow, BorderLayout.EAST);

        moreBooksPanel.add(carouselPanel, BorderLayout.CENTER);

        return moreBooksPanel;
    }

    private RoundedPanel createBookCard(Book b, MainFrame frame) {
        RoundedPanel card = new RoundedPanel(12);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        Dimension scaledCardSize = new Dimension(140, 220); // Adjusted for horizontal row (smaller width, fits more)
        card.setPreferredSize(scaledCardSize);
        card.setMinimumSize(new Dimension(120, 200));
        card.setMaximumSize(scaledCardSize);
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        card.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                frame.navigateTo("BOOKS");
            }
        });

        JLabel imgLabel = Theme.createBookImageLabel(b.getImagePath(), scaledCardSize.width - 20, scaledCardSize.height - 80);
        imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(Box.createVerticalStrut(8));
        card.add(imgLabel);
        card.add(Box.createVerticalStrut(8));

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.setOpaque(false);
        JLabel titleLabel = new JLabel(b.getTitle().replaceAll("\n", " "));
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 10));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);

        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(priceLabel, BorderLayout.SOUTH);
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textPanel.setMaximumSize(new Dimension(scaledCardSize.width - 20, 50));
        card.add(textPanel);

        card.add(Box.createVerticalGlue());

        return card;
    }
}