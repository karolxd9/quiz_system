package com.conf;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Klasa umożliwiająca łączenie z bazą danych
 */
public class DBConnector {
    private static String URL = "jdbc:mysql://h28.seohost.pl";
    private static String USER = "srv63119_platforma_testowa";
    private static String PASS = "root";

    /**
     * Łączenie z bazą danych
     * @return zwraca ustanowione połączenie
     */
    public static Connection connect(){
        // Zarejestruj sterownik JDBC
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Nie można znaleźć sterownika JDBC.");
            e.printStackTrace();
        }

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
