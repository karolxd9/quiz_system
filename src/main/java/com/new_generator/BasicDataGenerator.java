package com.new_generator;

import com.github.javafaker.Faker;

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
     * Metoda genruje pojedyncze wiersze danych
     *
     * @return
     */
    T generate() {
        return null;
    }


}
