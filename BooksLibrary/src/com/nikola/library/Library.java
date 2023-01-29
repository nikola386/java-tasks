package com.nikola.library;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Library {
    private final ArrayList<User> users;
    private final ArrayList<Book> books;
    private static Library obj;

    public static Library getInstance() {
        if (obj == null) {
            obj = new Library();
        }

        return obj;
    }

    private Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
    }

    public Book searchBook(String title) throws BookException {
        return books.stream().filter((b) -> b.getTitle().equals(title.trim())).findFirst()
                .orElseThrow(() -> new BookException("Book " + title + " not found"));
    }

    public void addBook(Book book) {
        books.add(book);
        saveBooksToXml();
    }

    public void lendBook(Book book, User to) throws BookException {
        if(book.isBorrowed()) {
            throw new BookException("Book is already borrowed");
        }
        to.borrowBook(book);
    }

    public void updateBook(int id, String isbn, String title, String author, int publishedYear, BigDecimal price) throws BookException {
        Book book = getBookById(id);
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setPrice(price);
        book.setAuthor(author);
        book.setPublishedYear(publishedYear);

        saveBooksToXml();
    }

    public void deleteBook(Book book) throws BookException {
        if(book.isBorrowed()) {
            throw new BookException("Book is borrowed");
        }

        Book bookToDelete = books.stream().filter((b) -> b.equals(book)).findFirst()
                .orElseThrow(() -> new BookException("Book " + book.getId() + " not found in the library"));
        books.remove(bookToDelete);

        saveBooksToXml();
    }

    private Book getBookById(int id) throws BookException {
        return books.stream().filter((b) -> b.getId() == id).findFirst()
                .orElseThrow(() -> new BookException("Book with id " + id + " not found"));
    }

    private void loadBooksFromXml() {

    }

    private void saveBooksToXml() {

    }
}
