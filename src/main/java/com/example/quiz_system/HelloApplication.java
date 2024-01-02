package com.example.quiz_system;

import com.conf.DBConnector;
import com.conf.SystemInfo;

/*import com.generator.UsersParallelGenerator;*/
import com.new_generator.LoginDataGenerator;
import com.new_generator.QuizTaskGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setMaximized(true);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
        DBConnector.connect();
        SystemInfo info = new SystemInfo();
        launch();



    }

}