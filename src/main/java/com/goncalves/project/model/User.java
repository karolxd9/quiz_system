package com.goncalves.project.model;

public class User {
    private String email, username, password, question, answer;

    // constructor, getters, and setters
    public User(String email, String username, String password, String question, String answer) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.question = question;
        this.answer = answer;
    }

    // getters
    public String getEmail() { return email; }

    public String getUsername() {return username;}

    public String getPassword() {return password;}

    public String getQuestion() {return question;}

    public String getAnswer() {return answer;}

    // setters
    public void setEmail(String email) {this.email = email;}

    public void setUsername(String username) {this.username = username;}

    public void setPassword(String password) {this.password = password;}

    public void setQuestion(String question) {this.question = question;}

    public void setAnswer(String answer) {this.answer = answer;}
}
