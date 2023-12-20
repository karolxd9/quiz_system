package com.new_generator;

import java.util.*;

public class QuizUserGenerator extends BasicDataGenerator{
    /**
     * Generacja użytkowników biorących udział w quizie
     * @param quizID numer identyfikacyjny quizu
     * @param numberOfUser liczba użytkowników
     * @param minUser minimalna możliwa liczba identyfikatora użytkownik
     * @param maxUser maksymalna możliwa liczba identyfikatora użytkownik
     * @return wiersze do tabeli user_quiz
     */
    public String generate(int quizID,int numberOfUser,int minUser,int maxUser){
        Set<Integer>usersId = new HashSet<>();
        String record = "";
        int ID;
        while(usersId.size() == numberOfUser){
            ID = (int)(Math.random() % maxUser + minUser);
            usersId.add(ID);
        }
        List<Integer> usersIDList = new ArrayList();
        usersIDList = usersId.stream().toList();
        for(int i = 0; i < numberOfUser; i++){
            record = usersIDList.get(i) + ","+quizID+";";
        }
        return record;
    }
}
