package com.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
    private String query;
    public Client(String query){
        this.query = query;
    }
    public void main() {
        try {
            Socket socket = new Socket("192.168.0.101", 7001);
            // Odbierz dane od serwera
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            OutputStreamWriter outWriter = new OutputStreamWriter(socket.getOutputStream());
            outWriter.write(this.query);
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