package com.new_generator;

import com.conf.QueryExecutor;
import com.github.javafaker.Faker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Klasa dająca podstawę do generowanie danych
 */
public abstract class BasicDataGenerator<V,T> {

    private Faker faker; // genrator danych


    public BasicDataGenerator(){
        this.faker = new Faker(new Locale("pl-PL"));
    }

    /**
     * Pobranie instancji generatora
     * @return generator
     */
    public Faker getFaker() {
        return faker;
    }

    /**
     * Metoda genruje pojedyncze wiersze danych
     * @return pojedynczy rekord
     */
    public T generate() {
        return null;
    }

    /**
     * Sprawdza czy dana wartość pojawia się w relacji
     * @param table Nazwa tabeli
     * @param column Nazwa atrybutu
     * @param value Wartość
     * @return
     */
    protected boolean checkUnique(String table,String column,String value){
        int licznik = 0;
        String query = "SELECT "+ column + " FROM "+ table + " WHERE "+ column + " = "+ "'"+ (T)(value) + "'";
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

    /**
     * Znajdywanie ekstremum globalnego w danej kolumnie
     * @param isMax czy ma to być największa wartość w kolumnie
     * @param column nazwa kolumny
     * @param table nazwa tabeli
     * @return wartość maksymalna(minimalna)
     */
    protected String getExtremeColumn(boolean isMax, String column, String table){
        String query = "SELECT MAX("+column+")"+" FROM "+table;
        String returnValue = "";
        try{
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet queryResult = queryExecutor.executeSelect(query);
            queryResult.next();
            returnValue = (String)(queryResult.getObject(column));
        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }

        return returnValue;

    }


}
