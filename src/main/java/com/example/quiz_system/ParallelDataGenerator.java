package com.example.quiz_system;

import com.example.quiz_system.DataGenerator;

import java.sql.SQLException;
import java.util.concurrent.Callable;



public class ParallelDataGenerator extends DataGenerator implements Callable<Boolean>{
    private long minUserID;
    private  long maxUserID;


    public ParallelDataGenerator() throws SQLException {
        this.minUserID = DataGenerator.getID(false,"user_id","user");
        this.maxUserID = DataGenerator.getID(true,"user_id","user");
    }



    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
