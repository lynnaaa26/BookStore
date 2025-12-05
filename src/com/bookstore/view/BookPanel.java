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
import java.util.stream.Collectors;

public class BookPanel extends JPanel {

    private BookController bookController;

    // *** AJOUT : livre actuellement affiché ***
    private Book currentBook;

    // *** AJOUT : panel principal que l'on reconstruit ***
    private JPanel mainBookPanelReference;

    public BookPanel(MainFrame frame) {
        bookController = frame.getBookController();

        // *** AJOUT : valeur par défaut TEMPORAIRE pour éviter null au lancement ***
        currentBook = bookController.getAllBooks().get(0);

        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Header
        RoundedPanel header = createHeader(frame);
        add(header, BorderLayout.NORTH);

        // *** AJOUT : on garde une référence du panel principal ***
        mainBookPanelReference = new JPanel(new BorderLayout());
        mainBookPanelReference.setOpaque(false);

        // Construction initiale
        rebuildMainContent(frame);

        add(mainBookPanelReference, BorderLayout.CENTER);
    }

    // *** AJOUT : appelée par MainFrame quand on clique un livre ***
    public void setBook(Book book) {
        this.currentBook = book;

        // Reconstruit dynamiquement l'affichage
        rebuildMainContent(SwingUtilities.getWindowAncestor(this) instanceof MainFrame ?
                (MainFrame) SwingUtilities.getWindowAncestor(this) : null);
    }

    // *** AJOUT : reconstruit le contenu du panel principal ***
    private void rebuildMainContent(MainFrame frame) {

        mainBookPanelReference.removeAll(); // efface l’ancien contenu

        RoundedPanel content = new RoundedPanel(12);
        content.setLayout(new BorderLayout(10, 5));
        content.setBackground(Color.WHITE);
        content.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Partie principale (livre affiché)
        RoundedPanel mainBookSection = new RoundedPanel(12);
        mainBookSection.setLayout(new BorderLayout(20, 0));
        mainBookSection.setBackground(Color.WHITE);
        mainBookSection.add(createMainBookContent(frame), BorderLayout.CENTER);
        content.add(mainBookSection, BorderLayout.NORTH);

        // Suggestions
        RoundedPanel moreBooksSection = new RoundedPanel(12);
        moreBooksSection.setBackground(Color.WHITE);
        moreBooksSection.setLayout(new BorderLayout());
        moreBooksSection.setBorder(new EmptyBorder(80, 0, 0, 0));
        moreBooksSection.add(createMoreBooksContent(frame), BorderLayout.CENTER);
        content.add(moreBooksSection, BorderLayout.CENTER);

        mainBookPanelReference.add(content, BorderLayout.CENTER);

        // Rafraîchir l'affichage
        revalidate();
        repaint();
    }

    private RoundedPanel createHeader(MainFrame frame) {
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(new EmptyBorder(5, 15, 5, 15));

        JButton backButton = new JButton("←");
        backButton.setPreferredSize(new Dimension(60, 50));
        backButton.setFont(new Font("SansSerif", Font.BOLD, 24));
        backButton.setFocusPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setForeground(Color.BLACK);
        backButton.addActionListener(e -> frame.navigateTo("HOME"));
        header.add(backButton, BorderLayout.WEST);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        logoPanel.setBackground(Color.WHITE);
        JLabel logoIcon = new JLabel("\uD83D\uDCD6");
        logoIcon.setFont(new Font("SansSerif", Font.PLAIN, 32));
        JLabel logoText = new JLabel("Story time ★");
        logoText.setFont(new Font("SansSerif", Font.BOLD, 28));
        logoText.setForeground(Color.BLACK);
        logoPanel.add(logoIcon);
        logoPanel.add(logoText);
        header.add(logoPanel, BorderLayout.CENTER);

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        rightIcons.setBackground(Color.WHITE);
        JButton wishlistIcon = new JButton("♥");
        wishlistIcon.setPreferredSize(new Dimension(50, 50));
        wishlistIcon.setFont(new Font("SansSerif", Font.BOLD, 24));
        wishlistIcon.setFocusPainted(false);
        wishlistIcon.setContentAreaFilled(false);
        wishlistIcon.setForeground(Color.BLACK);
        wishlistIcon.addActionListener(e -> frame.navigateTo("WISHLIST"));

        JButton cartIcon = new JButton("🛒");
        cartIcon.setPreferredSize(new Dimension(50, 50));
        cartIcon.setFont(new Font("SansSerif", Font.BOLD, 24));
        cartIcon.setFocusPainted(false);
        cartIcon.setContentAreaFilled(false);
        cartIcon.setForeground(Color.BLACK);
        cartIcon.addActionListener(e -> frame.navigateTo("CART"));

        rightIcons.add(wishlistIcon);
        rightIcons.add(cartIcon);
        header.add(rightIcons, BorderLayout.EAST);

        return header;
    }

