package com.new_generator;

public class QuizTaskGenerator extends BasicDataGenerator{

    public String generate(int quizID,String nameOfQuiz,TypeOfTask type){
        String record=""; //cały wiersz
        String typeOfTask =""; //zmienna przechowująca rodzaj zadania
        switch(type){
            case OTWARTE:
                typeOfTask = "otwarte";
                break;
            case ZAMKNIETE:
                typeOfTask = "zamknięte";
                break;
            case WIELOKROTNE:
                typeOfTask = "zamknięte wielokrotnego wyboru";
        }
        record = quizID + "," + nameOfQuiz + "," + typeOfTask;
        return record;
    }

}
