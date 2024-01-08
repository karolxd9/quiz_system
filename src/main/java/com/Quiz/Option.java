package com.Quiz;

public class Option {

    private int quiz_id;
    private String content;

    public Option(int quiz_id,String content){
        this.quiz_id = quiz_id;
        this.content = content;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public String getContent() {
        return content;
    }
}
