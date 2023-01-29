Make a simple Java application, that describes a library and consists of the following objects:
1)	Create class Book with the following properties : id, ISBN, title, author, published year, price.
2)	Create class User with the following properties : name, list of books. This class represents a library user that should be able to borrow a book, to return the book that he/she borrowed, to donate a book. To achieve that you will need to implement the corresponding methods:  borrowBook, returnBook, donateBook.
3)	Create class Library with the following methods: searchBook, addBook, lendBook, updateBook, deleteBook. Implement all corresponding operations like adding a book, searching for a book, lending a book, donating a book, updating/deleting a book.
4)	Library class should also store (read and write) the books into an XML file with the following structure:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<library>
	<book>
		<id>1</id>
		<isbn>978-1-56619-909-4</isbn>
		<title>Everyday Italian</title>
		<author>Giada De Laurentiis</author>
		<published_year>2005</published_year>
		<price>30.00</price>
	</book>
	<book>
		<id>2</id>
		<isbn>978-1-4028-9462-6</isbn>
		<title>Harry Potter</title>
		<author>J K. Rowling</author>
		<published_year>2005</published_year>
		<price>29.99</price>
	</book>
</library>
```