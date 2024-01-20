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
            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            printWriter.write(this.query);


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
}