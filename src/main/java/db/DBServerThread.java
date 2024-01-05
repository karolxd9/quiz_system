package db;

import com.conf.SystemInfo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class DBServerThread{
    private Socket socket;
    private ArrayList<DBServerThread>threadList;
    private int port;

    private int howManyThread;
    ExecutorService executor;
    public DBServerThread(ArrayList<DBServerThread>threadList){
        SystemInfo info = new SystemInfo();
        this.threadList = threadList;
        int howManyThread = info.getNumberOfCore();
        this.executor = Executors.newFixedThreadPool(howManyThread);
    }

    public void main(){
        try(ServerSocket serverSocket = new ServerSocket(6000)){
            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem "+ clientSocket.getInetAddress());

                FutureTask<Void> futureTask = new FutureTask<>(new ClientHandler(clientSocket));
                this.executor.execute(futureTask);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
