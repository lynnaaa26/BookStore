package com.bookController;

//  logic handels business logic for books by using bookrepository for data access

import com.bookstore.model.Book;
import com.bookstore.model.BookRepository;

import java.util.List;
import java.util.Optional;


public class BookController {
   // Get all books
   public List<Book> getAllBooks() {
       return BookRepository.getAllBooks();
   }

   // gettin the  book by ID
   public Optional<Book> getBookById(int id) {
       return BookRepository.getBookById(id);
   }

   // searching the book 
   public List<Book> searchBooks(String query) {
       return BookRepository.searchBooks(query);
   }
}
