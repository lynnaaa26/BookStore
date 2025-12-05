package com.bookstore.view;

import com.bookstore.model.Book; // *** AJOUT ***
import com.bookstore.model.Cart;
import com.bookstore.storage.CartFileStorage; // *** AJOUT : For loading/saving cart ***
import com.bookController.BookController;
import com.bookController.CartController;
import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Color;

public class MainFrame extends JFrame {
    // Shared instances
    private Cart sharedCart;
    private BookController bookController;
    private CartController cartController;

    // Panels
    private CartPanel cartPanel;
    private BookPanel bookPanel; // *** AJOUT *** panel detail livre

    // CardLayout for navigation
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrame() {
        // Set FlatLaf Look and Feel
        try {
            UIManager.put("Panel.background", Theme.BEIGE);
            UIManager.put("Button.background", Theme.LIGHT_BEIGE);
            UIManager.put("Button.foreground", Theme.SADDLE_BROWN);
            UIManager.put("Label.foreground", Theme.SADDLE_BROWN);
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("ScrollPane.background", Theme.BEIGE);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        SwingUtilities.updateComponentTreeUI(this);

        // 1. Initialize shared instances
        sharedCart = new Cart();
        bookController = new BookController();
        cartController = new CartController(sharedCart);

        // *** AJOUT : Load persisted cart on startup ***
        CartFileStorage.load(sharedCart);
        System.out.println("App started: Loaded cart with " + sharedCart.getBookQuantities().size() + " items.");

        // 2. Initialize panels
        cartPanel = new CartPanel(this);
        bookPanel = new BookPanel(this); // *** AJOUT ***

        // *** AJOUT : Refresh cart display after loading ***
        refreshCartPanel();

        // 3. Initialize CardLayout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.setBackground(Theme.BEIGE);

        // 4. Add panels
        mainPanel.add(new HomePanel(this), "HOME");
        mainPanel.add(bookPanel, "BOOKS"); // *** MODIFIÉ : on utilise l’instance ***
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
        getRootPane().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.SADDLE_BROWN));

        // 6. Show default panel
        navigateTo("HOME");
    }

    /* Navigate to a panel by name */
    public void navigateTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    /* Refresh cart panel content */
    public void refreshCartPanel() {
        if (cartPanel != null) {
            cartPanel.updateCartDisplay(); // reload items
        }
    }

    // *** AJOUT : méthode appelée quand on clique sur un livre ***
    public void showBookDetails(Book book) {
        bookPanel.setBook(book); // on met le livre dans le panel détail
        navigateTo("BOOKS"); // on affiche la page du livre
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