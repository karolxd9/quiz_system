package com.example.quiz_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
    private static String URL = "jdbc:mysql://127.0.0.1/platforma_testowa?autoReconnect=true&autoReconnectForPools=true";
    private static String USER = "root";
    private static String PASS = "";

    public static Connection connect(){
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL, USER, PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return connection;
    }

}
