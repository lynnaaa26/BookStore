package com.bookstore.view;

import java.awt.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.border.*;

import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookController.CartController;
import com.bookController.BookController;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class CartPanel extends JPanel {

    // Model and Controller instances
    private Cart cart; // Shared cart instance
    private CartController cartController;
    private BookController bookController;

    // UI Components for updates
    private JLabel subtotalLabel;
    private JLabel totalLabel;
    private JPanel itemsContainer; // Scrollable container for dynamic product panels
    private List<JLabel> quantityLabels = new ArrayList<>(); // Track per-item quantity labels for updates

    public CartPanel(MainFrame frame) {
        this.cartController = frame.getCartController();
        this.cart = frame.getCart();
        this.bookController = frame.getBookController();

        setBackground(new Color(246, 245, 222));
        setLayout(new BorderLayout()); // BorderLayout for overall structure

        // Title Panel with enhanced styling
        JLabel cartTitle = new JLabel("YOUR SHOPPING CART");
        cartTitle.setFont(new Font("Serif", Font.BOLD, 28));
        cartTitle.setForeground(new Color(100, 70, 30));
        cartTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(246, 245, 222));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        titlePanel.add(cartTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Scrollable container for cart items with enhanced scrolling
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setPreferredSize(new Dimension(900, 250));
        scrollPane.setBorder(BorderFactory.createLoweredBevelBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        add(scrollPane, BorderLayout.CENTER);

        // Totals Panel with better alignment and styling
        JPanel totalsPanel = new JPanel(new GridBagLayout());
        totalsPanel.setBackground(new Color(246, 245, 222));
        totalsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        GridBagConstraints tgbc = new GridBagConstraints();
        tgbc.anchor = GridBagConstraints.EAST;
        tgbc.insets = new Insets(2, 0, 2, 0);

        subtotalLabel = new JLabel(""); // Will be set in updateTotals
        subtotalLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        tgbc.gridx = 0;
        tgbc.gridy = 0;
        totalsPanel.add(subtotalLabel, tgbc);

        JLabel deliveryLabel = new JLabel("Delivery costs : " + Cart.DELIVERY_COST + " DZD"); // Use constant from model
        deliveryLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        tgbc.gridy = 1;
        totalsPanel.add(deliveryLabel, tgbc);

        totalLabel = new JLabel(""); // Will be set in updateTotals
        totalLabel.setFont(new Font("Serif", Font.BOLD, 24));
        totalLabel.setForeground(new Color(0, 150, 0));
        tgbc.gridy = 2;
        totalsPanel.add(totalLabel, tgbc);
        add(totalsPanel, BorderLayout.SOUTH);

     // Buttons Panel with enhanced button styling
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(246, 245, 222));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JButton continueBtn = createStyledButton("CONTINUE SHOPPING", false);
        continueBtn.addActionListener(e -> frame.navigateTo("HOME"));

        JButton confirmBtn = createStyledButton("CONFIRM", true);
        confirmBtn.addActionListener(e -> {
            if (!cart.isEmpty()) {
                frame.navigateTo("ORDERFORM");
            } else {
                JOptionPane.showMessageDialog(this, "Cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(continueBtn);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(confirmBtn);

        // combine totalsPanel and buttonPanel into a single south panel
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BorderLayout());
        southPanel.setBackground(new Color(246, 245, 222));
        southPanel.add(totalsPanel, BorderLayout.NORTH);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(southPanel, BorderLayout.SOUTH);

        // Initial load and update
        loadCartItems();
        updateTotals();
    }

    /**
     * Creates a styled button with consistent theme.
     */
    private JButton createStyledButton(String text, boolean isBold) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Serif", isBold ? Font.BOLD : Font.PLAIN, 14));
        btn.setBackground(new Color(220, 220, 200));
        btn.setForeground(new Color(101, 67, 33));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 180), 1),
            BorderFactory.createEmptyBorder(8, 16, 8, 16)
        ));
        btn.setFocusPainted(false);
        btn.setRolloverEnabled(true);
        btn.setContentAreaFilled(true);
        btn.setOpaque(true);
        return btn;
    }

    /**
     * Loads all cart items dynamically and creates product panels for each.
     */
    private void loadCartItems() {
        itemsContainer.removeAll(); // Clear existing
        quantityLabels.clear(); // Clear tracked labels

        Map<Integer, Integer> quantities = cart.getBookQuantities();
        if (quantities.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            emptyLabel.setForeground(new Color(100, 70, 30));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 40, 0));
            itemsContainer.add(emptyLabel);
        } else {
            // Get all book IDs with qty > 0
            List<Integer> bookIds = quantities.keySet().stream()
                    .filter(id -> quantities.get(id) > 0)
                    .collect(Collectors.toList());

            for (int bookId : bookIds) {
                Book book = bookController.getBookById(bookId).orElse(null);
                if (book != null) {
                    JPanel productPanel = createProductPanel(book, quantities.get(bookId));
                    itemsContainer.add(productPanel);
                    itemsContainer.add(Box.createVerticalStrut(15)); // Enhanced spacing between items
                }
            }
        }
        revalidate();
        repaint();
        SwingUtilities.invokeLater(() -> itemsContainer.getParent().revalidate());
    }

    /**
     * Creates a single product panel for a book in cart using GridBagLayout for better responsiveness.
     */
    private JPanel createProductPanel(Book book, int initialQty) {
        JPanel productPanel = new JPanel(new GridBagLayout());
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(210, 210, 180), 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        productPanel.setMinimumSize(new Dimension(800, 180));
        productPanel.setMaximumSize(new Dimension(900, 180));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.weightx = 0;

        // Image Panel with rounded corners simulation
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(235, 235, 210));
        imagePanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 170)));
        imagePanel.setMinimumSize(new Dimension(120, 140));
        imagePanel.setMaximumSize(new Dimension(120, 140));
        imagePanel.setPreferredSize(new Dimension(120, 140));

        // Load image dynamically from book model
        URL imageUrl = getClass().getResource(book.getImagePath());
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setVerticalAlignment(SwingConstants.CENTER);
            imagePanel.add(imageLabel, BorderLayout.CENTER);
        } else {
            JLabel placeholderLabel = new JLabel("No Image");
            placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            placeholderLabel.setVerticalAlignment(SwingConstants.CENTER);
            placeholderLabel.setFont(new Font("Serif", Font.BOLD, 12));
            placeholderLabel.setForeground(new Color(120, 70, 40));
            imagePanel.add(placeholderLabel, BorderLayout.CENTER);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        productPanel.add(imagePanel, gbc);

        // Title and Author from model
        JLabel bookTitle = new JLabel(book.getTitle());
        bookTitle.setFont(new Font("Serif", Font.BOLD, 20));
        bookTitle.setForeground(new Color(80, 50, 20));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.insets = new Insets(0, 10, 5, 10);
        gbc.weightx = 0.6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        productPanel.add(bookTitle, gbc);

        JLabel bookAuthor = new JLabel("Author: " + book.getAuthor());
        bookAuthor.setFont(new Font("Serif", Font.ITALIC, 16));
        bookAuthor.setForeground(new Color(120, 70, 40));
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10);
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        productPanel.add(bookAuthor, gbc);

     // Quantity Controls Panel
        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        qtyPanel.setBackground(Color.WHITE);
        qtyPanel.setPreferredSize(new Dimension(160, 40));
        qtyPanel.setMinimumSize(new Dimension(160, 40));
        qtyPanel.setMaximumSize(new Dimension(160, 40));

        JLabel qtyLabel = new JLabel(String.valueOf(cart.getQuantity(book.getId())), SwingConstants.CENTER);
        qtyLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        qtyLabel.setPreferredSize(new Dimension(50, 40));
        qtyLabel.setMinimumSize(new Dimension(50, 40));
        qtyLabel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180)));
        qtyLabel.setOpaque(true);
        qtyLabel.setBackground(Color.WHITE);

        JButton minusBtn = new JButton("-");
        JButton plusBtn = new JButton("+");

        // style buttons properly
        styleQuantityButton(minusBtn);
        styleQuantityButton(plusBtn);

        // add action listeners
        minusBtn.addActionListener(e -> {
            System.out.println("Minus clicked for book ID: " + book.getId() + ", current qty: " + cart.getQuantity(book.getId()));
            cartController.decreaseQuantity(book);
            System.out.println("After decrease, new qty: " + cart.getQuantity(book.getId()));
            updateCartDisplay();
        });

        plusBtn.addActionListener(e -> {
            System.out.println("Plus clicked for book ID: " + book.getId() + ", current qty: " + cart.getQuantity(book.getId()));
            cartController.increaseQuantity(book);
            System.out.println("After increase, new qty: " + cart.getQuantity(book.getId()));
            updateCartDisplay();
        });
        qtyPanel.add(minusBtn);
        qtyPanel.add(qtyLabel);
        qtyPanel.add(plusBtn);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 0, 10);
        gbc.weightx = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        productPanel.add(qtyPanel, gbc);

        // Price and Availability
        JPanel pricePanel = new JPanel(new GridLayout(2, 1, 0, 2));
        pricePanel.setBackground(Color.WHITE);

        JLabel priceLabel = new JLabel(book.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.BOLD, 18));
        priceLabel.setForeground(new Color(0, 140, 0));
        priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel availabilityLabel = new JLabel("Available: 20"); // TODO: Dynamically fetch availability
        availabilityLabel.setFont(new Font("Serif", Font.PLAIN, 14));
        availabilityLabel.setForeground(new Color(150, 75, 0));
        availabilityLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        pricePanel.add(priceLabel);
        pricePanel.add(availabilityLabel);

        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.insets = new Insets(0, 20, 0, 10);
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        productPanel.add(pricePanel, gbc);

        // Delete Button
        JButton deleteBtn = new JButton("✕"); // Use a nicer symbol
        styleDeleteButton(deleteBtn);
        deleteBtn.addActionListener(e -> {
            cartController.deleteBook(book);
            updateCartDisplay();
        });

        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.insets = new Insets(0, 10, 0, 0);
        gbc.fill = GridBagConstraints.NONE;
        productPanel.add(deleteBtn, gbc);

        return productPanel;
    }

    /**
     * Styles quantity buttons for a polished look.
     */
    private void styleQuantityButton(JButton btn) {
        btn.setFont(new Font("Dialog", Font.BOLD, 16));
        btn.setBackground(new Color(235, 235, 210));
        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        btn.setMinimumSize(new Dimension(40, 40));
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setMaximumSize(new Dimension(40, 40));
        btn.setFocusPainted(false);
        btn.setFocusable(true);
    }

    /**
     * Styles delete button for consistency.
     */
    private void styleDeleteButton(JButton btn) {
        btn.setBackground(new Color(200, 20, 20));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Serif", Font.BOLD, 16));
        btn.setBorder(BorderFactory.createLineBorder(new Color(150, 10, 10), 1));
        btn.setPreferredSize(new Dimension(40, 40));
        btn.setFocusPainted(false);
        btn.setRolloverEnabled(true);
    }

    /**
     * Updates the entire cart display after changes (reload items, update quantities, totals).
     */
    public void updateCartDisplay() {
        loadCartItems(); // Reload all panels
        updateTotals();
    }

    /**
     * Updates total labels from controller/model.
     */
    private void updateTotals() {
        int subtotal = cartController.getTotalCartValue();
        subtotalLabel.setText("Subtotal: " + subtotal + " DZD");

        int deliveryCost = Cart.DELIVERY_COST;

        int total = subtotal + deliveryCost;
        totalLabel.setText("Total: " + total + " DZD");
    }
}