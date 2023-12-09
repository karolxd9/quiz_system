package com.conf;

public class SystemInfo {
    private int numberOfCore;

    public SystemInfo(){
        numberOfCore = Runtime.getRuntime().availableProcessors();
    }

    public int getNumberOfCore(){
        return this.numberOfCore;
    }


}
