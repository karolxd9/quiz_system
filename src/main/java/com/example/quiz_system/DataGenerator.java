package com.example.quiz_system;
import java.lang.StringBuilder;
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
    


}
