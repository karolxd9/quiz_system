
package com.conf;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/*
 * Klasa umożliwiająca wykonywanie zapytań
 */

public class QueryExecutor {
    private static Connection connection; // Zmienna do przechowywania połączenia

    public QueryExecutor() {
        connection = DBConnector.connect(); // Inicjalizacja połączenia w konstruktorze
    }

    public Connection getConnection() {
        return connection;
    }


/**
     * Wykonywanie selekcji (zapytań SELECT)
     *
     * @param selectQuery nasze zapytanie select
     * @return wykonane zapytanie selekcji
     */

    public ResultSet executeSelect(String selectQuery) throws RuntimeException {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(selectQuery);
        } catch (SQLException e) {
            throw new RuntimeException("Błąd zapytania SELECT: " + e.getMessage());
        }
    }


/**
     * Wykonywanie zapytań modyfikujących stan bazy danych (UPDATE, INSERT, DELETE)
     *
     * @param query zapytanie (inne niż SELECT)
     */

    public void executeQuery(String query) throws RuntimeException {
        try {
            Statement statement = connection.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException("Błąd zapytania: " + e.getMessage());
        }

    }


    /**
     * Wykonuje zapytanie SQL i zwraca wynik w postaci ResultSet
     *
     * @param query  zapytanie SQL
     * @param socket gniazdo sieciowe do komunikacji (jeśli jest potrzebne)
     * @return wynik zapytania jako ResultSet
     * @throws SQLException
     */
    public static ResultSet result(String query, Socket socket) throws SQLException{
        try{
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return statement.executeQuery(query);
        }catch (SQLException e){
            throw new SQLException("Błąd wykonania zapytania SQL: " + e.getMessage());
        }
    }


/**
     * Liczy ilość wierszy po wykonaniu zapytania
     *
     * @param queryResult wynik zapytania
     * @return liczba wierszy
     */

    public static int countRows(ResultSet queryResult) throws SQLException {
        int count = 0;
        try{
        while (queryResult.next()) {
            count++;
        }}
        catch (NullPointerException e){
            System.out.println("NullPointerException");
            return 0;
        }
        return count;
    }


/**
     * Sprawdzenie czy dana wartość występuje w kolumnie
     *
     * @param column   nazwa kolumny
     * @param table    nazwa tabeli
     * @param username wartość, której występowalność sprawdzamy w kolumnie danej tabeli
     * @param socket   gniazdo sieciowe do komunikacji
     * @return true, jeśli wartość nie występuje, false w przeciwnym razie
     * @throws SQLException
     */

    public static boolean lackValue(String column, String table, String username, Socket socket) throws SQLException {
        int count = 0;
        String query = "SELECT " + column + " FROM " + table + " WHERE " + column + " = '" + username + "'";
        ResultSet resultSet = result(query, socket);
        count = countRows(resultSet);

        return count == 0;
    }



}



