package com.file;

public class TaskFormat {
    private String header;
    private String name;
    private String content;
    private int maxPoint;

    public TaskFormat(String header,String name,String content,int maxPoint){
        this.header = header;
        this.name = name;
        this.content = content;
        this.maxPoint = maxPoint;
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
}
