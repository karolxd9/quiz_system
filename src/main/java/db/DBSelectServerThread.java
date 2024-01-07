package db;

import com.conf.DBConnector;
import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import com.conf.SystemInfo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

//serwer do współbieżnej obsługi zapytań selekcji bazy danych
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

    /**
     * Świadczy usługę dostarczania zasobów z zapytania
     */
    public void main() {
        try (ServerSocket serverSocket = new ServerSocket(6000)) {

            BufferedReader input = new BufferedReader(new InputStreamReader(GlobalSettings.socket.getInputStream()));


            while (true) {

                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream toClient = new ObjectOutputStream(new ObjectOutputStream(clientSocket.getOutputStream()));
                System.out.println("Połączono z klientem " + clientSocket.getInetAddress());
                String query = input.readLine();
                System.out.println(query);
                QueryExecutor executor1 = new QueryExecutor();
                ResultSet result = (ResultSet) executor1.executeSelect(query);
                toClient.writeObject((Object) result);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}