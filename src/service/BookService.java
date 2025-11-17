package service;

import dao.BookDao;
import model.Book;

import java.util.List;

public class BookService {
    private final BookDao dao;

    public BookService(BookDao dao) { this.dao = dao; }

    public void importFromJson(String path) throws Exception { dao.importFromJson(path); }
    public List<Book> getAllBooks() throws Exception { return dao.getAllBooks(); }
    public List<Book> getBooksSortedByYear() throws Exception { return dao.getBooksSortedByYear(); }
    public List<Book> getBooksBefore2000() throws Exception { return dao.getBooksBefore2000(); }
    public void dropTables() throws Exception { dao.dropTables(); }
}