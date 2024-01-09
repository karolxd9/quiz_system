package com.example.quiz_system;

import com.auth.Auth;
import com.auth.Register;
import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import com.conf.SystemInfo;

/*import com.generator.UsersParallelGenerator;*/
import com.db.DBSelectServerThread;
import com.modification.ModificationUserData;
import com.quiz.Option;
import com.quiz.Task;
import com.quiz.Type;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;

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
       // Socket socket = new Socket("192.168.0.103",6000);
        DBSelectServerThread dbServer = new DBSelectServerThread();
        dbServer.setPort(7000);
        dbServer.main();
        launch();


        Register newUser = new Register("Adam","","Głaz","adamxd69","Malutenki69", GlobalSettings.socket);
        QueryExecutor queryExecutor = new QueryExecutor();
        ResultSet r1 = queryExecutor.executeSelect("SELECT * FROM WHERE login = '"+newUser.getLogin()+"'");
        int userID = r1.getInt("user_id");
        ModificationUserData userDataMod = new ModificationUserData();
        userDataMod.changeLogin(userID,"karolpxd69");
        userDataMod.changeFirstName(userID,"Karol");
        userDataMod.changeSurname(userID,"Przybycin");
        userDataMod.changePassword(userID,"Malutenki69","Malutenki10+59");

        Auth wynikLogowania = new Auth();
        System.out.println(wynikLogowania.login1step("karolpxd69","Malutenki10+59",GlobalSettings.socket));

        Task newTask = new Task(15001,"Klasa anonimowa","Co to klasa anonimowa","Wyjaśnij co to klasa anonimowa",10);
        newTask.addType(Type.OPENED);

        Option additionalOption = new Option(15002,"silniową");
        additionalOption.addCorretion(false);
        additionalOption.addOptionToDB();



    }

}