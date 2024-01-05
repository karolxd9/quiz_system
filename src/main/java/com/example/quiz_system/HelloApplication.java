package com.example.quiz_system;

import com.auth.Auth;
import com.auth.Register;
import com.auth.SHA256Hashing;
import com.conf.DBConnector;
import com.conf.SystemInfo;

/*import com.generator.UsersParallelGenerator;*/
import com.new_generator.LoginDataGenerator;
import com.new_generator.QuizTaskGenerator;
import db.ClientHandler;
import db.DBServerThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Strona główna");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws SQLException, InterruptedException, UnknownHostException, IOException {

        SystemInfo info = new SystemInfo();
        /*Auth logowanie = new Auth();
        logowanie.login1step("oskareczek","koteczek");
        Register rejestruj = new Register("Karol","Przybycin","oskar12345","Masochista123");
        System.out.println(rejestruj.isOK());*/
        ArrayList<DBServerThread>threads = new ArrayList<>();
        DBServerThread dbServer = new DBServerThread(threads);
        dbServer.main();
        Socket socket = new Socket("localhost",6000);
        
        launch();
    }

}