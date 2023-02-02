package com.nikola.library;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;

public class LibraryManager extends Library {
    private static final String FILE_PATH = "./books.xml";
    private static LibraryManager obj;
    private int nextBookId;

    public static LibraryManager getInstance() {
        if (obj == null) {
            obj = new LibraryManager();
        }

        return obj;
    }

    private LibraryManager() {
        loadBooksFromXml();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User searchUser(String name) throws Exception {
        return users.stream().filter((b) -> b.getName().equals(name.trim())).findFirst()
                .orElseThrow(() -> new Exception("User " + name + " not found"));
    }

    public Book searchBookByTitle(String title) throws BookException {
        return books.stream().filter((b) -> b.getTitle().equals(title.trim())).findFirst()
                .orElseThrow(() -> new BookException("Book " + title + " not found"));
    }

    public Book searchBookByISBN(String isbn) {
        return books.stream().filter((b) -> b.getIsbn().equals(isbn.trim())).findFirst().orElse(null);
    }

    public void addBook(Book book) {
        book.setId(nextBookId);
        books.add(book);
        saveBooksToXml();
        nextBookId++;
    }

    public void lendBook(Book book, User to) throws BookException {
        if (book.isBorrowed()) {
            throw new BookException("Book is already borrowed");
        }
        to.borrowBook(book);
    }

    public void updateBook(int id, String isbn, String title, String author, int publishedYear, BigDecimal price) {
        Book book = getBookById(id);
        if (isbn != null) book.setIsbn(isbn);
        if (title != null) book.setTitle(title);
        if (price != null) book.setPrice(price);
        if (author != null) book.setAuthor(author);
        if (publishedYear > 0) book.setPublishedYear(publishedYear);

        saveBooksToXml();
    }

    public void deleteBook(Book book) throws BookException {
        if (book.isBorrowed()) {
            throw new BookException("Book is borrowed");
        }

        Book bookToDelete = books.stream().filter((b) -> b.equals(book)).findFirst()
                .orElseThrow(() -> new BookException("Book " + book.getId() + " not found in the library"));
        books.remove(bookToDelete);

        saveBooksToXml();
    }

    private Book getBookById(int id) {
        return books.stream().filter((b) -> b.getId() == id).findFirst()
                .orElse(null);
    }

    private void loadBooksFromXml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Library data = (Library) context.createUnmarshaller()
                    .unmarshal(new FileReader(FILE_PATH));
            books = data.books;
            nextBookId = data.books.size() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBooksToXml() {
        try {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            mar.marshal(this, new File(FILE_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
