package com.example.readinglist.service;

import com.example.readinglist.Book;
import com.example.readinglist.dao.DatabaseDAO;
import com.example.readinglist.dao.ReadingListDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReadingListService {

    private final ReadingListDAO readingListDAO = new DatabaseDAO();


    public void listBooks(HttpServletRequest request, HttpServletResponse response) {
        try {
            var bookList = readingListDAO.getAll();
            request.setAttribute("bookList", bookList);
            var dispatcher = request.getRequestDispatcher("reading-list.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public void showNewForm(HttpServletRequest request, HttpServletResponse response) {
        try {
            var dispatcher = request.getRequestDispatcher("form.jsp");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
                try {
                    var dispatcher = request.getRequestDispatcher("form-invalid.jsp");
                    dispatcher.forward(request, response);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (NumberFormatException e2) {
            e2.printStackTrace();

            try {
                var dispatcher = request.getRequestDispatcher("form-invalid.jsp");
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
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
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        try {
            var id = Long.valueOf(request.getParameter("id"));
            var existingBook = readingListDAO.getById(id);
            var dispatcher = request.getRequestDispatcher("form-invalid.jsp");
            request.setAttribute("book", existingBook);
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }

    }

    public void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        try {
            readingListDAO.remove(Long.valueOf(request.getParameter("id")));
            response.sendRedirect("list");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
