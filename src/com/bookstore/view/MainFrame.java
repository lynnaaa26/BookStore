package com.bookstore.view;

import com.bookstore.model.Cart;
import com.bookController.BookController;
import com.bookController.CartController;
import com.bookstore.utils.InputValidator;
import com.formdev.flatlaf.FlatLightLaf;  // Import FlatLaf light theme
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;

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
        // Set FlatLaf as the Look and Feel (before any Swing components are created)
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            // Optional: Customize colors to match your theme (beige base, saddle brown accents)
            UIManager.put("Panel.background", Theme.BEIGE);  // Beige panels
            UIManager.put("Button.background", Theme.LIGHT_BEIGE);  // Light beige buttons
            UIManager.put("Button.foreground", Theme.SADDLE_BROWN);  // Brown text
            UIManager.put("Label.foreground", Theme.SADDLE_BROWN);  // Brown labels
            UIManager.put("TextField.background", Color.WHITE);  // White text fields
            UIManager.put("ScrollPane.background", Theme.BEIGE);  // Beige scroll areas
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to system L&F if FlatLaf fails
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        SwingUtilities.updateComponentTreeUI(this); // Refresh UI

        // 1. Initialize shared instances first
        sharedCart = new Cart();
        bookController = new BookController();
        cartController = new CartController(sharedCart);
        // 2. Initialize CartPanel with the frame (now cart is not null)
        cartPanel = new CartPanel(this);
        // 3. Initialize CardLayout and main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Theme.BEIGE);
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
        getRootPane().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.SADDLE_BROWN)); // Subtle bottom border
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
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}