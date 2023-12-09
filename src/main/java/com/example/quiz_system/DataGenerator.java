package com.example.quiz_system;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;

import com.conf.QueryExecutor;
import com.github.javafaker.Faker;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

/**
 * Klasa umożliwiająca generację danych do bazy danych
 */
public class DataGenerator {
    private Faker faker;

    public DataGenerator(){
        this.faker = new Faker(new Locale("pl-PL"));
    }

    /**
     * Generacja rekordu dla tabeli użytkownika
     * @return wygenerowany rekord dla tabeli użytkownika
     */
    protected String generateUser(){
        String oneRecord = "";
        oneRecord = this.faker.name().firstName() + ","+this.faker.name().firstName()+","+this.faker.name().lastName()+";";
        return oneRecord;
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
    protected static long getID(boolean isMax,String column, String table) throws SQLException,NullPointerException {
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

    /**
     * Generacja pojedynczego rekordu w tabeli z danymi logowania
     * @param id Numer identyfikacyjny użytkownika
     * @return
     */
    protected String generateUserLogin(int id){
        String oneRecord = "";
        String password = this.faker.internet().password(10,20);
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(password, Charsets.UTF_8);
        HashCode sha256 = hasher.hash();
        String sha256Text = sha256.toString();
        oneRecord = oneRecord + id + "," + this.faker.name().username() + "," + sha256Text + ";";
        return oneRecord;
    }

    /**
     * Przykładowa nazwa quizu
     * @return nazwa quizu w języku polsim
     */
    protected String quizName(){
        return this.faker.app().name();
    }

    protected int certificationPoints(){
        Random generatedNumber = new Random();
        int Number = generatedNumber.nextInt(1000) + 100;
        return Number;
    }

}