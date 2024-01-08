package db;

import com.conf.DBConnector;
import com.conf.SystemInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//serwer do współbieżnej obsługi zapytań selekcji bazy danych
public class DBSelectServerThread {
    private Socket socket;

    private int port;
    private String query;

    private int howManyThread;
    private PrintWriter output;
    ExecutorService executor;
    public DBSelectServerThread(){
        this.port = 6000;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    /**
     * Świadczy usługę dostarczania zasobów z zapytania
     */
    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(this.getPort())) {
            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());
                clientSocket.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}