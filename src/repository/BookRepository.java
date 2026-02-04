package repository;

import model.BookBase;
import model.EBook;
import model.PrintedBook;
import model.Author;
import exception.DatabaseOperationException;
import utils.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepository {
    public void create(BookBase b) {
        String sql = "INSERT INTO books (name, type, price, author_id) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, b.getName());
            ps.setString(2, b.type());

            if (b instanceof EBook e) {
                ps.setDouble(3, e.getPrice());
                ps.setNull(4, Types.INTEGER);
            } else if (b instanceof PrintedBook p) {
                ps.setDouble(3, p.getPrice());
                ps.setInt(4, p.getAuthor().getId());
            }

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                b.setId(rs.getInt(1)); // <-- обновляем id после вставки
            }

        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to create entity", e);
        }
    }
    public List<BookBase> getAll() {
        List<BookBase> result = new ArrayList<>();
        String sql = """
        SELECT b.id, b.name, b.type, b.price, a.id AS author_id, a.name AS author_name
        FROM books b
        LEFT JOIN authors a ON b.author_id = a.id
    """;

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String type = rs.getString("type").toUpperCase();
                if (type.equalsIgnoreCase("EBOOK")) {
                    result.add(new EBook(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
                }else if (type.equalsIgnoreCase("PRINTED")) {
                    Author author = new Author(
                            rs.getString("author_name"),
                            rs.getInt("author_id")
                                        );
                    result.add(new PrintedBook(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getDouble("price"),
                            author));
                }
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Can't print out all", e);
        }
        return result;
    }
    public BookBase getById(int id) {
        String sql = """
            SELECT b.id, b.name, b.type, b.price, a.id AS author_id, a.name AS author_name
            FROM books b
            LEFT JOIN authors a ON b.author_id = a.id
            WHERE b.id = ?
        """;

        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                String type = rs.getString("type");
                if (type.equalsIgnoreCase("Ebook")) {
                    rs.getInt("id");
                    rs.getString("name");
                    rs.getDouble("price");
                } else if (type.equalsIgnoreCase("Printed")) {
                    Author author = new Author(
                            rs.getString("author_name"),
                            rs.getInt("author_id")
                    );
                    return new PrintedBook(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"), author);
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to get by id");
        }
    }
    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection();PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("FAiled to delete");
        }
    }

    public void update(int id, BookBase b) {
        String sql = "UPDATE books SET name = ?, type = ?, price = ?, author_id = ? WHERE id = ?";
        try (Connection con = DatabaseConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, b.getName());
            ps.setString(2, b.type());
            ps.setDouble(3, b.getPrice());

            if (b instanceof EBook) {
                ps.setNull(4, Types.INTEGER);
            }else if (b instanceof PrintedBook p) {
                ps.setInt(4, p.getAuthor().getId());
            }

            ps.setInt(5, id);
            int rows = ps.executeUpdate();

            if (rows == 0 ) {
                throw new DatabaseOperationException("Book with that id not found");
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Failed to update", e);
        }
    }
}