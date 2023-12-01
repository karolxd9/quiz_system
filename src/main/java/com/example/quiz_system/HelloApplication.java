package com.example.quiz_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException{
        DBConnector.connect();
        QueryExecutor query = new QueryExecutor();
        try {
            ResultSet result = query.executeSelect("SELECT * FROM user");
            while(result.next()) {
                result.next();
                String resultOfQuery = result.getString("first_name");
                System.out.println(resultOfQuery);
            }

        }
        catch(SQLException | NullPointerException e){
            e.printStackTrace();
        }
        launch();


    }
}