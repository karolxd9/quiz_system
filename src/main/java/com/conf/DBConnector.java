package com.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Klasa umożliwiająca łączenie z bazą danych
 */
public class DBConnector {
    private static String URL = "jdbc:mysql://127.0.0.1/platforma_testowa?autoReconnect=true&autoReconnectForPools=true";
    private static String USER = "root";
    private static String PASS = "";

    /**
     * Łączenie z bazą danych
     * @return zwraca ustanowione połączenie
     */
    public static Connection connect(){
        Connection connection = null;
        try {
           connection = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Udało się");
        }
        catch(SQLException e){
            e.printStackTrace();
            System.out.println("Bład");
        }

        return connection;
    }

}
