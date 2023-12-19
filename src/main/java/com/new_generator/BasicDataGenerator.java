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
    public V typeOfData; //rodzaj danych poddany do generowania
    private Faker faker; // genrator danych


    public BasicDataGenerator(V typeOfData){
        this.faker = new Faker(new Locale("pl-PL"));
        this.typeOfData = typeOfData;
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

    public boolean checkUnique(String table,String column,String value){
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


}
