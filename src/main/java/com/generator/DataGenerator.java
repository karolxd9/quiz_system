package com.generator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Random;

import com.conf.QueryExecutor;
import com.conf.SystemInfo;
import com.github.javafaker.Faker;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;

/**
 * Klasa umożliwiająca generację danych do bazy danych
 */
public class DataGenerator {
    private Faker faker; // genrator danych
    private int forUsers; //liczba użytkowników
    private SystemInfo info; //informacje o sprzęcie, na którym uruchomiona zostanie aplikacja
    private int recordPerThread; //liczba rekordów do wygenerowania w jednym wątku
    private int nThreads; //liczba wątków

    /*
        Liczba rekordów, które nie zostały wygenerowana
        współbieżnie(wynikiem każdego z wątku będzie taka sama liczba wyjątków,
        lecz reszta z podziału tego zasobu do wątku zostanie wykonana jednowątkowo)
     */
    private int leftRecords;

    public DataGenerator(int forUsers,SystemInfo info){
        this.faker = new Faker(new Locale("pl-PL"));
        this.forUsers = forUsers;
        this.info = info;
        this.nThreads = info.getNumberOfCore();
        this.recordPerThread = (this.forUsers) / (this.info.getNumberOfCore());
        if(this.recordPerThread == 0){
            this.leftRecords = this.forUsers;
        }
        else{
            this.leftRecords = this.forUsers % this.recordPerThread;
        }
    }

    /**
     * @return liczba użytkowników do wygenerowania
     */
    public int getForUsers(){
        return this.forUsers;
    }

    /**
     * @return informacje o sprzęcie
     */
    public SystemInfo getInfo(){
        return this.info;
    }

    /**
     * @return liczba rekordów do wygenerowania przez wątek
     */
    public int getRecordPerThread() {
        return this.recordPerThread;
    }

    /**
     * @return Liczba pozostałych wątków do wykonanie nierównoległego
     */
    public int getLeftRecords() {
        return this.leftRecords;
    }

    /**
     * Generacja rekordu dla tabeli użytkownika
     * @return wygenerowany rekord dla tabeli użytkownika d
     */
    public String generateUser(){
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
    public static long getID(boolean isMax,String column, String table) throws SQLException,NullPointerException {
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
    public String generateUserLogin(int id){
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
     * @return nazwa quizu w języku polskim
     */
    public String quizName(){
        return this.faker.app().name();
    }

    /**
     * Generacja potrzebnej liczbt punktów, aby otrzymać certyfikat
     * @return Number liczba punktów potrzebnych do uzyskania certyfikatu
     */
    public int certificationPoints(){
        Random generatedNumber = new Random();
        int Number = generatedNumber.nextInt(1000) + 100;
        return Number;
    }

}