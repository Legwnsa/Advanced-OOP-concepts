import model.Author;
import model.EBook;
import model.PrintedBook;
import model.BookBase;
import repository.BookRepository;
import service.BookService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookRepository repo = new BookRepository();
        BookService service = new BookService(repo);

        Author herbert = new Author("Frank Herbert", 1);
        Author rowling = new Author("J.K. Rowling", 2);

        EBook ebook1 = new EBook(0, "Invisible Man", 9.99);
        EBook ebook2 = new EBook(0, "Effective Java", 15.99);

        PrintedBook printed1 = new PrintedBook(0, "Dune", 19.99, herbert);
        PrintedBook printed2 = new PrintedBook(0, "Harry Potter", 25.5, rowling);

        try {
            service.addBook(ebook1);
            service.addBook(ebook2);
            service.addBook(printed1);
            service.addBook(printed2);
        } catch (Exception e) {
            System.out.println("Ошибка добавления: " + e.getMessage());
        }

        List<BookBase> allBooks = service.getBooks();
        System.out.println("Все книги в базе:");
        for (BookBase b : allBooks) {
            System.out.println(b.Info() + " | Type: " + b.type());
        }

        try {
            BookBase book = service.getBookById(1);
            System.out.println("\nКнига с ID 1: " + book.Info());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            ebook1.setPrice(11.99);
            service.updateBook(ebook1.getId(), ebook1);
            System.out.println("\nКнига обновлена: " + service.getBookById(ebook1.getId()).Info());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            service.deleteBook(ebook2.getId());
            System.out.println("\nКнига удалена: " + ebook2.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nРасчёт штрафов:");
        for (BookBase b : service.getBooks()) {
            if (b instanceof PrintedBook) {
                System.out.println(b.getName() + " fine: " + service.calculateFine(b.getId()));
            }
        }

        System.out.println("\n___________________\n Книги Ровлинг:");
        for (BookBase b : service.getBooks()) {
            if (b instanceof PrintedBook && ((PrintedBook) b).getAuthor().getId() == rowling.getId()) {
                System.out.println(b.getName());
            }
        }

    }
}
