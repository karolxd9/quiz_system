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

    public ClientHandler(Socket clientSocket,String query){
        this.clientSocket = clientSocket;
        this.query = query;
    }



    @Override
    public ResultSet call(){
        try{
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            out.println(this.query);
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
