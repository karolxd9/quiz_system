package com.example.quiz_system;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExecutor {
    public ResultSet executeSelect(String selectQuery){
        try {
            Connection connection = DBConnector.connect();
            Statement statemant = connection.createStatement();
            return statemant.executeQuery(selectQuery);
        }
        catch(SQLException e) {
            throw new RuntimeException("Błąd zapytania");
        }

    }

    public void executeQuery(String query){
        try{
            Connection connection = DBConnector.connect();
            Statement statemant = connection.createStatement();
            statemant.execute(query);
        }
        catch(SQLException e) {
            throw new RuntimeException("Błąd zapytania");
        }

    }
}
