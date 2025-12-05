package com.bookstore.storage;

import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;
import com.bookstore.model.Wishlist;

import java.io.*;
import java.util.Optional;

public class WishlistFileStorage {

    private static final String FOLDER_PATH = System.getProperty("user.home") + File.separator + "BookStoreApp";
    private static final String FILE_PATH = FOLDER_PATH + File.separator + "wishlist.txt";

    /** Save wishlist */
    public static void save(Wishlist wishlist) {
        try {
            new File(FOLDER_PATH).mkdirs();

            try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
                for (int bookId : wishlist.getBookIds()) {
                    writer.println(bookId);
                }
            }

            System.out.println("Wishlist saved to: " + FILE_PATH);

        } catch (IOException e) {
            System.err.println("Error saving wishlist: " + e.getMessage());
        }
    }

    /** Load wishlist */
    public static void load(Wishlist wishlist) {
        File file = new File(FILE_PATH);

        if (!file.exists() || file.length() == 0) {
            System.out.println("Wishlist file missing or empty. Current wishlist remains unchanged.");
            return;
        }

        wishlist.clear(); // Only clear if file has content
        System.out.println("Loading wishlist from: " + FILE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int valid = 0, skipped = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                try {
                    int bookId = Integer.parseInt(line);
                    Optional<Book> bookOpt = BookRepository.getBookById(bookId);

                    if (bookOpt.isPresent()) {
                        wishlist.addBook(bookOpt.get());
                        valid++;
                    } else skipped++;

                } catch (NumberFormatException e) {
                    skipped++;
                }
            }

            System.out.println("Wishlist load complete: " + valid + " items loaded, " + skipped + " skipped.");

        } catch (IOException e) {
            System.err.println("Error reading wishlist file: " + e.getMessage());
        }
    }
}
