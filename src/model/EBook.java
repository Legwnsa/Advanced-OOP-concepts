package model;
import exception.InvalidInputException;
import interfaces.Fine;
import interfaces.Valid;

public class EBook extends BookBase implements Valid, Fine {
    private double price;
    private Author author;

    public EBook(int id, String name, double price) {
        super(id, name);
        if (price <= 0) throw new InvalidInputException("Price must be positive");
        this.price = price;
    }



    @Override
    public double fine() {
        return 0;
    }

    public void setAuthor(Author author) {
        if (author == null) throw new InvalidInputException("Author cannot be null");
        this.author = author;
    }

    public Author getAuthor() {
        return author;
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
        return "Ebook";
    }

    @Override
    public boolean valid() {
        return getName() != null && !getName().trim().isEmpty() && price > 0;
    }
}