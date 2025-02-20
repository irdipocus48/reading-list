package com.example.readinglist.listener;

import com.example.readinglist.dao.DatabaseDAO;
import com.example.readinglist.dao.DatabaseFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;


public class ReadingListListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        var context = servletContextEvent.getServletContext();
        var readingListDAO = new DatabaseDAO();
        DatabaseFactory.establishConnection();
        context.setAttribute("readingListDAO", readingListDAO);
        System.out.println("Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DatabaseFactory.closeConnection();
        System.out.println("Context destroyed.");
    }

}
