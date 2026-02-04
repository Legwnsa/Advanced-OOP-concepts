package model;

import exception.InvalidInputException;

public class Author {
    private int id;
    private String name;
    public Author(String name, int id) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Author name cannot be empty");
        this.id = id;
        this.name = name;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setId(int a) {
        if (a <= 0) throw new InvalidInputException("Id can't be negative");
        id = a;
    }

    public void setName(String b) {
        if (b == null || b.trim().isEmpty()) throw new InvalidInputException("Author name can't be empty");
        name = b;
    }

    @Override
    public String toString() {
        return "Author name: " +name + "; Author id: " + id;
    }
}



