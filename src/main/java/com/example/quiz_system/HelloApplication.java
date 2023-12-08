package com.example.quiz_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        //pobranie najmniejszej ID oraz największego ID użytkownika z bazy
        DataGenerator generatedData = new DataGenerator();
        long minID = generatedData.getID(false,"user_id","user");
        long maxID = generatedData.getID(true,"user_id","user");
        System.out.println(minID + " "+maxID);

        // wygenerowanie pojedynczego loginu i hasła w SHA256
        System.out.println(generatedData.generateUserLogin((int) (maxID+1)));


        //wygenrowanie pojedynczej nazwy quizu(do celów testowych tożsame z nazwami aplikacji)
        System.out.println(generatedData.quizName());

    }

}