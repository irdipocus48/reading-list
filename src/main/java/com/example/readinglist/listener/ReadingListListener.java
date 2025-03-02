package com.example.readinglist.listener;

import com.example.readinglist.dao.DatabaseDAO;
import com.example.readinglist.dao.DatabaseFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebListener
public class ReadingListListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        var context = servletContextEvent.getServletContext();
        var readingListDAO = new DatabaseDAO();
        DatabaseFactory.establishConnection();
        context.setAttribute("readingListDAO", readingListDAO);
        log.info("Context initialized.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DatabaseFactory.closeConnection();
        log.info("Context destroyed.");
    }

}
