package db;

import com.conf.QueryExecutor;
import javafx.scene.shape.StrokeLineCap;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

//klasa wspomagająca współbieżne odpytywanie serwera o wynik zapytania selekcji
public class ClientHandler implements Callable {

    private final Socket clientSocket;
    private String query;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public synchronized String addQuery(String query){
        this.query = query;
        return query;
    }


    /**
     * Klient odpytujący serwer o wyniki zapytania selekcji
     * @return wynik zapytania
     */
    @Override
    public ResultSet call(){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            ObjectInputStream fromServer = new ObjectInputStream(new ObjectInputStream(clientSocket.getInputStream()))){
            out.println(this.query);
            ResultSet resultSet = null;
            try {
                resultSet = (ResultSet) fromServer.readObject();
            }
            catch(ClassNotFoundException | NullPointerException e){
                e.printStackTrace();
            }
            return resultSet;

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}