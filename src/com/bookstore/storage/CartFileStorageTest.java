package com.bookstore.storage;

import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;
import com.bookstore.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CartFileStorageTest {
    private Cart cart;
    private Book testBook;

    @BeforeEach
    void setUp() {
        BookRepository.clear();  // Reset repo to avoid duplicate ID exceptions
        cart = new Cart();
        testBook = new Book(1, "Test Book", "Author", 10, "test.jpg");
        BookRepository.addBook(testBook);  // Now safe to add
    }

    @AfterEach
    void tearDown() {
        new File("cart.txt").delete();  // Clean up file after each test
    }

    @Test
    void testSaveAndLoad() {
        // Add to cart
        cart.addBook(testBook, 2);

        // Save
        CartFileStorage.save(cart);

        // Load into a new cart
        Cart loadedCart = new Cart();
        CartFileStorage.load(loadedCart);

        // Assert integrity: quantities match (use map with ID)
        Map<Integer, Integer> quantities = loadedCart.getBookQuantities();
        assertEquals(2, quantities.get(testBook.getId()));
        assertEquals(1, quantities.size());
    }

    @Test
    void testLoadNonExistentFile() {
        Cart emptyCart = new Cart();
        CartFileStorage.load(emptyCart);
        assertTrue(emptyCart.isEmpty());
    }

    @Test
    void testLoadCorruptFile() {
        // Simulate corrupt file: manually write invalid line to cart.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("cart.txt"))) {
            writer.println("invalid,abc"); // Bad parse
        } catch (IOException e) {
            fail("Failed to create corrupt file: " + e.getMessage());
        }
        
        Cart loadedCart = new Cart();
        CartFileStorage.load(loadedCart); // Should not crash; handle gracefully
        
        assertTrue(loadedCart.isEmpty()); // Should remain empty
    }

    // New test: Partial corrupt file (some valid, some bad)
    @Test
    void testLoadPartialCorruptFile() {
        // Write mixed file: one valid (ID 1, qty 3), one corrupt
        try (PrintWriter writer = new PrintWriter(new FileWriter("cart.txt"))) {
            writer.println("1,3");  // Valid
            writer.println("invalid,abc");  // Corrupt
        } catch (IOException e) {
            fail("Failed to create partial corrupt file: " + e.getMessage());
        }

        Book validBook = new Book(1, "Valid Book", "Author", 10, "valid.jpg");
        BookRepository.clear();
        BookRepository.addBook(validBook);

        Cart loadedCart = new Cart();
        CartFileStorage.load(loadedCart);

        Map<Integer, Integer> quantities = loadedCart.getBookQuantities();
        assertEquals(3, quantities.get(1));  // Only valid loaded
        assertEquals(1, quantities.size());  // Skipped the bad one
    }
}