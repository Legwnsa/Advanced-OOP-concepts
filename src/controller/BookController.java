package controller;

import model.BookBase;
import model.EBook;
import service.BookService;

import java.util.List;

public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }
    public List<BookBase> listBooks() {
        return service.getBooks();
    }
    public void addBook(BookBase book) {
        service.addBook(book);
    }
    public void removeBook(int id) {
        service.deleteBook(id);
    }
    public void updateBook(int id, EBook a) {
        service.updateBook(id, a);
    }

    public List<BookBase> getBooksSortedByName() {
        return service.getBooksSortedByName();
    }

    public List<BookBase> filterValidBooks(List<BookBase> all) {
        return service.getValidBooks();
    }

    public BookBase getBookById(int i) {
        return service.getBookById(i);
    }

    public double calculateFine(int id) {
        return service.calculateFine(id);
    }
}