    // *** MODIFIÉ : maintenant utilise currentBook ***
    private JPanel createMainBookContent(MainFrame frame) {

        Book mainBook = currentBook;  // *** AJOUT CRUCIAL ***

        JPanel mainBookPanel = new JPanel(new BorderLayout(20, 0));
        mainBookPanel.setBackground(Color.WHITE);

        // Affichage image
        RoundedPanel imagePanel = new RoundedPanel(12);
        Dimension scaledSize = new Dimension(220, 310);
        imagePanel.setPreferredSize(scaledSize);
        imagePanel.setMinimumSize(new Dimension(180, 280));
        imagePanel.setMaximumSize(scaledSize);
        imagePanel.setLayout(new BorderLayout());

        JLabel mainImageLabel = Theme.createBookImageLabel(mainBook.getImagePath(), scaledSize.width, scaledSize.height);
        mainImageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        imagePanel.add(mainImageLabel, BorderLayout.CENTER);
        mainBookPanel.add(imagePanel, BorderLayout.WEST);

        // Affichage infos
        RoundedPanel infoPanel = new RoundedPanel(12);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JLabel titleLabel = new JLabel(mainBook.getTitle());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
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

        JTextArea desc = new JTextArea(mainBook.getDescription() != null ?
                mainBook.getDescription() :
                "No description available.");
        desc.setLineWrap(true);
        desc.setWrapStyleWord(true);
        desc.setEditable(false);
        desc.setOpaque(false);
        desc.setFont(new Font("SansSerif", Font.BOLD, 13));
        desc.setForeground(Color.BLACK);
        desc.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        desc.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(desc);

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

 // --- Nouvelle section : More Books (horizontal scroll + titre) ---
    private JPanel createMoreBooksContent(MainFrame frame) {
        List<Book> allBooks = bookController.getAllBooks();

        // Filtrer pour ne pas afficher le livre actuel
        List<Book> suggestions = allBooks.stream()
                .filter(b -> b.getId() != currentBook.getId())
                .limit(10) // on prend 10 livres max
                .collect(Collectors.toList());

        // Panel principal vertical pour titre + scroll
        JPanel moreBooksPanel = new JPanel();
        moreBooksPanel.setLayout(new BorderLayout());
        moreBooksPanel.setBackground(Color.WHITE);

        // Titre
        JLabel titleLabel = new JLabel("More Books");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(new EmptyBorder(0, 10, 10, 0));
        moreBooksPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel horizontal pour les suggestions
        JPanel suggestionsPanel = new JPanel();
        suggestionsPanel.setLayout(new BoxLayout(suggestionsPanel, BoxLayout.X_AXIS));
        suggestionsPanel.setBackground(Color.WHITE);

        for (Book b : suggestions) {
            JPanel bookCard = createBookCardLarge(b, frame);
            suggestionsPanel.add(bookCard);
            suggestionsPanel.add(Box.createHorizontalStrut(15)); // espacement entre les livres
        }

        // Scroll pane horizontal
        JScrollPane scrollPane = new JScrollPane(suggestionsPanel,
                JScrollPane.VERTICAL_SCROLLBAR_NEVER,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);
        scrollPane.setBackground(Color.WHITE);
        scrollPane.getViewport().setBackground(Color.WHITE);

        moreBooksPanel.add(scrollPane, BorderLayout.CENTER);
        return moreBooksPanel;
    }

    // Version "grande" des cartes pour More Books
    private RoundedPanel createBookCardLarge(Book b, MainFrame frame) {
        RoundedPanel bookPanel = new RoundedPanel(12);
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(8, 8, 8, 8)
        ));
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.setMaximumSize(new Dimension(140, 240)); // taille plus grande

        JLabel imageLabel = Theme.createBookImageLabel(b.getImagePath(), 120, 180);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(imageLabel);

        JLabel titleLabel = new JLabel(b.getTitle());
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setMaximumSize(new Dimension(120, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bookPanel.add(Box.createVerticalStrut(5));
        bookPanel.add(titleLabel);

        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        priceLabel.setForeground(Theme.GREEN);
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(priceLabel);

        // Quand on clique sur une suggestion, on met à jour le BookPanel
        bookPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.showBookDetails(b);
            }
        });

        return bookPanel;
    }
}