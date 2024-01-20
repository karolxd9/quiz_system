package com.db;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;

public class DBServerThread {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(7000);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nowe połączenie: " + socket);

                // Uruchom nowy wątek dla obsługi połączenia
                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket socket;
        private String query;
        public ClientHandler(Socket socket) {
            this.socket = socket;
            InputStreamReader inReader = new InputStreamReader(socket.getInputStream());
            this.query = inReader;
        }
        @Override
        public void run() {
            try {
                // Pobierz dane z bazy danych
                Connection connection = DriverManager.getConnection("jdbc:mysql://h28.seohost.pl/srv63119_platforma_testowa", "srv63119_platforma_testowa", "root");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                // Przekazanie danych do klienta
                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

                Result<Object>wynik = new Result<>(resultSet);
                wynik.getData("first_name");

                // Zamknij zasoby
                resultSet.close();
                statement.close();
                connection.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        public void addQuery(String query) {
            this.query = query;
        }
    }
}