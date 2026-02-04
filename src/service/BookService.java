package service;

import exception.DuplicateResourceException;
import exception.InvalidInputException;
import exception.ResourceNotFoundException;
import interfaces.Valid;
import model.BookBase;
import model.EBook;
import model.PrintedBook;
import repository.BookRepository;
import utils.SortingUtils;

import java.util.List;

public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public void addBook(BookBase book) {
        book.validateOrThrow();
        if (!book.valid()) throw new InvalidInputException("Book is not valid");
        repo.create(book);
    }

    public List<BookBase> getBooks() {
        return repo.getAll();
    }

    public BookBase getBookById(int id) {
        BookBase b = repo.getById(id);
        if (b == null) throw new ResourceNotFoundException("Book not found with id: " + id);
        return b;
    }

    public double calculateFine(int id) {
        BookBase b = getBookById(id);
        double a = 0;
        if (b instanceof PrintedBook) { a = b.fine();}
        return a;
    }

    public void deleteBook(int id) {
        repo.delete(id);
    }

    public void updateBook(int id, BookBase book) {
        book.validateOrThrow();
        if (!book.valid()) throw new InvalidInputException("Book is not valid");
        repo.update(id, book);
    }

    public List<BookBase> getBooksSortedByName() {
        return SortingUtils.sortByName(repo.getAll());
    }
    public List<BookBase> getValidBooks() {
        return SortingUtils.filterValidBooks(repo.getAll());
    }

}

