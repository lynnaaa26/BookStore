package com.bookController;

import com.bookstore.model.Book;
import com.bookstore.model.Wishlist;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class WishlistController {
    private Wishlist wishlist;

    public WishlistController(Wishlist wishlist) {
        this.wishlist = wishlist;
    }

    // Add book to wishlist
    public void addBook(Book book) {
        if (book != null) {
            wishlist.addBook(book);
            try {
                JOptionPane.showMessageDialog(null, book.getTitle() + " added to wishlist!", "Added", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                System.err.println("UI error after add: " + e.getMessage());
            }
        }
    }

    // Remove book from wishlist
    public void removeBook(Book book) {
        if (book != null) {
            int confirm = JOptionPane.showConfirmDialog(null, "Remove " + book.getTitle() + " from wishlist?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                wishlist.removeBook(book);
                JOptionPane.showMessageDialog(null, book.getTitle() + " removed from wishlist.", "Removed", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    // Check if a book is in wishlist
    public boolean isInWishlist(Book book) {
        return book != null && wishlist.contains(book);
    }

    // Get the size of the wishlist
    public int size() {
        return wishlist.size();
    }

    public boolean isEmpty() {
        return wishlist.isEmpty();
    }

    // **New method**: get all books in wishlist
    public List<Book> getAllBooks() {
        return new ArrayList<>(wishlist.getBooks()); // Returns a copy to prevent external modification
    }
}
