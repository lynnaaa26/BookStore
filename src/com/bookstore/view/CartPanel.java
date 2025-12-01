package com.bookstore.view;

import com.bookstore.model.Book;
import com.bookController.BookController;
import com.bookController.CartController;
import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import com.bookstore.model.Cart;

public class CartPanel extends JPanel {
    private Cart cart;
    private CartController cartController;
    private BookController bookController;

    private JLabel subtotalLabel;
    private JLabel totalLabel;
    private JPanel itemsContainer;

    public CartPanel(MainFrame frame) {
        this.cartController = frame.getCartController();
        this.cart = frame.getCart();
        this.bookController = frame.getBookController();

        setBackground(Color.WHITE);
        setLayout(new BorderLayout(10, 10));

        // North container for header and title
        JPanel northContainer = new JPanel(new BorderLayout());
        northContainer.setBackground(Color.WHITE);

        // Header
        RoundedPanel header = createHeaderPanel(frame);
        northContainer.add(header, BorderLayout.NORTH);

        // Title
        JLabel cartTitle = new JLabel("SHOPPING CART");
        cartTitle.setFont(new Font("Serif", Font.BOLD, 24));
        cartTitle.setForeground(Color.BLACK);
        cartTitle.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 0));
        JPanel titleContainer = new JPanel(new BorderLayout());
        titleContainer.setBackground(Color.WHITE);
        titleContainer.add(cartTitle, BorderLayout.CENTER);
        northContainer.add(titleContainer, BorderLayout.CENTER);

        add(northContainer, BorderLayout.NORTH);

        // Items
        itemsContainer = new JPanel();
        itemsContainer.setLayout(new BoxLayout(itemsContainer, BoxLayout.Y_AXIS));
        itemsContainer.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemsContainer);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        // Subtotal + delivery + total panel
        RoundedPanel totalsPanel = createTotalsPanel(frame);
        add(totalsPanel, BorderLayout.SOUTH);

        loadCartItems();
        updateTotals();
    }

    private RoundedPanel createHeaderPanel(MainFrame frame) {
        RoundedPanel header = new RoundedPanel(8);
        header.setLayout(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton backButton = Theme.createIconButton("← Back", e -> frame.navigateTo("HOME"));
        backButton.setForeground(Color.BLACK);
        header.add(backButton, BorderLayout.WEST);

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

        JPanel rightIcons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        rightIcons.setBackground(Color.WHITE);

        JButton wishlistIcon = Theme.createIconButton("❤️", e -> frame.navigateTo("WISHLIST"));
        wishlistIcon.setForeground(Color.BLACK);
        JButton homeIcon = Theme.createIconButton("🏠", e -> frame.navigateTo("HOME"));
        homeIcon.setForeground(Color.BLACK);
        rightIcons.add(wishlistIcon);
        rightIcons.add(homeIcon);
        header.add(rightIcons, BorderLayout.EAST);

        return header;
    }

    private RoundedPanel createTotalsPanel(MainFrame frame) {
        RoundedPanel totalsWrapper = new RoundedPanel(12);
        totalsWrapper.setLayout(new BorderLayout());
        totalsWrapper.setBackground(Color.WHITE);
        totalsWrapper.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // Subtotal + delivery + total panel with centered lines and limited beige background
        JPanel totalsContent = new JPanel();
        totalsContent.setLayout(new BoxLayout(totalsContent, BoxLayout.Y_AXIS));
        totalsContent.setBackground(Color.WHITE);

        // Subtotal line
        JPanel subtotalLine = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        subtotalLine.setBackground(Theme.LIGHT_BEIGE);
        subtotalLine.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        subtotalLabel = new JLabel();
        subtotalLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        subtotalLabel.setForeground(Color.BLACK);
        subtotalLine.add(subtotalLabel);
        totalsContent.add(subtotalLine);

        // Delivery line
        JPanel deliveryLine = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        deliveryLine.setBackground(Theme.LIGHT_BEIGE);
        deliveryLine.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JLabel deliveryLabel = new JLabel("Delivery costs : " + Cart.DELIVERY_COST + " DZD");
        deliveryLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        deliveryLabel.setForeground(Color.BLACK);
        deliveryLine.add(deliveryLabel);
        totalsContent.add(deliveryLine);

        // Separator line
        JPanel sepPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 5));
        sepPanel.setOpaque(false);
        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setForeground(Theme.DARK_BROWN);
        sep.setPreferredSize(new Dimension(200, 1));
        sepPanel.add(sep);
        totalsContent.add(sepPanel);

        // Total line
        JPanel totalLine = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        totalLine.setBackground(Theme.LIGHT_BEIGE);
        totalLine.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Serif", Font.BOLD, 24));
        totalLabel.setForeground(Color.BLACK);
        totalLine.add(totalLabel);
        totalsContent.add(totalLine);

        totalsWrapper.add(totalsContent, BorderLayout.NORTH);

        // Buttons outside totals panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton continueBtn = new JButton("CONTINUE SHOPPING");
        continueBtn.setBackground(Color.LIGHT_GRAY);
        continueBtn.setForeground(Color.BLACK);
        continueBtn.setFont(new Font("Serif", Font.BOLD, 14));
        continueBtn.setFocusPainted(false);
        continueBtn.addActionListener(e -> frame.navigateTo("HOME"));

        JButton confirmBtn = new JButton("CONFIRM");
        confirmBtn.setBackground(Theme.GREEN);
        confirmBtn.setForeground(Color.WHITE);
        confirmBtn.setFont(new Font("Serif", Font.BOLD, 14));
        confirmBtn.setFocusPainted(false);
        confirmBtn.addActionListener(e -> {
            if (!cart.isEmpty()) frame.navigateTo("ORDERFORM");
            else JOptionPane.showMessageDialog(this, "Cart is empty!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        buttonPanel.add(continueBtn);
        buttonPanel.add(confirmBtn);

        totalsWrapper.add(buttonPanel, BorderLayout.SOUTH);

        return totalsWrapper;
    }

    private void loadCartItems() {
        itemsContainer.removeAll();

        Map<Integer, Integer> quantities = cart.getBookQuantities();
        if (quantities.isEmpty()) {
            JLabel emptyLabel = new JLabel("Your cart is empty", SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Serif", Font.PLAIN, 16));
            emptyLabel.setForeground(Color.BLACK);
            itemsContainer.add(emptyLabel);
        } else {
            for (int id : quantities.keySet()) {
                if (quantities.get(id) > 0) {
                    Book book = bookController.getBookById(id).orElse(null);
                    if (book != null) {
                        RoundedPanel productPanel = createProductPanel(book, quantities.get(id));
                        itemsContainer.add(productPanel);
                        itemsContainer.add(Box.createVerticalStrut(15));
                    }
                }
            }
        }

        revalidate();
        repaint();
    }

    private RoundedPanel createProductPanel(Book book, int initialQty) {
        RoundedPanel productPanel = new RoundedPanel(12);
        productPanel.setLayout(new GridBagLayout());
        productPanel.setBackground(Color.WHITE);
        productPanel.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 10);

        // Book image
        RoundedPanel imagePanel = new RoundedPanel(8);
        imagePanel.setPreferredSize(new Dimension(80, 100));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.setBackground(Theme.LIGHT_BEIGE);
        URL imageUrl = getClass().getResource(book.getImagePath());
        if (imageUrl != null) {
            ImageIcon icon = new ImageIcon(imageUrl);
            Image scaled = icon.getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH);
            imagePanel.add(new JLabel(new ImageIcon(scaled)));
        } else {
            JLabel noImg = new JLabel("No Image", SwingConstants.CENTER);
            noImg.setForeground(Color.BLACK);
            imagePanel.add(noImg);
        }

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridheight = 2;
        productPanel.add(imagePanel, gbc);

        // Title and author
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridheight = 1;
        JLabel title = new JLabel(book.getTitle());
        title.setFont(new Font("Serif", Font.BOLD, 16));
        title.setForeground(Color.BLACK);
        productPanel.add(title, gbc);

        gbc.gridy = 1;
        JLabel author = new JLabel("Author: " + book.getAuthor());
        author.setFont(new Font("Serif", Font.ITALIC, 14));
        author.setForeground(Color.DARK_GRAY);
        productPanel.add(author, gbc);

        // Quantity
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridheight = 2;
        productPanel.add(createQuantityPanel(book), gbc);

        // Price
        gbc.gridx = 3;
        JLabel price = new JLabel(book.getPrice() + " DZD");
        price.setFont(new Font("Serif", Font.BOLD, 18));
        price.setHorizontalAlignment(SwingConstants.RIGHT);
        price.setForeground(Theme.GREEN);
        productPanel.add(price, gbc);

        // Delete
        gbc.gridx = 4;
        JButton deleteBtn = new JButton("✕");
        deleteBtn.setBackground(Color.LIGHT_GRAY);
        deleteBtn.setForeground(Color.BLACK);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setFont(new Font("Serif", Font.BOLD, 14));
        deleteBtn.addActionListener(e -> {
            cartController.deleteBook(book);
            updateCartDisplay();
        });
        productPanel.add(deleteBtn, gbc);

        return productPanel;
    }

    private JPanel createQuantityPanel(Book book) {
        RoundedPanel qtyPanel = new RoundedPanel(6);
        qtyPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        qtyPanel.setBackground(Theme.LIGHT_BEIGE);

        JLabel qtyLabel = new JLabel(String.valueOf(cart.getQuantity(book.getId())));
        qtyLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        qtyLabel.setForeground(Color.BLACK);
        qtyLabel.setPreferredSize(new Dimension(40, 30));
        qtyLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qtyLabel.setBorder(BorderFactory.createLineBorder(Theme.LIGHT_BORDER, 1));
        qtyLabel.setOpaque(true);
        qtyLabel.setBackground(Color.WHITE);

        JButton minusBtn = new JButton("-");
        JButton plusBtn = new JButton("+");
        minusBtn.setBackground(Color.LIGHT_GRAY);
        plusBtn.setBackground(Color.LIGHT_GRAY);
        minusBtn.setForeground(Color.BLACK);
        plusBtn.setForeground(Color.BLACK);
        minusBtn.setFocusPainted(false);
        plusBtn.setFocusPainted(false);

        minusBtn.addActionListener(e -> {
            cartController.decreaseQuantity(book);
            qtyLabel.setText(String.valueOf(cart.getQuantity(book.getId())));
            updateTotals();
        });

        plusBtn.addActionListener(e -> {
            cartController.increaseQuantity(book);
            qtyLabel.setText(String.valueOf(cart.getQuantity(book.getId())));
            updateTotals();
        });

        qtyPanel.add(minusBtn);
        qtyPanel.add(qtyLabel);
        qtyPanel.add(plusBtn);

        return qtyPanel;
    }

    public void updateCartDisplay() {
        loadCartItems();
        updateTotals();
    }

    private void updateTotals() {
        int subtotal = cartController.getTotalCartValue();
        subtotalLabel.setText("Subtotal: " + subtotal + " DZD");

        int total = subtotal + Cart.DELIVERY_COST;
        totalLabel.setText("Total: " + total + " DZD");
    }
}