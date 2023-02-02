package com.nikola.library;

import java.math.BigDecimal;

public class LibraryTest {
    private static LibraryManager lib;

    public static void main(String[] args) throws Exception {
        lib = LibraryManager.getInstance();
        createBooks();
        createUsers();

        lib.updateBook(2, "123-456-789-9", null, null, 0, null);
        lib.deleteBook(lib.searchBookByTitle("To Kill a Mockingbird"));

        Book bookToLend = lib.searchBookByTitle("1984");
        User user = lib.searchUser("John Doe");
        lib.lendBook(bookToLend, user);
        System.out.printf("Book %s is lent: %s%n", bookToLend.getTitle(), bookToLend.isBorrowed());
        user.returnBook(bookToLend);
        System.out.printf("Book %s is lent: %s%n", bookToLend.getTitle(), bookToLend.isBorrowed());

        user.donateBook(new Book("123-456-789-0", "To Kill a Mockingbird", "Harper Lee",
                1960, BigDecimal.valueOf(10.99)));
    }

    private static void createUsers() {
        lib.addUser(new User("John Doe"));
        lib.addUser(new User("Jane Doe"));
        lib.addUser(new User("Bob Smith"));
        lib.addUser(new User("Alice Johnson"));
        lib.addUser(new User("Charlie Brown"));
    }

    private static void createBooks() {
        if(lib.searchBookByISBN("123-456-789-0") == null) lib.addBook(new Book( "123-456-789-0", "To Kill a Mockingbird", "Harper Lee",
                1960, BigDecimal.valueOf(10.99)));
        if(lib.searchBookByISBN("456-789-123-1") == null) lib.addBook(new Book("456-789-123-1", "1984", "George Orwell",
                1949, BigDecimal.valueOf(12.99)));
        if(lib.searchBookByISBN("789-123-456-2") == null) lib.addBook(new Book("789-123-456-2", "Pride and Prejudice", "Jane Austen",
                1813, BigDecimal.valueOf(14.99)));
        if(lib.searchBookByISBN("111-222-333-4") == null) lib.addBook(new Book("111-222-333-4", "The Great Gatsby", "F. Scott Fitzgerald",
                1925, BigDecimal.valueOf(11.99)));
        if(lib.searchBookByISBN("222-333-444-5") == null) lib.addBook(new Book("222-333-444-5", "The Catcher in the Rye", "J.D. Salinger",
                1951, BigDecimal.valueOf(13.99)));
    }
}