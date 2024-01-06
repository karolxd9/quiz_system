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

public class DBSelectServerThread {
    private Socket socket;
    private ArrayList<DBSelectServerThread>threadList;
    private int port;
    private String query;

    private int howManyThread;
    private PrintWriter output;
    ExecutorService executor;
    public DBSelectServerThread(ArrayList<DBSelectServerThread>threadList){
        SystemInfo info = new SystemInfo();
        this.threadList = threadList;
        int howManyThread = info.getNumberOfCore();
        this.executor = Executors.newFixedThreadPool(howManyThread);

    }

    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {

            //BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //output = new PrintWriter(socket.getOutputStream(),true);

            while (true) {

                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());
                FutureTask<Void> futureTask = new FutureTask<>(new ClientHandler(clientSocket));
                this.executor.execute(futureTask);

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}