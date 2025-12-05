package com.bookController;

import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import javax.swing.JOptionPane; // for delete dialog

public class CartController {
    private Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    // adding book to cart
    public void addToCart(Book book, int quantity) {
        if (quantity > 0) {
            cart.addBook(book, quantity);
        }
    }

    // increase quantity
    public void increaseQuantity(Book book) {
        cart.addBook(book, 1);
    }

    // decrease quantity
    public void decreaseQuantity(Book book) {
        //simply call removeBook
        // to handle removing the item completely if the quantity becomes 0.
        cart.removeBook(book, 1);
    }

    // delete book
    public void deleteBook(Book book) {
        int confirm = JOptionPane.showConfirmDialog(null, "Remove " + book.getTitle() + " from cart?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            cart.deleteBook(book);
            JOptionPane.showMessageDialog(null, "Removed from cart.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // get cart totals
    public int getTotalCartValue() { return cart.getTotalCartValue(); }
    public int getGrandTotal() { return cart.getGrandTotal(); }
    public boolean isEmpty() { return cart.isEmpty(); }
}