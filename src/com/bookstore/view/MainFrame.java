package com.bookstore.view;

import com.bookstore.model.Cart;
import com.bookController.BookController;
import com.bookController.CartController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    // Shared instances
    private Cart sharedCart;
    private BookController bookController;
    private CartController cartController;
    private CartPanel cartPanel;

    // CardLayout for navigation
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        // 1. Initialize shared instances first
        sharedCart = new Cart();
        bookController = new BookController();
        cartController = new CartController(sharedCart);

        // 2. Initialize CartPanel with the frame (now cart is not null)
        cartPanel = new CartPanel(this);

        // 3. Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 4. Add panels (pass frame; use single instance of CartPanel)
        mainPanel.add(new HomePanel(this), "HOME");
        mainPanel.add(new BookPanel(this), "BOOKS");
        mainPanel.add(cartPanel, "CART");
        mainPanel.add(new WishlistPanel(this), "WISHLIST");
        mainPanel.add(new OrderFormPanel(this), "ORDERFORM");
        mainPanel.add(new SearchPanel(this), "SEARCH");

        // 5. Setup JFrame
        setContentPane(mainPanel);
        setTitle("Bookstore");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        // 6. Show default panel
        navigateTo("HOME");
    }

    /**
     * Navigate to a panel by name
     */
    public void navigateTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    /**
     * Refresh cart panel content
     */
    public void refreshCartPanel() {
        if (cartPanel != null) {
            cartPanel.updateCartDisplay(); // reload items
        }
    }

    // Getters for shared instances
    public Cart getCart() { return sharedCart; }
    public BookController getBookController() { return bookController; }
    public CartController getCartController() { return cartController; }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
