package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class HomePanel extends JPanel {
    private BookController bookController;

    public HomePanel(MainFrame mainFrame) {
        bookController = mainFrame.getBookController();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- HEADER ---
        JPanel header = createHeader(mainFrame);
        add(header, BorderLayout.NORTH);

        // --- MAIN CONTAINER (vertical scroll) ---
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBackground(Color.WHITE);
        container.setBorder(new EmptyBorder(10, 10, 10, 10));

        List<Book> allBooks = bookController.getAllBooks();

        // --- Best Sellers Section ---
        container.add(createSectionTitle("★ Best Sellers of the Month ★"));
        List<Book> bestsellers = allBooks.subList(0, Math.min(4, allBooks.size()));
        container.add(createBooksRow(bestsellers, mainFrame));

        // --- Genre Sections (classified based on books) ---
        String[][] genres = {
            {"Classics", "0", "6"},    // The Yellow Wallpaper, To Kill a Mockingbird, We Have Always Lived in the Castle, Mrs Dalloway, Sense and Sensibility, Moby Dick
            {"Fiction", "6", "10"},    // Cloud Atlas, The Girl on the Train, Harry Potter Pack, The Poppy War
            {"Horror", "4", "6"},      // Frankenstein, Fahrenheit 451
            {"Romance", "13", "14"}    // Wuthering Heights
            // Add more sections if needed
        };

        for (String[] genre : genres) {
            int start = Integer.parseInt(genre[1]);
            int end = Integer.parseInt(genre[2]);
            if (start >= allBooks.size()) continue;
            end = Math.min(end, allBooks.size());
            List<Book> genreBooks = allBooks.subList(start, end);
            if (!genreBooks.isEmpty()) {
                container.add(createSectionTitle(genre[0]));
                container.add(createBooksRow(genreBooks, mainFrame));
            }
        }

        container.add(Box.createVerticalGlue());

        JScrollPane scrollPane = new JScrollPane(container);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);

        add(scrollPane, BorderLayout.CENTER);
    }

    private JPanel createHeader(MainFrame mainFrame) {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Title
        JLabel headerLabel = new JLabel("📖 Story time ★");
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        headerLabel.setForeground(Color.BLACK);
        topPanel.add(headerLabel, BorderLayout.WEST);

        // Icons
        JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightButtonsPanel.setOpaque(false);

        JButton search = Theme.createThemedButton("🔍", 18, e -> mainFrame.navigateTo("SEARCH"));
        search.setForeground(Color.BLACK);
        search.setBackground(Color.WHITE);

        JButton wishlist = Theme.createThemedButton("❤️", 18, e -> mainFrame.navigateTo("WISHLIST"));
        wishlist.setForeground(Color.BLACK);
        wishlist.setBackground(Color.WHITE);

        JButton cart = Theme.createThemedButton("🛒", 18, e -> mainFrame.navigateTo("CART"));
        cart.setForeground(Color.BLACK);
        cart.setBackground(Color.WHITE);

        rightButtonsPanel.add(search);
        rightButtonsPanel.add(wishlist);
        rightButtonsPanel.add(cart);
        topPanel.add(rightButtonsPanel, BorderLayout.EAST);

        return topPanel;
    }

    private JLabel createSectionTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(new Font("SansSerif", Font.BOLD, 22));
        label.setForeground(Color.BLACK);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(new EmptyBorder(15, 5, 10, 0));
        return label;
    }

    private JPanel createBooksRow(List<Book> books, MainFrame mainFrame) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        row.setBackground(Color.WHITE);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        for (Book b : books) {
            row.add(createBookPanel(b, mainFrame));
        }
        return row;
    }

    private JPanel createBookPanel(Book b, MainFrame mainFrame) {
        RoundedPanel bookPanel = new RoundedPanel(12);
        bookPanel.setLayout(new BoxLayout(bookPanel, BoxLayout.Y_AXIS));
        bookPanel.setBackground(Color.WHITE);
        bookPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        bookPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bookPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                mainFrame.navigateTo("BOOKS");
            }
        });

        JLabel imageLabel = Theme.createBookImageLabel(b.getImagePath(), 140, 200);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(imageLabel);

        bookPanel.add(Box.createVerticalStrut(8));

        JLabel title = new JLabel(b.getTitle());
        title.setFont(new Font("SansSerif", Font.BOLD, 14));
        title.setForeground(Color.BLACK);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(title);

        JLabel price = new JLabel(b.getPrice() + " DZD");
        price.setFont(new Font("SansSerif", Font.PLAIN, 13));
        price.setForeground(Theme.GREEN);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookPanel.add(price);

        JButton addBtn = new JButton("Add to Cart");
        addBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        addBtn.setBackground(Color.LIGHT_GRAY);
        addBtn.setForeground(Color.BLACK);
        addBtn.setFocusPainted(false);
        addBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        addBtn.addActionListener(e -> {
            mainFrame.getCartController().addToCart(b, 1);
            JOptionPane.showMessageDialog(this, "Added " + b.getTitle() + " to cart!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
        bookPanel.add(addBtn);

        return bookPanel;
    }
}
