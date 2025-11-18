package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Book;
import model.Visitor;
import util.DbUtil;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void createTables() throws Exception {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255) NOT NULL, " +
                    "author VARCHAR(255), " +
                    "publishingYear INT, " +
                    "isbn VARCHAR(50) UNIQUE, " +
                    "publisher VARCHAR(255))");

            stmt.execute("CREATE TABLE IF NOT EXISTS visitors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "surname VARCHAR(100), " +
                    "phone VARCHAR(50), " +
                    "subscribed BOOLEAN)");
        }
    }

    public void importFromJson(String path) throws Exception {
        createTables();
        Gson gson = new Gson();
        Type visitorListType = new TypeToken<List<Visitor>>() {}.getType();
        List<Visitor> visitors = gson.fromJson(new FileReader(path), visitorListType);

        try (Connection conn = DbUtil.getConnection()) {
            for (Visitor visitor : visitors) {
                insertVisitor(conn, visitor);
                if (visitor.getFavoriteBooks() != null) {
                    for (Book book : visitor.getFavoriteBooks()) {
                        insertBook(conn, book);
                    }
                }
            }
        }
    }

    private void insertVisitor(Connection conn, Visitor visitor) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "MERGE INTO visitors (name,surname,phone,subscribed) KEY(name,surname) VALUES (?,?,?,?)")) {
            ps.setString(1, visitor.getName());
            ps.setString(2, visitor.getSurname());
            ps.setString(3, visitor.getPhone());
            ps.setBoolean(4, visitor.isSubscribed());
            ps.executeUpdate();
        }
    }

    private void insertBook(Connection conn, Book book) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "MERGE INTO books (name,author,publishingYear,isbn,publisher) KEY(isbn) VALUES (?,?,?,?,?)")) {
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getPublishingYear());
            ps.setString(4, book.getIsbn());
            ps.setString(5, book.getPublisher());
            ps.executeUpdate();
        }
    }

    private Book mapBook(ResultSet rs) throws SQLException {
        Book b = new Book();
        b.setName(rs.getString("name"));
        b.setAuthor(rs.getString("author"));
        b.setPublishingYear(rs.getInt("publishingYear"));
        b.setIsbn(rs.getString("isbn"));
        b.setPublisher(rs.getString("publisher"));
        return b;
    }

    public List<Book> getAllBooks() throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * " + "FROM books ORDER BY id")) {
            while (rs.next()) {
                list.add(mapBook(rs));
            }
        }
        return list;
    }

    public List<Book> getBooksSortedByYear() throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * " + "FROM books ORDER BY publishingYear")) {
            while (rs.next()) {
                list.add(mapBook(rs));
            }
        }
        return list;
    }

    public List<Book> getBooksBefore2000() throws Exception {
        List<Book> list = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * " + "FROM books WHERE publishingYear < 2000");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(mapBook(rs));
            }
        }
        return list;
    }

    public void dropTables() throws Exception {
        try (Connection conn = DbUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS books");
            stmt.execute("DROP TABLE IF EXISTS visitors");
        }
    }
}