package com.example.quiz_system;

import com.db.Client;
import com.db.DBServerThread;

import java.io.IOException;
import java.net.Socket;

public class HelloApplication{
    public static void main(String[] args) throws IOException {
        Client client = new Client("SELECT * FROM user");
        client.sendQuery();
        client.receiveResponse();

    }
}
