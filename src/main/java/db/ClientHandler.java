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

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;

    }


    @Override
    public Void call(){
        try(PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){
            String inputLine;

            while((inputLine = in.readLine()) != null) {
                QueryExecutor queryExecutor = new QueryExecutor();
                ResultSet resultSet = queryExecutor.executeSelect("SELECT * FROM user");
                while (resultSet.next()) {
                    String result = resultSet.getString("last_name");
                    out.println(result);
                }

                resultSet.close();
            }

        }
        catch(IOException | SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
