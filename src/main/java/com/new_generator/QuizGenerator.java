package com.new_generator;

public class QuizGenerator extends BasicDataGenerator{



    /**
     * Generacja nazw quizów(w tym przypadku z nazwami superbohaterów)
     * @return nazwa quizu
     */
    public String generate(){
        return getFaker().superhero().name();
    }
}
