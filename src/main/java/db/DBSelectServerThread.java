package db;

import com.conf.DBConnector;
import com.conf.SystemInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
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
        try (ServerSocket serverSocket = new ServerSocket(60000)) {

            while (true) {

                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String clientQuery = in.readLine();
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());
                System.out.println(clientQuery);
                FutureTask futureTask = new FutureTask<>(new ClientHandler(clientSocket,clientQuery));
                this.executor.execute(futureTask);
                ResultSet r1 = (ResultSet) futureTask.get();
                ObjectOutputStream toClient = new ObjectOutputStream(clientSocket.getOutputStream());
                toClient.writeObject(r1);



            }
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            executor.shutdown();
        }
    }

}
