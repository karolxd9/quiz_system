package com.goncalves.project.controller;

import com.auth.Auth;
import com.conf.GlobalSettings;

import com.conf.QueryExecutor;
import com.example.quiz_system.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    public TextField login_showPassword;
    @FXML
    private TextField loginInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private CheckBox passwordShow;
    @FXML
    private Hyperlink login_forgotPassword;
    @FXML
    private Button loginButton;
    @FXML
    private Label registerHyperlink;
    @FXML
    private Button CreateAccount;
    private static String username;
    private static String password;
    private static boolean loginStatus;


    public static void setLogin(String login) {
        loginController.username = login;
    }


    @FXML
    public void loginToSystem() throws SQLException, IOException {
        String username = loginInput.getText();
        String password = passwordInput.getText();
        if(username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pola");
            alert.setTitle("Puste pola");
            alert.setContentText("Pole nie mogą być puste");
            alert.show();

        }
        else {

            Auth auth = new Auth();


            try {
                loginStatus = auth.login1step(username, password, GlobalSettings.socket);
            } catch (SQLException e) {
                System.out.println("Błąd z bazą danych.");
            }

            if (loginStatus == true) {
                String query = "SELECT * FROM user_login WHERE login = '"+username+"'";
                ResultSet idResult = QueryExecutor.result(query,GlobalSettings.socket);
                idResult.next();

                int currentID = idResult.getInt("user_id");
                SharedData.getInstance().setDane(currentID);
                SharedData.getInstance().setLoginStatus(loginStatus);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quiz_system/views/homeForm.fxml"));
                Parent mainForm = loader.load();
                Scene mainFormScene = new Scene(mainForm);

                // Get the Stage from the current scene
                Stage window = (Stage) loginButton.getScene().getWindow();
                // Set the scene to the stage
                window.setScene(mainFormScene);

                SharedData.getInstance().setStage(window);
                window.show();



            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawne dane logowania");
                alert.setTitle("Niepoprawne dane logowania");
                alert.setContentText("Wprowadź poprawne dane logowania");
                alert.show();
            }

        }

    }

    @FXML
    public void showPassword(){
        if(passwordShow.isSelected()){
            login_showPassword.setText(passwordInput.getText());
            login_showPassword.setVisible(true);
            passwordInput.setVisible(false);
        }
        else{
            login_showPassword.setText(passwordInput.getText());
            login_showPassword.setVisible(false);
            passwordInput.setVisible(true);
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        passwordShow.setSelected(false);
    }
}
