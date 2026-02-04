import controller.BookController;
import interfaces.Valid;
import model.Author;
import model.EBook;
import model.PrintedBook;
import model.BookBase;
import repository.BookRepository;
import service.BookService;
import utils.ReflectionUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();
        BookService service = new BookService(repo);
        BookController controller = new BookController(service);


        Author herbert = new Author("Frank Herbert", 1);
        Author rowling = new Author("J.K. Rowling", 2);

        EBook ebook1 = new EBook(0, "Invisible Man", 9.99);
        EBook ebook2 = new EBook(0, "Effective Java", 15.99);

        PrintedBook printed1 = new PrintedBook(0, "Dune", 19.99, herbert);
        PrintedBook printed2 = new PrintedBook(0, "Harry Potter", 25.5, rowling);

        System.out.println("ebook1 valid? " + ebook1.valid());
        controller.addBook(ebook1);
        controller.addBook(ebook2);
        controller.addBook(printed1);
        controller.addBook(printed2);

        List<BookBase> allBooks = controller.listBooks();
        System.out.println("Все книги в базе:");
        for (BookBase b : allBooks) {
            System.out.println(b.Info() + " | Type: " + b.type());
        }

        try {
            BookBase book = controller.getBookById(1);
            System.out.println("\nКнига с ID 1: " + book.Info());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ebook1.setPrice(11.99);
            controller.updateBook(ebook1.getId(), ebook1);
            System.out.println("\nКнига обновлена: " + controller.getBookById(ebook1.getId()).Info());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            controller.removeBook(ebook2.getId());
            System.out.println("\nКнига удалена: " + ebook2.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nРасчёт штрафов:");
        for (BookBase b : controller.listBooks()) {
            if (b instanceof PrintedBook) {
                System.out.println(b.getName() + " fine: " + controller.calculateFine(b.getId()));
            }
        }

        System.out.println("\n___________________\n Книги Ровлинг:");
        for (BookBase b : controller.listBooks()) {
            if (b instanceof PrintedBook && ((PrintedBook) b).getAuthor().getId() == rowling.getId()) {
                System.out.println(b.getName());
            }
        }

        List<BookBase> sortedByName = controller.getBooksSortedByName();
        sortedByName.forEach(book -> System.out.println(book.Info()));

        List<BookBase> validBooks = controller.filterValidBooks(repo.getAll());
        System.out.println("Valid books: " + validBooks.size());

        ReflectionUtils.inspectClass(PrintedBook.class);

        if (Valid.alwaysTrue()) {
            System.out.println("Static method works!");
        }


    }
}