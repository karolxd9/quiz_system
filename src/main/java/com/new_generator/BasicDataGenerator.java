package com.new_generator;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * Klasa umożliwiająca generowanie danych
 */
public abstract class BasicDataGenerator<T> {
    public T typeOfData; //rodzaj danych poddany do generowania
    private Faker faker; // genrator danych

    /**
     * Metoda genruje pojedyncze dane
     * @param typeOfData
     */
    public generateData(T typeOfData){
        this.faker = new Faker(new Locale("pl-PL"));
        this.typeOfData = typeOfData;
    }

}
