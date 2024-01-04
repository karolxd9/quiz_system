package com.conf;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    public int countRows(ResultSet queryResult) throws SQLException {
        int count = 0;
        while(queryResult.next()){
            count++;
        }
        return count;
    }

    public boolean isUnique(String column, String table, String username) throws SQLException{
        int licznik = 0;
        String query = "SELECT "+ column + " FROM "+ table + " WHERE "+ column + " = "+ "'"+ username + "'";
        try{
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet rs = queryExecutor.executeSelect(query);
            while (rs.next()){
                rs.next();
                licznik++;
            }
        }
        catch(SQLException e){
            System.out.println("Błąd z bazą danych");
        }
        if(licznik == 0){
            return true;
        }
        return false;

    }
}
