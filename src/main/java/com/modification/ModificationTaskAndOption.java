package com.modification;

import com.conf.GlobalSettings;
import com.db.DMLHandler;

import java.util.ArrayList;

public class ModificationTaskAndOption {
    private DMLHandler modificationHandler; //Obsługa DML

    /**
     * Usunięcie odpowiedzi
     * @param optionID identryfikator odpowiedzi
     */
    public void deleteOption(int optionID){
        ArrayList<String> list = new ArrayList<>();
        String query = "DELETE FROM option WHERE option_id = "+optionID;
        list.add(query);
        this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
        this.modificationHandler.run();
    }

    /**
     * Usunięcie zadania
     * @param task_id identyfikator zadania
     */
    public void deleteTask(int task_id){
        ArrayList<String> list = new ArrayList<>();
        String queryOption = "DELETE FROM option WHERE task_id = "+task_id;
        String queryTask = "DELETE FROM task WHERE task_id = "+task_id;
        list.add(queryOption);
        list.add(queryTask);
        this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
        this.modificationHandler.run();
    }

    /**
     * Zmiana przynależności quizu do zadania
     * @param taskID identyfikator zadania
     * @param quizID identyfikator quizu
     */
    public void changeQuizIDForTask(int taskID, int quizID){
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE task quiz_id = "+quizID+" WHERE taskID = "+taskID;
        list.add(query);
        this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
        this.modificationHandler.run();
    }

    /**
     * Zmiana nazwy zadania
     * @param task_id identyfikator zadania
     * @param taskName nazwa zadania
     */
    public void changeTaskName(int task_id,String taskName){
        ArrayList<String>list = new ArrayList<>();
        String query = "UPDATE task SET name = "+"'"+taskName+"' WHERE task_id = "+task_id;
        list.add(query);
        this.modificationHandler = new DMLHandler(GlobalSettings.socket,list);
        this.modificationHandler.run();
    }


}
