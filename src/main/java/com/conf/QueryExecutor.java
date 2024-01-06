package com.conf;

import db.ClientHandler;

import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Klasa umożliwiająca wykonywanie zapytań
 */
public class QueryExecutor {

    /**
     * Wykonywanie selekcji(zapytań SELECT)
     * @param selectQuery nasze zapytanie select
     * @return wykonane zapytanie selekcji
     */
    public ResultSet executeSelect(String selectQuery) throws RuntimeException{
        try {
            Connection connection = DBConnector.connect();
            Statement statemant = connection.createStatement();
            return statemant.executeQuery(selectQuery);
        }
        catch(SQLException e) {
            throw new RuntimeException("Błąd zapytania");
        }
    }

    /**
     * Wykonywanie zapytań modyfikujących stan bazy danych(UPDATE,INSERT,DELETE)
     * @param query zapytanie(inne niż SELECT)
     */
    public void executeQuery(String query) throws RuntimeException{
        try{
            Connection connection = DBConnector.connect();
            Statement statemant = connection.createStatement();
            statemant.execute(query);
        }
        catch(SQLException e) {
            throw new RuntimeException("Błąd zapytania");
        }

    }

    /**
     * Liczy ilość wierszy po wykonaniu zapytania
     * @param queryResult liczba wierszy
     */
    public static int countRows(ResultSet queryResult) throws SQLException {
        int count = 0;
        while(queryResult.next()){
            count++;
        }
        return count;
    }

    /**
     *
     * @param column
     * @param table
     * @param username
     * @return
     * @throws SQLException
     */

    /**
     * Sprawdzenie czy dana wartość występuje w kolumnie
     * @param column nazwa kolumny
     * @param table nazwa tabeli
     * @param username wartość, której występowalność sprawdzamy w kolumnie danej tabeli
     * @return wartość czy dana wartośc występuje w kolumnie danej tabeli
     * @throws SQLException
     */
    public static boolean lackValue(String column, String table, String username,Socket socket) throws SQLException {
        int licznik = 0;
        String query = "SELECT "+ column + " FROM "+ table + " WHERE "+ column + " = "+ "'"+ username + "'";
        ResultSet resultSet = QueryExecutor.result(query,socket);
        licznik = QueryExecutor.countRows(resultSet);

        if(licznik == 0){
            return true;
        }
        return false;

    }

    /**
     * Przetwarzanie danego zapytania selekcji za pomocą klienta;
     * @param query zapytanie selekcji
     * @param socket gniazdo sieciowe do komunikacji
     * @return wynik zapytania selekcji
     */
    public static ResultSet result(String query,Socket socket){
        ClientHandler clientHandler = new ClientHandler(socket);
        clientHandler.addQuery(query);
        FutureTask task = new FutureTask<>(clientHandler);
        GlobalSettings.exec.execute(task);
        ResultSet resultSet = null;
        try {
            resultSet = (ResultSet) task.get();
        }
        catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        return resultSet;
    }
}
