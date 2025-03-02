package com.example.readinglist.controller;

import com.example.readinglist.dao.DatabaseFactory;
import com.example.readinglist.service.ReadingListService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebServlet
public class ReadingListController extends HttpServlet {

    private final ReadingListService readingListService = new ReadingListService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();

        try {
            log.info("Operation: {}", action);
            switch (action) {
                case "/new" -> readingListService.showNewForm(request, response);
                case "/insert" -> readingListService.insertBook(request, response);
                case "/delete" -> readingListService.deleteBook(request, response);
                case "/edit" -> readingListService.showEditForm(request, response);
                case "/update" -> readingListService.updateBook(request, response);
                default -> readingListService.listBooks(request, response);
            }
        } catch (Exception e) {
           log.error("Error during GET request: {}", e.getMessage());
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }

    @Override
    public void destroy() {
        DatabaseFactory.closeConnection();
    }
}
