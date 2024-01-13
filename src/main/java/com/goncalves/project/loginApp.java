package com.goncalves.project;

import com.goncalves.project.dao.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class loginApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Ładowanie FXML i inicjalizacja sceny
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/views/loginForm.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("SYSTEM QUIZÓW");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        // Zamykanie połączenia z bazą danych
        DatabaseConnection.close();
        super.stop();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
