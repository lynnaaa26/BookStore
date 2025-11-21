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
        setBackground(new Color(245, 245, 220)); // Beige background
        setLayout(new BorderLayout(20, 20));
        // add empty borders
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        //------------------- top panel--------------------------
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false); // Transparent to show beige bg
        JLabel header = new JLabel(" 📖 Story time ★", SwingConstants.LEFT);
        header.setFont(new Font("Serif", Font.BOLD, 26)); // font style and size
        header.setForeground(new Color(101, 67, 33)); // Saddle brown
        topPanel.add(header, BorderLayout.WEST); // place at top little to the left
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        rightButtonsPanel.setOpaque(false);
        JButton search = new JButton("🔍");
        search.setFont(new Font("Serif", Font.PLAIN, 16));
        search.setBackground(new Color(220, 220, 200)); // Light beige
        search.setForeground(new Color(101, 67, 33)); // Saddle brown text
        search.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        search.addActionListener(e -> mainFrame.navigateTo("SEARCH"));
        rightButtonsPanel.add(search);
        JButton wishlistButton = new JButton("❤️");
        wishlistButton.setFont(new Font("Serif", Font.PLAIN, 16));
        wishlistButton.setBackground(new Color(220, 220, 200)); // Light beige
        wishlistButton.setForeground(new Color(101, 67, 33)); // Saddle brown text
        wishlistButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        wishlistButton.addActionListener(e -> mainFrame.navigateTo("WISHLIST"));
        rightButtonsPanel.add(wishlistButton);
        JButton cartButton = new JButton("🛒");
        cartButton.setFont(new Font("Serif", Font.PLAIN, 16));
        cartButton.setBackground(new Color(220, 220, 200)); // Light beige
        cartButton.setForeground(new Color(101, 67, 33)); // Saddle brown text
        cartButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        cartButton.addActionListener(e -> mainFrame.navigateTo("CART"));
        rightButtonsPanel.add(cartButton);
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        //------------------ main content ---------------------
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0)); // left and right
        contentPanel.setBackground(new Color(245, 245, 220)); // Beige
        //-----------------left side ---------------------
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); // vertical
        leftPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        leftPanel.setBackground(new Color(245, 245, 220)); // Beige
        JLabel subHeader = new JLabel("Find your next favorite story !");
        subHeader.setFont(new Font("Serif", Font.BOLD, 16));
        subHeader.setForeground(new Color(101, 67, 33)); // Saddle brown
        subHeader.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(subHeader);
        leftPanel.add(Box.createVerticalStrut(10)); //add vertical spacing to a container [1]
        // Fetch books dynamically from controller
        List<Book> allBooks = bookController.getAllBooks();
        // For demo, use first 4 books (add categories logic later)
        List<Book> featuredBooks = allBooks.subList(0, Math.min(4, allBooks.size()));
        //container to group books by rows
        JPanel booksContainer = new JPanel();
        booksContainer.setLayout(new BoxLayout(booksContainer, BoxLayout.Y_AXIS)); // vertical
        booksContainer.setBackground(new Color(245, 245, 220)); // Beige
        // loop through books two by two so we can get 2 per line
        for (int i = 0; i < featuredBooks.size(); i += 2) {
            JPanel rowPanel = new JPanel(new GridLayout(1, 2, 20, 20)); // 2 books per row
            rowPanel.setBackground(new Color(245, 245, 220)); // Beige
            for (int j = i; j < i + 2 && j < featuredBooks.size(); j++) { // i+2 so it adds 2 books with the condition to make sure we don't get through the last book if it's odd
                Book b = featuredBooks.get(j);
                String category = "Fiction"; // Hardcoded for demo; fetch from model later
                JPanel bookPanel = createBookPanel(b, category, mainFrame);
                rowPanel.add(bookPanel);
            }
            // adding each row to the main container
            booksContainer.add(rowPanel);
            booksContainer.add(Box.createVerticalStrut(10)); // spacing between rows
        }
        // add the grouped container to the left panel
        leftPanel.add(booksContainer);
        //---------------right side------------------
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(new Color(245, 245, 220)); // Beige
        rightPanel.setBorder(BorderFactory.createTitledBorder(null, "Best sellers of the Month", 0, 0, new Font("Serif", Font.BOLD, 14), new Color(101, 67, 33))); // Themed border
        // Fetch bestsellers (e.g., first 3)
        List<Book> bestsellers = allBooks.subList(0, Math.min(3, allBooks.size()));
        JLabel bestSellersTitle = new JLabel("Best Sellers of the Month!");
        bestSellersTitle.setFont(new Font("Serif", Font.BOLD, 16));
        bestSellersTitle.setForeground(new Color(101, 67, 33)); // Saddle brown
        bestSellersTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        rightPanel.add(bestSellersTitle);
        rightPanel.add(Box.createVerticalStrut(10));
        // Horizontal layout for three books
        JPanel booksRow = new JPanel(new GridLayout(1, 3, 10, 0));
        booksRow.setBackground(new Color(245, 245, 220)); // Beige
        for (Book b : bestsellers) {
            JPanel item = createBestsellerItem(b, mainFrame);
            booksRow.add(item);
        }
        rightPanel.add(booksRow);
        //Add both sides to main content
        contentPanel.add(leftPanel);
        contentPanel.add(rightPanel);
        add(contentPanel, BorderLayout.CENTER);
    }

    private JPanel createBookPanel(Book b, String category, MainFrame mainFrame) {
        JPanel bookPanel = new JPanel(); // we create a new jpanel that will represent one book
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS)); // the content of the book will vertical
        bookPanel.setBorder(BorderFactory.createTitledBorder(null, category, 0, 0, new Font("Serif", Font.BOLD, 12), new Color(101, 67, 33))); // Themed border
        bookPanel.setBackground(Color.WHITE);
        // Make the entire bookPanel clickable to navigate to BookPanel
        bookPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainFrame.navigateTo("BOOKS");
            }
        });
        bookPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // image placeholder
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(100, 140));
        imageLabel.setMaximumSize(new Dimension(100, 140));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = loadBookImage(b.getImagePath());
        if (icon != null && icon.getIconWidth() > 0) {
            Image scaledImage = icon.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
            imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        } else {
            imageLabel.setText("No Image");
            imageLabel.setBackground(new Color(245, 245, 220)); // Beige fallback
            imageLabel.setOpaque(true);
            imageLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        }
        bookPanel.add(imageLabel);
        // book info
        JLabel title = new JLabel(b.getTitle());
        title.setFont(new Font("Serif", Font.BOLD, 14));
        title.setForeground(new Color(101, 67, 33)); // Saddle brown
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(title);
        JLabel price = new JLabel(b.getPrice() + " DZD");
        price.setFont(new Font("Serif", Font.PLAIN, 12));
        price.setForeground(new Color(34, 139, 34)); // Green for price
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(price);
        // "Add to Cart" button with new listener
        JButton addBtn = new JButton("Add to Cart");
        addBtn.setFont(new Font("Serif", Font.BOLD, 11));
        addBtn.setBackground(new Color(220, 220, 200)); // Light beige
        addBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        addBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(e -> {
            System.out.println("Adding book to cart: " + b.getTitle() + " (ID: " + b.getId() + ")"); // Debug log
            mainFrame.getCartController().addToCart(b, 1);
            System.out.println("Cart after add: " + mainFrame.getCart().getBookQuantities()); // Debug log
            // Confirm and navigate to Cart
            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Added " + b.getTitle() + " to cart!\nGo to Cart now?", 
                "Success", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                mainFrame.navigateTo("CART"); // Recreates CartPanel, loads items
            }
        });
        bookPanel.add(addBtn);
        return bookPanel;
    }

    private JPanel createBestsellerItem(Book b, MainFrame mainFrame) {
        JPanel item = new JPanel();
        item.setLayout(new BoxLayout(item, BoxLayout.Y_AXIS));
        item.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 200), 1)); // Light beige border
        item.setBackground(Color.WHITE);
        item.setPreferredSize(new Dimension(120, 220));
        item.setMaximumSize(new Dimension(120, 220));
        item.setAlignmentX(Component.CENTER_ALIGNMENT);
        // Make item clickable
        item.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainFrame.navigateTo("BOOKS");
            }
        });
        item.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        // image placeholder
        JLabel imagePlaceholder = new JLabel();
        imagePlaceholder.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePlaceholder.setPreferredSize(new Dimension(100, 140));
        imagePlaceholder.setMaximumSize(new Dimension(100, 140));
        imagePlaceholder.setHorizontalAlignment(SwingConstants.CENTER);
        // Try to load image for bestsellers
        ImageIcon icon = loadBookImage(b.getImagePath());
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
        item.add(imagePlaceholder);
        item.add(Box.createVerticalStrut(5));
        JLabel titleLabel = new JLabel(b.getTitle());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 12));
        titleLabel.setForeground(new Color(101, 67, 33)); // Saddle brown
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(titleLabel);
        JLabel authorLabel = new JLabel("Author: " + b.getAuthor());
        authorLabel.setFont(new Font("Serif", Font.ITALIC, 11));
        authorLabel.setForeground(new Color(139, 69, 19)); // Darker brown
        authorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(authorLabel);
        JLabel priceLabel = new JLabel(b.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.PLAIN, 11));
        priceLabel.setForeground(new Color(34, 139, 34)); // Green
        priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(priceLabel);
        JLabel ratingLabel = new JLabel("★★★★★");
        ratingLabel.setFont(new Font("Serif", Font.PLAIN, 11));
        ratingLabel.setForeground(new Color(255, 215, 0)); // Gold stars
        ratingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        item.add(ratingLabel);
        item.add(Box.createVerticalStrut(5));
        JButton addBtn = new JButton("Add");
        addBtn.setFont(new Font("Serif", Font.BOLD, 10));
        addBtn.setBackground(new Color(220, 220, 200)); // Light beige
        addBtn.setForeground(new Color(101, 67, 33)); // Saddle brown text
        addBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1)); // Beige border
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(e -> {
            System.out.println("Adding book to cart: " + b.getTitle() + " (ID: " + b.getId() + ")"); // Debug log
            mainFrame.getCartController().addToCart(b, 1);
            System.out.println("Cart after add: " + mainFrame.getCart().getBookQuantities()); // Debug log
            // Confirm and navigate to Cart
            int choice = JOptionPane.showConfirmDialog(
                this, 
                "Added " + b.getTitle() + " to cart!\nGo to Cart now?", 
                "Success", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.INFORMATION_MESSAGE
            );
            if (choice == JOptionPane.YES_OPTION) {
                mainFrame.navigateTo("CART"); // Recreates CartPanel, loads items
            }
        });
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