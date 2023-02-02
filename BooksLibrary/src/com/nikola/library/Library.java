package com.nikola.library;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement(name = "library")
public class Library {
    protected ArrayList<User> users;
    protected ArrayList<Book> books;

    public Library() {
        users = new ArrayList<>();
        books = new ArrayList<>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @XmlElement(name = "book")
    public ArrayList<Book> getBooks() {
        return books;
    }
}
