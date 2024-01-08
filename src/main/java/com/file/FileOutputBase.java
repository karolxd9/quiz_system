package com.file;

import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import org.w3c.dom.Document;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Klasa ogólna za pomocą, która pomoże w generacji i tworzeniu pliku o danym rozszerzeniu
 */
public class FileOutputBase implements Runnable{

    private String name; //nazwa pliku
    private String path; //nazwa ścieżki, gdzie zapisać plik

    public FileOutputBase(String name,String path){
        this.name = name;
        this.path = path;
    }

    /**
     * Lista zadań z quizu
     * @param QuizID numer quizu
     * @return kolekcja zadań z danego quizu
     */
    public ArrayList<TaskFormat> getQuizesToArrayList(int QuizID){
        ArrayList<TaskFormat>results = new ArrayList<>();
        TaskFormat quiz = null;
        ResultSet tasks = QueryExecutor.result("SELECT * FROM task WHERE quiz_id = "+QuizID, GlobalSettings.socket);
        try{
            while(tasks.next()){
                String name = tasks.getString("name");
                String header = tasks.getString("header");
                String content = tasks.getString("contant");
                int max_point = tasks.getInt("max_points");
                quiz = new TaskFormat(header,name,content,max_point);
                results.add(quiz);
            }
        }catch(NullPointerException | SQLException e){
            e.printStackTrace();
        }
        return results;

    }

    /**
     * Możliwe odpowiedzi do quizu
     * @param QuizID numer quizu
     * @return kolekcja odowiedzi do danego quizu
     */
    public ArrayList<OptionFormat>getAnswersToArrayList(int QuizID){
        ArrayList<OptionFormat>answers = new ArrayList<>();
        try {
            OptionFormat optionFormat = null;
            ResultSet answersResult = QueryExecutor.result("SELECT * FROM option WHERE quiz_id = " + QuizID,GlobalSettings.socket);
            while (answersResult.next()) {
                String content = answersResult.getString("content");
            }
        }
        catch (NullPointerException | SQLException e){
            e.printStackTrace();
        }

        return answers;
    }

    @Override
    public void run() {
        
    }
}
