A. SOLID Documentation

• SRP: BookRepository - responsible CRUD operations with books, doesn't include business logics.
BookService - responsible only for Business logics: validation, fine calculating, check for validation.
BookController - responsible only for input/output and sends information to the service.

• OCP: BookBase - abstract class, easily deals with new (EBook, PrintedBook) without changing existing classes.

• LSP - All methods works with BookBase and with any subclasses

• ISP Valid and Fine interfaces specialized exactly for their functions, classes implements only interfaces that are needed

• DIP BookService depends on repository

B. Advanced OOP Features
Must include short explanations of where you used:
• Generics in SortingUtils
• Lambdas Sorting and filtering in SortingUtils
• Reflection ReflectionUtils.inspectClass(PrintedBook.class) outputs rows, methods and type of classes
• Interface default/static methods Default method:validateOrThrow() in Valid checks if book is valid; Static method: Valid.alwaysTrue() demonstrates static metod of interface
C. OOP Documentation
• Abstract class + subclasses: BookBase + EBook, PrintedBook
• Composition relationships: Printed Book -> Author example of composition
• Polymorphism examples: List<BookBase> involves object of both Ebook and PrintedBook types
• UML diagram (updated):


D. Database Section
• Schema: CREATE TABLE authors (
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



• Constraints: NOT NULL to crucial rows and Foreign key for connection between author and their book
• Sample inserts: INSERT INTO authors (name) VALUES ('J.K. Rowling');
INSERT INTO authors (name) VALUES ('Frank Herbert');

INSERT INTO books (name, type, price, author_id) VALUES
('Harry Potter', 'PRINTED', 25.50, 1),
('Dune', 'PRINTED', 19.99, 2),
('Effective Java', 'EBOOK', 15.99, NULL);

E. Architecture Explanation
• Controller, service, repository roles:
Controller - sends operations to service, doesn't include business logics
Service - apllies business logics(validation, fine calculation etc) and calls repository through interface
Repository - CRUD operations with BD, none of business logics
• Examples of request/response behavior: Main -> Controller.addBook(book) -> Service.validateOrThrow() -> Repository.create(book)

F. Execution Instructions
• How to compile and run: javac -d out $(find src -name "*.java")
java -cp out Main

• Requirements (Java version, DB connection): java 17+ in DatabaseConnection.java place own connection
G. Screenshots
Show:<img width="388" height="658" alt="Снимок экрана 2026-02-04 224647" src="https://github.com/user-attachments/assets/847ced06-3c41-4999-bf8d-bd929e4debba" /><img width="866" height="666" alt="Снимок экрана 2026-02-04 225322" src="https://github.com/user-attachments/assets/88bc62f2-0d8a-48a3-bd10-fcf8ee5fa2c2" /><img width="936" height="798" alt="Снимок экрана 2026-02-04 225335" src="https://github.com/user-attachments/assets/026a41de-9534-4e33-88f1-fc53856b9b35" />


H. Reflection
• What you learned: How to u
se Reflection to analyze classes, how Lambda simplifies sorting and filtrating.
• Challenges: At the first time i didn't really understood how to combine interfaces with abstract classes for SOLID operations
• Value of SOLID architecture: Clear architecture simplifies everything
