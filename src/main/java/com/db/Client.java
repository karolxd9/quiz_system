package com.db;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private String query;
    public Client(String query){
        this.query = query;
    }
    public String getQuery(){
        return this.query;
    }
    public void main() {
        try {
            Socket socket = new Socket("192.168.0.101", 7003);

            //wyśyłanie danych do serwera
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);


            printWriter.println(this.query);

            try{
                Thread.sleep(500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            //odbierz dane od serwera
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            try {
                ArrayList<Object> receivedData = (ArrayList<Object>) inputStream.readObject();
                System.out.println("Otrzymano dane: " + receivedData.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendQuery() {
        try (Socket socket = new Socket("192.168.0.101", 7003);
             PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

            // Wyślij zapytanie do serwera
            printWriter.println(this.query);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveResponse() {
        try (Socket socket = new Socket("192.168.0.101", 7003);
             ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {

            // Odbierz dane od serwera
            try {
                ArrayList<Object> receivedData = (ArrayList<Object>) inputStream.readObject();
                System.out.println("Otrzymano dane: " + receivedData.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}