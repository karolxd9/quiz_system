package com.example.quiz_system;

public class SystemInfo {
    private int numberOfCore;

    public SystemInfo(){
        this.numberOfCore = Runtime.getRuntime().availableProcessors();
    }

    public int getNumberOfCore(){
        return this.numberOfCore;
    }

}
