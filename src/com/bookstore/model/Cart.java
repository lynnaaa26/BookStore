package com.bookstore.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> bookQuantities; // Key: Book ID, Value: Quantity
    public static final int DELIVERY_COST = 500; // Fixed delivery cost

    public Cart() {
        bookQuantities = new HashMap<>();
    }

    // Add or update quantity for a book
    public void addBook(Book book, int quantity) {
        int currentQty = bookQuantities.getOrDefault(book.getId(), 0);
        bookQuantities.put(book.getId(), currentQty + quantity);
    }

    // Remove or decrease quantity
    public void removeBook(Book book, int quantity) {
        int currentQty = bookQuantities.getOrDefault(book.getId(), 0);
        if (currentQty > quantity) {
            bookQuantities.put(book.getId(), currentQty - quantity);
        } else {
            bookQuantities.remove(book.getId());
        }
    }

    // Delete book entirely
    public void deleteBook(Book book) {
        bookQuantities.remove(book.getId());
    }

    // Get total cart value (excluding delivery) - Now fetches prices from BookRepository
    public int getTotalCartValue() {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : bookQuantities.entrySet()) {
            int bookId = entry.getKey();
            Book book = BookRepository.getBookById(bookId).orElse(null);
            if (book != null) {
                total += book.getPrice() * entry.getValue();
            }
        }
        return total;
    }

    // Get grand total (with delivery)
    public int getGrandTotal() {
        return getTotalCartValue() + DELIVERY_COST;
    }

    // Is cart empty?
    public boolean isEmpty() {
        return bookQuantities.isEmpty();
    }

    // Get quantity for a book
    public int getQuantity(int bookId) {
        return bookQuantities.getOrDefault(bookId, 0);
    }

    // Get all book IDs in cart
    public Map<Integer, Integer> getBookQuantities() {
        return new HashMap<>(bookQuantities);
    }
}