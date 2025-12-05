package com.bookstore.storage;

import com.bookstore.model.Cart;
import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;

import java.io.*;
import java.util.Optional;

public class CartFileStorage {

    private static final String FILE_PATH = "cart.txt";

    // ✅ Save cart to file (unchanged)
    public static void save(Cart cart) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (var entry : cart.getBookQuantities().entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.err.println("Error saving cart: " + e.getMessage());  // Better than printStackTrace
        }
    }

    // ✅ Enhanced Load: Handles corrupt/invalid data gracefully
    public static void load(Cart cart) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No cart file found; starting empty cart.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int validLines = 0;
            int skippedLines = 0;

            while ((line = reader.readLine()) != null) {
                // Trim and skip empty lines
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length != 2) {
                    skippedLines++;
                    System.err.println("Skipping invalid format line: " + line);
                    continue;
                }

                try {
                    int bookId = Integer.parseInt(parts[0].trim());
                    int quantity = Integer.parseInt(parts[1].trim());

                    // Integrity checks
                    if (quantity <= 0) {
                        skippedLines++;
                        System.err.println("Skipping invalid quantity (<=0): " + quantity + " for book " + bookId);
                        continue;
                    }

                    Optional<Book> bookOpt = BookRepository.getBookById(bookId);
                    if (bookOpt.isPresent()) {
                        cart.addBook(bookOpt.get(), quantity);
                        validLines++;
                    } else {
                        skippedLines++;
                        System.err.println("Warning: Book ID " + bookId + " not found in repository; skipping.");
                    }
                } catch (NumberFormatException e) {
                    skippedLines++;
                    System.err.println("Skipping unparseable line (bad number): " + line + " - " + e.getMessage());
                }
            }

            // Debug output
            System.out.println("Cart load complete: " + validLines + " valid items, " + skippedLines + " skipped.");
        } catch (IOException e) {
            System.err.println("Error reading cart file: " + e.getMessage());
        }
    }
}