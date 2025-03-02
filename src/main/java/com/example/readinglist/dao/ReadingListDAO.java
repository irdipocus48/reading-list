package com.example.readinglist.dao;

import com.example.readinglist.model.Book;

import java.util.List;

public interface ReadingListDAO {

    void add(Book book);

    List<Book> getAll();

    Book getById(Long id);

    void update(Book book);

    void remove(Long id);

}
