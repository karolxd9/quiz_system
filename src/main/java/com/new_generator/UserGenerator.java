package com.new_generator;

import java.util.concurrent.Callable;

/**
 * Klasa do generacji danych użytkownika
 */
public class UserGenerator extends BasicDataGenerator{
    public UserGenerator(Object typeOfData) {
        super(typeOfData);
    }

    public String generate(){
        String oneRecord = "";
        oneRecord = getFaker().name().firstName() + ","+getFaker().name().firstName()+","+getFaker().name().lastName()+";";
        return oneRecord;
    }
}
