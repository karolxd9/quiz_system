package com.example.quiz_system;
import java.lang.StringBuilder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import com.github.javafaker.Faker;

/**
 * Klasa umożliwiająca generację danych do bazy danych
 */
public class DataGenerator {
    private Faker faker;

    public DataGenerator(){
        this.faker = new Faker(new Locale("pl-PL"));
    }

    /**
     * Generacja dla encji Użytkownik
     * @param n liczba rekordów do generacji
     * @return wygenerowane dane
     */
    public StringBuilder generateUsers(int n){
        StringBuilder records = new StringBuilder();
        String oneRecord = "";
        for(int i=0; i<n; i++){
            oneRecord = this.faker.name().firstName() + ","+this.faker.name().firstName()+","+this.faker.name().lastName()+";";
            records.append(oneRecord);
            oneRecord = "";
        }
        return records;
    }

    /**
     * Funkcja operacyjna do uzyskania największego(najmniejszego) numeru ID w tabeli
     * @param isMax czy szukamy największej wartości false - najmniejsza true - największa
     * @param column nazwa kolimny
     * @param table nazwa tabeli
     * @return wartość największego ID w tabeli
     * @throws SQLException
     * @throws NullPointerException
     */
    public long getID(boolean isMax,String column, String table) throws SQLException,NullPointerException {
        long IndexUser = -1;
        String query = "SELECT " + column +" FROM "+table;
        try{
            if(isMax == true) {
                query = "SELECT " + column +" FROM "+ table + " ORDER BY "+ column +" DESC";
            }

            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet queryResult = queryExecutor.executeSelect(query);
            queryResult.next();
            IndexUser = queryResult.getLong("user_id");

        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        return IndexUser;
    }

    /**
     * Sprawdza unikalność danej kolumny
     * @param column nazwa kolumny
     * @param table nazwa tabeli
     * @param username nazwa loginu
     * @return
     */
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