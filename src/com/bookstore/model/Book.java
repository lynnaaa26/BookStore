package com.bookstore.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int price; // in DZD
    private String imagePath;

    // description
    private String description;

    // Constructor
    public Book(int id, String title, String author, int price, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imagePath = imagePath;
    }

    // constructeur complet avec description
    public Book(int id, String title, String author, int price, String imagePath, String description) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.imagePath = imagePath;
        this.description = description;
    }

    // *** FIX : Removed stub constructor (unused; was causing confusion with double price/stock) ***

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public int getPrice() { return price; }
    public void setPrice(int price) { this.price = price; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    // getter description
    public String getDescription() {
        return description;
    }

    // setter description
    public void setDescription(String description) {
        this.description = description;
    }
}