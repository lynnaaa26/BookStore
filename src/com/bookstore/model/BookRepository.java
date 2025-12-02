package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * BookRepository: Central manager for all books in the bookstore.
 * Add your books in the static initializer block below.
 * This keeps data organized and easy to update.
 * In Session 7, we'll load from database instead of hardcoding.
 */
public class BookRepository {
    private static List<Book> books = new ArrayList<>();

    // Static initializer: ADD ALL YOUR BOOKS HERE!
    static {
        // Existing books from your panels (IDs start at 1)
        books.add(new Book(1, "The Yellow Wallpaper", "Charlotte Perkins Gilman", 1000, "/imagess/yellow.jpg"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", 1500, "/imagess/mockingbird.jpg"));
        books.add(new Book(3, "We Have Always Lived in the Castle", "Shirley Jackson", 1000, "/imagess/we castle.jpg"));
        books.add(new Book(4, "Mrs Dalloway", "Virginia Woolf", 1200, "/imagess/d.jpg"));
        books.add(new Book(5, "Frankenstein: The 1818 Text", "Mary Shelley", 2000, "/imagess/f.jpg"));
        books.add(new Book(6, "Fahrenheit 451", "Ray Bradbury", 2000, "/imagess/451.jpg"));
        books.add(new Book(7, "Cloud Atlas", "David Mitchell", 1200, "/imagess/cloud.jpg"));
        books.add(new Book(8, "The Girl on the Train", "Paula Hawkins", 1600, "/imagess/The_Girl_on_the_Train.jpg"));
        books.add(new Book(9, "Sense and Sensibility", "Jane Austen", 1300, "/imagess/sense.jpg"));
        books.add(new Book(10, "Harry Potter Pack", "J.K. Rowling", 12000, "/imagess/potter.jpg"));
        books.add(new Book(10, "The Poppy War", "Rebecca F. Kuang", 12000, "/imagess/poopy.jpg"));
        books.add(new Book(10, "Moby Dick", "Herman Melville", 12000, "/imagess/moby.jpg"));
        books.add(new Book(10, "the picture of dorian gray", "oscar wilde", 12000, "/imagess/gray.jpg"));
        books.add(new Book(10, "Wuthering helights", "emily bronte", 12000, "/imagess/emily.jpg"));
        

        // ADD YOUR NEW BOOKS HERE (example):
        // books.add(new Book(11, "1984", "George Orwell", 1100, "/imagess/1984.jpg"));
        // books.add(new Book(12, "Pride and Prejudice", "Jane Austen", 900, "/imagess/pride.jpg"));
        // books.add(new Book(13, "The Great Gatsby", "F. Scott Fitzgerald", 850, "/imagess/gatsby.jpg"));
        // ... Add as many as you want! Aim for 20-50 for a good bookstore demo.
    }

    // Get all books (returns a copy to protect the original list)
    public static List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    // Get a single book by ID
    public static Optional<Book> getBookById(int id) {
        return books.stream().filter(b -> b.getId() == id).findFirst();
    }

    // Search books by title or author (case-insensitive partial match)
    public static List<Book> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        return books.stream()
                .filter(b -> b.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                             b.getAuthor().toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

    // Add a new book (for future admin features)
    public static void addBook(Book book) {
        // Check for unique ID
        if (getBookById(book.getId()).isPresent()) {
            throw new IllegalArgumentException("Book ID already exists: " + book.getId());
        }
        books.add(book);
    }

    // Remove a book by ID
    public static void removeBook(int id) {
        books.removeIf(b -> b.getId() == id);
    }
}