package com.example.readinglist.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


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
                    System.out.println("Connection established.");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Connection error.");
            e.printStackTrace();
        }
    }


    public static void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
            if (connection.isClosed()) {
                System.out.println("Connection destroyed.!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


