Library Management APi
Purpose:
Library Management API allows managing books, authors, and different types of books (EBook and PrintedBook) through console-based CRUD operations. The project demonstrates object-oriented programming principles, JDBC database operations, and exception handling.

Entities and Relationships:
BookBase (abstract)
Subclasses: EBook, PrintedBook
Fields: id, name, price, author (for PrintedBook), type
BookBase contains Author (composition)

Database Tables:
authors
books

Relationships:
One Author can have many PrintedBooks
EBooks have no authors

Abstract Class:
BookBase
Fields: id, name, price
Abstract methods: type(), fine(), valid()
Concrete methods: getters/setters for encapsulation

Subclasses:
1)EBook
Field: price, author, 
Implements type(), fine(), valid()
2)PrintedBook
Field: price, author
Implements type(), fine(), valid()

Interfaces:
Validatable have valid() ensures proper business rules
Composition / Aggregation:
PrintedBook has an Author

Polymorphism Example:

BookBase b1 = new EBook("Effective Java", 15.99);
BookBase b2 = new PrintedBook("Dune", 19.99, herbert);
System.out.println(b1.type()); // "EBOOK"
System.out.println(b2.type()); // "PRINTED"

Database Description
Schema:
CREATE TABLE authors (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE books (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(20) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    author_id INT,
    CONSTRAINT fk_author FOREIGN KEY(author_id) REFERENCES authors(id)
);

Sample Inserts:

INSERT INTO authors(name) VALUES ('Frank Herbert'), ('J.K. Rowling');

INSERT INTO books(name, type, price, author_id) VALUES
('Dune', 'PRINTED', 19.99, 1),
('Harry Potter', 'PRINTED', 25.50, 2),
('Effective Java', 'EBOOK', 15.99, NULL),
('Invisible Man', 'EBOOK', 9.99, NULL);

Controller / Main Demo
Creating Books and Authors:

Author herbert = new Author("Frank Herbert", 1);
Author rowling = new Author("J.K. Rowling", 2);

BookBase ebook = new EBook("Effective Java", 15.99);
BookBase printed = new PrintedBook("Dune", 19.99, herbert);

service.addBook(ebook);
service.addBook(printed);


CRUD Operations:

service.getBookById(1);
service.updateBook(1, updatedBook);
service.deleteBook(2);
service.getBooks().forEach(b -> System.out.println(b.Info()));


Validation Example:

BookBase invalid = new PrintedBook("", -5, herbert);
service.addBook(invalid); // throws InvalidInputException

Instructions to Compile and Run
Add PostgreSQL JDBC driver (postgresql-42.7.8.jar) to classpath.
Compile and run.

Screenshots:<img width="788" height="616" alt="image" src="https://github.com/user-attachments/assets/2f8d7ff1-910b-4484-be1d-02d0e60cc5d8" />


I learned: Working with JDBC, connections, and SQL queries, Implementing multi-layer architecture: Controller to Service to Repository, Applying OOP: abstraction, inheritance, interfaces, composition,Using polymorphism to handle EBooks and PrintedBooks through the same interface

