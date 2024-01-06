package db;

import com.conf.QueryExecutor;
import javafx.scene.shape.StrokeLineCap;

import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Callable;

public class ClientHandler implements Callable {

    private final Socket clientSocket;
    private String query = "";

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public String addQuery(String query){
        this.query = query;
        return query;
    }


    @Override
    public ResultSet call(){
        try{
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            System.out.println(out);
            System.out.println(in);
            QueryExecutor queryExecutor = new QueryExecutor();
            ResultSet resultSet = queryExecutor.executeSelect(this.query);
            return resultSet;

        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;

    }
}
