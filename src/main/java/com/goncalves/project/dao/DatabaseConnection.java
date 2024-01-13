package com.goncalves.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    public static Connection connect() {
        if (connection == null) {
            try {
                // Connect to your database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/epos", "root", "admin");
                System.out.println("Successfully connected to the database.");
            } catch (SQLException e) {
                System.out.println("An error occurred while connecting to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void close() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("An error occurred while closing the database connection.");
                e.printStackTrace();
            }
        }
    }
}
