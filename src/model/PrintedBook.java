package model;
import exception.InvalidInputException;
import interfaces.Fine;
import interfaces.Valid;

public class PrintedBook extends BookBase implements Valid, Fine {
    private double price;
    private Author author;

    public PrintedBook(int id, String name, double price, Author author) {
        super(id, name);
        if (price <= 0) throw new IllegalArgumentException("Price must be positive");
        if (author == null) throw new IllegalArgumentException("Author cannot be null");
        this.price = price;
        this.author = author;
    }


    @Override
    public double fine() {
        return 5.5;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) throw new InvalidInputException("Price must be positive");
        this.price = price;
    }

    @Override
    public String type() {
        return "Printed book";
    }

    public void setAuthor(Author author) {
        if (author == null) throw new InvalidInputException("Author cannot be null");
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean valid() {
        return getName() != null && !getName().trim().isEmpty() && price > 0 && author != null;
    }
}