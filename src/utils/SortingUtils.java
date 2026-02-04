package utils;

import model.BookBase;

import java.awt.print.Book;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingUtils {
    public static List<BookBase> sortByName(List<BookBase> books) {
        return books.stream().sorted(Comparator.comparing(BookBase::getName)).collect(Collectors.toList());
    }

    public static List<BookBase> filterValidBooks(List<BookBase> books) {
        return books.stream().filter(BookBase::valid).collect(Collectors.toList());
    }

    public static List<BookBase> sortByPrice(List<BookBase> books) {
    return books.stream().sorted((b1, b2) -> Double.compare(b1.getPrice(), b2.getPrice())).collect(Collectors.toList());
    }
}
