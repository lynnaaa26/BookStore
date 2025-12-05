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

    // Static initializer: ADD ALL YOUR BOOKS HERE
    static {
        books.add(new Book(1, "The Yellow Wallpaper", "Charlotte Perkins Gilman", 1000, "/imagess/yellow.jpg",
                "A short story about a woman’s mental health and her descent into madness while confined to a room."));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee", 1500, "/imagess/mockingbird.jpg",
                "In a racially divided Southern town, young Scout Finch witnesses her father defend an innocent Black man accused of a crime, exploring themes of justice and morality."));
        books.add(new Book(3, "We Have Always Lived in the Castle", "Shirley Jackson", 1000, "/imagess/we castle.jpg",
                "Two sisters live in isolation after their family is poisoned, uncovering dark secrets and psychological tension in a gothic setting."));
        books.add(new Book(4, "Mrs Dalloway", "Virginia Woolf", 1200, "/imagess/d.jpg",
                "Follows Clarissa Dalloway through a single day in London as she prepares for a party, interweaving reflections on life, love, and societal pressures."));
        books.add(new Book(5, "Frankenstein: The 1818 Text", "Mary Shelley", 2000, "/imagess/f.jpg",
                "A scientist’s obsession with creating life leads to a monstrous being, exploring themes of ambition, responsibility, and human nature."));
        books.add(new Book(6, "Fahrenheit 451", "Ray Bradbury", 2000, "/imagess/451.jpg",
                "In a dystopian future, books are banned and “firemen” burn them, while one man begins to question the society’s oppressive control over knowledge."));
        books.add(new Book(7, "Cloud Atlas", "David Mitchell", 1200, "/imagess/cloud.jpg",
                "Six interconnected stories spanning centuries explore how individual actions ripple across time, examining freedom, oppression, and human connection."));
        books.add(new Book(8, "The Girl on the Train", "Paula Hawkins", 1600, "/imagess/The_Girl_on_the_Train.jpg",
                "A troubled woman becomes entangled in a missing person investigation, revealing secrets and deception among those around her."));
        books.add(new Book(9, "Sense and Sensibility", "Jane Austen", 1300, "/imagess/sense.jpg",
                "The Dashwood sisters navigate love, heartbreak, and social expectations in 19th-century England, balancing reason and emotion."));
        books.add(new Book(10, "Harry Potter Pack", "J.K. Rowling", 12000, "/imagess/potter.jpg",
                "A boxed set containing all Harry Potter books, following the magical adventures of Harry Potter and his friends as they battle dark forces and grow up at Hogwarts School of Witchcraft and Wizardry."));
        books.add(new Book(11, "The Poppy War", "Rebecca F. Kuang", 12000, "/imagess/poopy.jpg",
                "A war orphan rises to power in a brutal empire, discovering her shamanic abilities while navigating war, politics, and vengeance."));
        books.add(new Book(12, "Moby Dick", "Herman Melville", 12000, "/imagess/moby.jpg",
                "Captain Ahab obsessively hunts the white whale, Moby Dick, in a tale of obsession, revenge, and humanity’s struggle against nature."));
        books.add(new Book(13, "The Picture of Dorian Gray", "Oscar Wilde", 12000, "/imagess/gray.jpg",
                "Dorian Gray remains forever young while his portrait ages, exploring vanity, moral corruption, and the consequences of indulgence."));
        books.add(new Book(14, "Wuthering Heights", "Emily Bronte", 12000, "/imagess/emily.jpg",
                "A dark tale of passion and revenge unfolds across generations on the Yorkshire moors, focusing on the tumultuous love between Heathcliff and Catherine."));
    }

    // *** AJOUT : Clear all books (for tests or resets; call before adding test data) ***
    public static void clear() {
        books.clear();
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