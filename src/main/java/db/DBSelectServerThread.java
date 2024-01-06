
package db;
import com.conf.SystemInfo;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class DBSelectServerThread {
    private ArrayList<DBSelectServerThread> threadList;
    private ExecutorService executor;

    public DBSelectServerThread(ArrayList<DBSelectServerThread> threadList) {
        SystemInfo info = new SystemInfo();
        this.threadList = threadList;
        int howManyThread = info.getNumberOfCore();
        this.executor = Executors.newFixedThreadPool(howManyThread);
    }

    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(60000)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());


                FutureTask<ResultSet> futureTask = new FutureTask<>(new ClientHandler(clientSocket, "SELECT * FROM quiz"));
                this.executor.execute(futureTask);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}