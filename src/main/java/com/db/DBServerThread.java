package com.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class DBServerThread {
    private static String query = "";
    public void main() {
        try {
            ServerSocket serverSocket = new ServerSocket(7003);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowe połączenie: " + socket);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                DBServerThread.query = bufferedReader.readLine();

                // Uruchom nowy wątek dla obsługi połączenia
                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) throws IOException {
            this.socket = socket;


        }
        @Override
        public void run() {
            try {

                // Pobierz dane z bazy danych
                Connection connection = DriverManager.getConnection("jdbc:mysql://h28.seohost.pl/srv63119_platforma_testowa", "srv63119_platforma_testowa", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(DBServerThread.query);

                // Przekazanie danych do klienta
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                Result<Object>wynik = new Result<>(resultSet);
                wynik.getData("first_name");


            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}