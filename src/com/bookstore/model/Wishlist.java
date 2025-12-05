package com.bookstore.model;

import com.bookstore.storage.WishlistFileStorage; // For auto-saving
import java.util.HashSet;
import java.util.Set;

public class Wishlist {
    private Set<Book> books = new HashSet<>(); // No duplicates

    public Wishlist() {}

    // Add a book (if not already present) – *** FIXED: Try-catch on save to prevent crash ***
    public void addBook(Book book) {
        if (book != null && books.add(book)) { // add() returns true if added
            try {
                WishlistFileStorage.save(this); // Auto-save
            } catch (Exception e) { // Catch IO or other errors
                System.err.println("Failed to save wishlist after add: " + e.getMessage());
                // Still added to memory, but warn user if needed
            }
        }
    }

    // Remove a book – *** FIXED: Try-catch on save ***
    public void removeBook(Book book) {
        if (book != null && books.remove(book)) {
            try {
                WishlistFileStorage.save(this); // Auto-save
            } catch (Exception e) {
                System.err.println("Failed to save wishlist after remove: " + e.getMessage());
            }
        }
    }

    // Check if contains book
    public boolean contains(Book book) {
        return book != null && books.contains(book);
    }

    // Get all books (defensive copy)
    public Set<Book> getBooks() {
        return new HashSet<>(books);
    }

    // For storage: Get set of book IDs
    public Set<Integer> getBookIds() {
        Set<Integer> ids = new HashSet<>();
        for (Book book : books) {
            if (book != null) {
                ids.add(book.getId());
            }
        }
        return ids;
    }

    // Clear all (for load reset)
    public void clear() {
        books.clear();
        try {
            WishlistFileStorage.save(this); // Save empty state
        } catch (Exception e) {
            System.err.println("Failed to save empty wishlist: " + e.getMessage());
        }
    }

    // Is empty?
    public boolean isEmpty() {
        return books.isEmpty();
    }

    // Size
    public int size() {
        return books.size();
    }
}