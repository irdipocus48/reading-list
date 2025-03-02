package com.example.readinglist.dao;


import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class DatabaseFactory {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5432/reading_list";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public static Connection connection;


    public static void establishConnection() {
        try {
            if (connection == null) {
                Class.forName(DB_DRIVER);
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                if (!connection.isClosed()) {
                    log.info("DB connection established.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
//            System.err.println("Connection error.");
            log.error("Error during DB connection establishing: {}", e.getMessage());
        }
    }


    public static void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            if (connection.isClosed()) {
                log.info("DB connection closed.");
            }
        } catch (SQLException e) {
            log.error("Error during DB connection closing: {}", e.getMessage());

        }
    }

}


