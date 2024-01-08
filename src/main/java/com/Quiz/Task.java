package com.Quiz;

import com.conf.GlobalSettings;
import db.DMLHandler;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String header;
    private String name;
    private String content;
    private Type type;
    private int maxPoint;
    private Level level;

    public Task(String header,String name,String content,int maxPoint){
        this.header = header;
        this.name = name;
        this.content = content;
        this.maxPoint = maxPoint;
        this.level = null;
    }
    public void addType(Type type){
        this.type = type;
    }
    public void addLevel(Level level){
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getContent() {
        return content;
    }

    public int getMaxPoint() {
        return maxPoint;
    }

    public Level getLevel(){
        return this.level;
    }

    public Type getType(){
        return this.type;
    }

}
