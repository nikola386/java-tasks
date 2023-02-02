package com.nikola.library;

import jakarta.xml.bind.annotation.*;

import java.math.BigDecimal;

@XmlRootElement(name = "book")
@XmlType(propOrder = { "id", "isbn", "title", "author", "publishedYear", "price" })
public class Book {
    private int id;
    private String isbn;
    private String title;
    private String author;
    private int publishedYear;
    private BigDecimal price;

    @XmlTransient
    private User borrower;

    private Book() {  // makes JAXB happy, will never be invoked
    }


    public Book(String isbn, String title, String author, int publishedYear, BigDecimal price) {
        if (isbn == null ||
                title == null ||
                price == null) {
            throw new IllegalArgumentException("Missing parameters");
        }

        this.isbn = isbn;
        this.title = title.trim();
        this.author = author.trim();
        this.publishedYear = publishedYear;
        this.price = price;
    }

    @XmlElement(name = "id")
    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    @XmlElement(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @XmlElement(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlElement(name = "publishedYear")
    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    @XmlElement(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return borrower != null;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        return "book: " + id + ", " + title + ", " + isbn + ", " + author + ", " + price;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Book) {
            Book b = (Book) obj;
            return id == b.getId() && isbn.equals(b.getIsbn());
        }
        return false;
    }
}
