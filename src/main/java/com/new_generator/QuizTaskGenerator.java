package com.new_generator;

public class QuizTaskGenerator extends BasicDataGenerator{
    
    public String generate(int quizID,String nameOfQuiz){
        String question = getFaker().lorem().sentence();
        return question;
    }

}
