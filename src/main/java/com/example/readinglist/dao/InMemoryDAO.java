package com.example.readinglist.dao;

import com.example.readinglist.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryDAO implements ReadingListDAO {

    private static final Map<Long, Book> BOOK_BY_ID = new HashMap<>();

    static {
        long id1 = System.currentTimeMillis();
        long id2 = System.currentTimeMillis() + 3453;
        BOOK_BY_ID.put(id1, new Book(id1, "Alise in wnderland", "Jon Jason", Book.Genre.Detective, 4, "ewcfevc"));
        BOOK_BY_ID.put(id2, new Book(id2, "Biba and boba", "Ben Truman", Book.Genre.History, 3, "dfvdv"));
    }

    public void add(Book book) {
        var id = System.currentTimeMillis();
        book.setId(id);
        BOOK_BY_ID.put(id, book);
    }


    public List<Book> getAll() {
        return new ArrayList<>(BOOK_BY_ID.values());
    }


    public Book getById(Long id) {
        return BOOK_BY_ID.get(id);
    }


    public void update(Book book) {
        BOOK_BY_ID.replace(book.getId(), book);
    }


    public void remove(Long id) {
        BOOK_BY_ID.remove(id);
    }

}
