package com.db;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.ResultSet;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.101", 7000);
            // Odbierz dane od serwera
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while (true) {
                try {
                    ResultSet receivedData = (ResultSet) inputStream.readObject();
                    System.out.println("Otrzymano dane: " + receivedData.toString());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}