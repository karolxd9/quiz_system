package com.example.quiz_system;

import com.conf.SystemInfo;
import com.generator.DataGenerator;
import com.generator.ParallelDataGenerator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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

    public static void main(String[] args) throws SQLException {

        //liczba procesorów
        SystemInfo system = new SystemInfo();
        System.out.println(system.getNumberOfCore());

        //pobranie najmniejszej ID oraz największego ID użytkownika z bazy
        DataGenerator generatedData = new DataGenerator();
        long minID = generatedData.getID(false,"user_id","user");
        long maxID = generatedData.getID(true,"user_id","user");
        System.out.println(minID + " "+maxID);

        // wygenerowanie pojedynczego loginu i hasła w SHA256
        System.out.println(generatedData.generateUserLogin((int) (maxID+1)));


        //wygenrowanie pojedynczej nazwy quizu(do celów testowych tożsame z nazwami aplikacji)
        System.out.println(generatedData.quizName());

        //wygenerowana liczba punktów niezbędna do uzyskania certyfikatu
        System.out.println(generatedData.certificationPoints());
        ParallelDataGenerator results = new ParallelDataGenerator(system);

        long time1_1 = System.currentTimeMillis();
        System.out.println(results.ParallelGeneratingUsers(1000));
        long time1_2 = System.currentTimeMillis();
        System.out.println("Czas równoległy: "+(long)(time1_2-time1_1));

       /* long time2_1 = System.currentTimeMillis();
        System.out.println(results.SingleGeneratingUsers(1000));
        long time2_2 = System.currentTimeMillis();
        System.out.println("Czas normalny: "+(long)(time2_2-time2_1));*/

        launch();






    }

}