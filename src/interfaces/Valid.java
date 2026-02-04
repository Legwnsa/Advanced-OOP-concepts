package interfaces;

import exception.InvalidInputException;

public interface Valid {
    boolean valid(); // абстрактный метод

    default void validateOrThrow() { // default метод
        if (!valid()) {
            throw new InvalidInputException("Validation failed");
        }
    }

    static boolean alwaysTrue() { // static метод
        return true;
    }
}
