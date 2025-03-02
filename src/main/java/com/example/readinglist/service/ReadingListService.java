package com.example.readinglist.service;

import com.example.readinglist.model.Book;
import com.example.readinglist.dao.DatabaseDAO;
import com.example.readinglist.dao.ReadingListDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ReadingListService {

    private final ReadingListDAO readingListDAO = new DatabaseDAO();


    public void listBooks(HttpServletRequest request, HttpServletResponse response) {
        try {
            var bookList = readingListDAO.getAll();
            request.setAttribute("bookList", bookList);
            var dispatcher = request.getRequestDispatcher("reading-list.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error during list: {}", e.getMessage());
        }
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            var dispatcher = request.getRequestDispatcher("form.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error during show new form: {}", e.getMessage());

        }
    }

    public void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            var id = Long.valueOf(request.getParameter("id"));
            var existingBook = readingListDAO.getById(id);
            var dispatcher = request.getRequestDispatcher("form.jsp");
            request.setAttribute("book", existingBook);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Error during show edit form: {}", e.getMessage());

        }
    }

    public void insertBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            var newBook = Book.builder()
                    .title(request.getParameter("title"))
                    .author(request.getParameter("author"))
                    .genre(Book.Genre.valueOf(request.getParameter("genre")))
                    .mark(Integer.parseInt(request.getParameter("mark")))
                    .comment(request.getParameter("comment"))
                    .build();
            if (newBook.isValid()) {
                readingListDAO.add(newBook);
                response.sendRedirect("list");
            } else {
                var dispatcher = request.getRequestDispatcher("form-invalid.jsp");
                dispatcher.forward(request, response);
            }

        } catch (IOException | NumberFormatException | ServletException e) {
            log.error("Error during insert: {}", e.getMessage());
        }
    }

    public void updateBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            var newBook = Book.builder()
                    .id(Long.valueOf(request.getParameter("id")))
                    .title(request.getParameter("title"))
                    .author(request.getParameter("author"))
                    .genre(Book.Genre.valueOf(request.getParameter("genre")))
                    .mark(Integer.parseInt(request.getParameter("mark")))
                    .comment(request.getParameter("comment").trim())
                    .build();
            if (newBook.isValid()) {
                readingListDAO.update(newBook);
            }
            response.sendRedirect("list");
            var id = Long.valueOf(request.getParameter("id"));
            var existingBook = readingListDAO.getById(id);
            var dispatcher = request.getRequestDispatcher("form-invalid.jsp");
            request.setAttribute("book", existingBook);
            dispatcher.forward(request, response);
        } catch (IOException | NumberFormatException | ServletException e) {
            log.error("Error during update: {}", e.getMessage());
        }

    }

    public void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            readingListDAO.remove(Long.valueOf(request.getParameter("id")));
            response.sendRedirect("list");
        } catch (IOException e) {
            log.error("Error during delete: {}", e.getMessage());
        }
    }

}
