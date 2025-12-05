package com.bookstore.model;

import com.bookstore.storage.CartFileStorage; // *** AJOUT : For auto-saving on changes ***
import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Integer, Integer> bookQuantities; // key: Book ID, Value: Quantity
    public static final int DELIVERY_COST = 500; // delivery cost

    public Cart() {
        bookQuantities = new HashMap<>();
    }

    // add or update quantity for a book
    public void addBook(Book book, int quantity) {
        int currentQty = bookQuantities.getOrDefault(book.getId(), 0);
        bookQuantities.put(book.getId(), currentQty + quantity);
        CartFileStorage.save(this);  // *** AJOUT : Auto-save after change ***
    }

    // remove or decrease quantity
    public void removeBook(Book book, int quantity) {
        int currentQty = bookQuantities.getOrDefault(book.getId(), 0);
        if (currentQty > quantity) {
            bookQuantities.put(book.getId(), currentQty - quantity);
        } else {
            bookQuantities.remove(book.getId());
        }
        CartFileStorage.save(this);  // *** AJOUT : Auto-save after change ***
    }

    // delete book entirely
    public void deleteBook(Book book) {
        bookQuantities.remove(book.getId());
        CartFileStorage.save(this);  // *** AJOUT : Auto-save after change ***
    }

    // get total cart value ,now fetches prices from BookRepository
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

    // getting grand total with delivery
    public int getGrandTotal() {
        return getTotalCartValue() + DELIVERY_COST;
    }

    // is cart empty?
    public boolean isEmpty() {
        return bookQuantities.isEmpty();
    }

    // get quantity for a book
    public int getQuantity(int bookId) {
        return bookQuantities.getOrDefault(bookId, 0);
    }

    // get all book ids in cart
    public Map<Integer, Integer> getBookQuantities() {
        return new HashMap<>(bookQuantities);
    }

    public Map getItems() {
        // TODO Auto-generated method stub
        return null;
    }
}