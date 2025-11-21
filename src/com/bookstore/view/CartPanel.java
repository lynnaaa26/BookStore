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
    private JLabel totalCartLabel;
    private JLabel totalLabel;
    private JPanel itemsContainer; // Scrollable container for dynamic product panels
    private List<JLabel> quantityLabels = new ArrayList<>(); // Track per-item quantity labels for updates

    public CartPanel(MainFrame frame) {
    	 this.cartController = frame.getCartController();   
    	    this.cart = frame.getCart(); 
    	    this.bookController = frame.getBookController();


        setBackground(new Color(246, 245, 222));
        setLayout(new BorderLayout()); // Changed to BorderLayout for better scrolling

        // Title
        JLabel cartTitle = new JLabel("YOUR SHOPPING CART");
        cartTitle.setFont(new Font("Serif", Font.BOLD, 28));
        cartTitle.setForeground(new Color(100, 70, 30));
        cartTitle.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(new Color(246, 245, 222));
        titlePanel.add(cartTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Headers (above scrollable items)
        JPanel headersPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headersPanel.setBackground(new Color(246, 245, 222));
        headersPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));
        JLabel lblBook = new JLabel("Book");
        lblBook.setFont(new Font("Serif", Font.BOLD, 18));
        lblBook.setPreferredSize(new Dimension(200, 30));
        JLabel lblQty = new JLabel("Quantity");
        lblQty.setFont(new Font("Serif", Font.BOLD, 18));
        lblQty.setPreferredSize(new Dimension(150, 30));
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Serif", Font.BOLD, 18));
        lblPrice.setPreferredSize(new Dimension(100, 30));
        JLabel lblActions = new JLabel("Actions");
        lblActions.setFont(new Font("Serif", Font.BOLD, 18));
        lblActions.setPreferredSize(new Dimension(150, 30));
        headersPanel.add(lblBook);
        headersPanel.add(Box.createHorizontalStrut(50));
        headersPanel.add(lblQty);
        headersPanel.add(Box.createHorizontalStrut(50));
        headersPanel.add(lblPrice);
        headersPanel.add(Box.createHorizontalStrut(50));
        headersPanel.add(lblActions);
        add(headersPanel, BorderLayout.NORTH);

        // Scrollable container for cart items
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setBackground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setPreferredSize(new Dimension(900, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane, BorderLayout.CENTER);

        // -------------- TOTALS ----------------
        JPanel totalsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalsPanel.setBackground(new Color(246, 245, 222));
        totalsPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        totalCartLabel = new JLabel(""); // Will be set in updateTotals
        totalCartLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        JLabel deliveryLabel = new JLabel("Delivery costs : " + Cart.DELIVERY_COST + " DZD"); // Use constant from model
        deliveryLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        totalLabel = new JLabel(""); // Will be set in updateTotals
        totalLabel.setFont(new Font("Serif", Font.BOLD, 24));
        totalLabel.setForeground(new Color(0, 150, 0));

        totalsPanel.add(totalCartLabel);
        totalsPanel.add(Box.createVerticalStrut(5));
        totalsPanel.add(deliveryLabel);
        totalsPanel.add(Box.createVerticalStrut(5));
        totalsPanel.add(totalLabel);
        add(totalsPanel, BorderLayout.SOUTH);

        // Initial load and update
        loadCartItems();
        updateTotals();

        // ---------------- Buttons at the Bottom ----------------
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(246, 245, 222));
        JButton continueBtn = new JButton("Continue Shopping");
        continueBtn.setFont(new Font("Serif", Font.PLAIN, 14));
        continueBtn.setBackground(new Color(220, 220, 200));
        continueBtn.setForeground(new Color(101, 67, 33));
        continueBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
        continueBtn.addActionListener(e -> frame.navigateTo("HOME"));
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setFont(new Font("Serif", Font.BOLD, 14));
        confirmBtn.setBackground(new Color(220, 220, 200));
        confirmBtn.setForeground(new Color(101, 67, 33));
        confirmBtn.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 180), 1));
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
        add(buttonPanel, BorderLayout.SOUTH);
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
                    itemsContainer.add(Box.createVerticalStrut(10)); // Spacing between items
                }
            }
        }
        revalidate();
        repaint();
    }

    /**
     * Creates a single product panel for a book in cart.
     */
    private JPanel createProductPanel(Book book, int initialQty) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(null);
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(new LineBorder(new Color(210, 210, 180), 2));
        productPanel.setPreferredSize(new Dimension(900, 180));
        productPanel.setMaximumSize(new Dimension(900, 180));

        // Image Panel
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(new Color(235, 235, 210));
        imagePanel.setBorder(new LineBorder(new Color(200, 200, 170)));
        imagePanel.setBounds(20, 20, 120, 140);
        imagePanel.setLayout(null);

        // Load image dynamically from book model
        URL imageUrl = getClass().getResource(book.getImagePath());
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaledImage = icon.getImage().getScaledInstance(120, 140, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(scaledIcon);
            imageLabel.setBounds(0, 0, 120, 140);
            imagePanel.add(imageLabel);
        } else {
            JLabel placeholderLabel = new JLabel("No Image");
            placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
            placeholderLabel.setVerticalAlignment(SwingConstants.CENTER);
            placeholderLabel.setBounds(0, 0, 120, 140);
            imagePanel.add(placeholderLabel);
        }
        productPanel.add(imagePanel);

        // Title and Author from model
        JLabel bookTitle = new JLabel(book.getTitle());
        bookTitle.setFont(new Font("Serif", Font.BOLD, 20));
        bookTitle.setBounds(160, 30, 300, 30);
        productPanel.add(bookTitle);

        JLabel bookAuthor = new JLabel("Author: " + book.getAuthor());
        bookAuthor.setFont(new Font("Serif", Font.ITALIC, 16));
        bookAuthor.setForeground(new Color(120, 70, 40));
        bookAuthor.setBounds(160, 65, 350, 30);
        productPanel.add(bookAuthor);

        // Quantity Buttons (tied to controller)
        JButton minusBtn = new JButton("-");
        minusBtn.setBounds(450, 60, 50, 35);
        minusBtn.addActionListener(e -> {
            cartController.decreaseQuantity(book);
            updateCartDisplay();
        });
        productPanel.add(minusBtn);

        JLabel qtyLabel = new JLabel(String.valueOf(initialQty), SwingConstants.CENTER);
        qtyLabel.setFont(new Font("Serif", Font.BOLD, 18));
        qtyLabel.setBounds(505, 60, 40, 35);
        productPanel.add(qtyLabel);
        quantityLabels.add(qtyLabel); // Track for updates

        JButton plusBtn = new JButton("+");
        plusBtn.setBounds(550, 60, 50, 35);
        plusBtn.addActionListener(e -> {
            cartController.increaseQuantity(book);
            updateCartDisplay();
        });
        productPanel.add(plusBtn);

        // Price from model
        JLabel priceLabel = new JLabel(book.getPrice() + " DZD");
        priceLabel.setFont(new Font("Serif", Font.BOLD, 18));
        priceLabel.setForeground(new Color(0, 140, 0));
        priceLabel.setBounds(640, 60, 120, 35);
        productPanel.add(priceLabel);

        // Delete Button (tied to controller)
        JButton deleteBtn = new JButton("X");
        deleteBtn.setBackground(new Color(200, 20, 20));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setBounds(780, 55, 60, 40);
        deleteBtn.addActionListener(e -> {
            cartController.deleteBook(book);
            updateCartDisplay();
        });
        productPanel.add(deleteBtn);

        return productPanel;
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
        int totalCart = cartController.getTotalCartValue();
        totalCartLabel.setText("Total cart : " + totalCart + " DZD");
        int total = cartController.getGrandTotal();
        totalLabel.setText("Total : " + total + " DZD");
    }
}