package com.quiz;

import com.conf.GlobalSettings;
import com.db.DMLHandler;

import java.util.ArrayList;

public class Option {

    private int quiz_id;
    private String content;
    private int taskID;
    private boolean isCorrect;

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

    public int getTaskID(){
        return taskID;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public void addOptionToDB(){
        String query = "INSERT INTO option('task_id','quiz_id','isCorrect','content') VALUES(";
        String taskIDQuery = "'"+this.getTaskID()+"',";
        String quizIDQuery = "'"+this.getQuiz_id()+"',";
        String correctionQuery = "'"+this.getIsCorrect()+"',";
        String contentQuery = "'"+this.getContent()+"')";
        query = query + taskIDQuery + quizIDQuery + contentQuery + contentQuery;
        ArrayList<String>list = new ArrayList<>();
        list.add(query);
        DMLHandler dmlHandler = new DMLHandler(GlobalSettings.socket,list);
        dmlHandler.run();
    }
}
