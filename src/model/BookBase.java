package model;

import exception.InvalidInputException;
import interfaces.Fine;
import interfaces.Valid;

public abstract class BookBase implements Fine, Valid {
    private int id;
    private String name;
    public BookBase(int id, String name) {
        this.id = id;
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be empty");
        }
        this.name = name;
    }
    public abstract String type();

    public abstract boolean valid();

    public abstract double getPrice();

    public String Info() {
        return "id: " + id + "; name: " + name;
    }

    public int getId() {return id;}

    public void setId(int a) {
        if (a<=0 ) throw new InvalidInputException("Id must be positive");
        id = a;
    }

    public String getName() {return name;}


    public void setName(String a) {
        if (a==null || a.trim().isEmpty()) throw new InvalidInputException("Book name cannot be empty");
        name = a;
    }

}
