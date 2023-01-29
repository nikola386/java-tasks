package com.nikola.library;

import java.util.ArrayList;

public class User {
    private String name;
    private final ArrayList<Book> books;

    public User(String name) {
        this.name = name;
        books = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        books.add(book);
        book.setBorrower(this);
    }

    public void returnBook(Book book) {
        books.remove(book);
        book.setBorrower(null);
    }

    public void donateBook(Book book) {

    }

    public String getName() {
        return name;
    }
}
