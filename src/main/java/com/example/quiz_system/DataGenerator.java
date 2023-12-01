package com.example.quiz_system;
import java.lang.StringBuilder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import com.github.javafaker.Faker;

/**
 * Klasa umożliwiająca generację danych do bazy danych
 */
public class DataGenerator {
    private Faker faker;
    private long maxIndexUser;

    public DataGenerator(){
        this.faker = new Faker(new Locale("pl-PL"));
        this.maxIndexUser = -1;
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

    public long getMaxIndexUser() throws SQLException,NullPointerException {
        DBConnector.connect();
        long maxIndexUser = -1;
        try{
            String query = "SELECT MAX(user_id) FROM user";
            DBConnector.connect();
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet queryResult = queryExecutor.executeSelect(query);
            queryResult.next();
            maxIndexUser = queryResult.getInt("user_id");

        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }

        return maxIndexUser;
    }
}
