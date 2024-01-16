package com.goncalves.project.controller;

import com.auth.Register;
import com.auth.SHA256Hashing;
import com.conf.GlobalSettings;
import com.conf.QueryExecutor;
import com.example.quiz_system.HelloApplication;
import com.modification.ModificationUserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label welcomeLabel;
    @FXML
    private MenuBar primaryMenu;
    @FXML
    private StackPane anchorWindow;
    @FXML
    private Button logout_btn;
    @FXML
    private BorderPane EditData;
    @FXML
    private Button changeLoginButton;
    @FXML
    private Button changeNameButton;
    @FXML
    private Button changeSurnameButton;
    @FXML
    private TextField changeLoginField;
    @FXML
    private TextField changeNameField;
    @FXML
    private TextField changeSurnameField;
    @FXML
    private PasswordField currentPassField;
    @FXML
    private PasswordField changePassField;
    @FXML
    private BorderPane editPass;
    private int ID;
    private boolean loginStatus;
    private Stage newStage;
    private String namaAndSurname = null;
    private ResultSet r1 = null;
    private String login = "";
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        primaryMenu.setMinWidth(anchorWindow.getWidth());
        changeLoginField.requestFocus();
        ID = SharedData.getInstance().getDane();
        loginStatus = SharedData.getInstance().isLoginStatus();
        String dataFromUser = "SELECT * FROM user_login NATURAL JOIN user WHERE user_id = "+ ID;
        System.out.println(dataFromUser);
        r1 = QueryExecutor.result(dataFromUser, GlobalSettings.socket);
        try {
            r1.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            namaAndSurname = r1.getString("first_name") +" "+r1.getString("last_name");
            login = r1.getString("login");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(namaAndSurname);
        this.welcomeLabel.setText("Witaj, " + namaAndSurname+" !");
    }
    public void logout() throws IOException {
        SharedData.getInstance().setLoginStatus(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/quiz_system/views/loginForm.fxml"));
        Parent mainForm = loader.load();
        Scene mainFormScene = new Scene(mainForm);

        // Get the Stage from the current scene
        Stage window = SharedData.getInstance().getNewStage();

        // Set the scene to the stage
        window.setScene(mainFormScene);
        window.show();
    }

    public void showUserInfo() {
        this.EditData.setVisible(false);
        this.EditData.setDisable(true);
        this.welcomeLabel.setText("Imię i nazwisko: "+this.namaAndSurname+"\n"+"Login: "+login);
        this.welcomeLabel.setVisible(true);
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
    }

    public void changeData(){
        this.welcomeLabel.setVisible(false);
        this.EditData.setVisible(true);
        this.EditData.setDisable(false);
        changeLoginField.setDisable(false);
        changeLoginField.setEditable(true);
        this.EditData.setDisable(true);
        this.EditData.setVisible(true);
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
    }

    public void changeLogin() throws SQLException {
        String login = changeLoginField.getText();
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
        if(login.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeLoginConditions(login)){
                changes.changeLogin(ID,login);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana loginu");
                alert.setTitle("Poprawna zmiana loginu");
                alert.setContentText("Zmiana loginu przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina loginu");
                alert.setTitle("Niepoprawna zmaina loginu");
                alert.setContentText("Zmiana loginu się nie powiodła");
                alert.show();
            }
        }
    }

    public void changeName(ActionEvent actionEvent) throws SQLException {
        String name = changeNameField.getText();
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
        if(name.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeNameConditions(name)){
                changes.changeFirstName(ID,name);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana imienia");
                alert.setTitle("Poprawna zmiana imienia");
                alert.setContentText("Zmiana imienia przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina imienia");
                alert.setTitle("Niepoprawna zmaina imienia");
                alert.setContentText("Zmiana imienia się nie powiodła");
                alert.show();
            }
        }
    }

    public void changeSurname(ActionEvent actionEvent) throws SQLException {
        String surname = changeSurnameField.getText();
        this.editPass.setVisible(false);
        this.editPass.setDisable(true);
        if(surname.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else{
            ModificationUserData changes = new ModificationUserData();
            if(Register.includeNameConditions(surname)){
                try {
                    changes.changeSurname(ID,surname);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana nazwiska");
                alert.setTitle("Poprawna zmiana nazwiska");
                alert.setContentText("Zmiana nazwiska przebiegła pomyślnie");
                alert.show();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmaina nazwiska");
                alert.setTitle("Niepoprawna zmaina nazwiska");
                alert.setContentText("Zmiana nazwiska się nie powiodła");
                alert.show();
            }
        }
    }

    public void changePass() throws SQLException {
        this.editPass.setVisible(true);
        this.editPass.setDisable(false);
        String currentPassword = currentPassField.getText();
        String newPassword = changePassField.getText();
        ModificationUserData changePasswordObj = new ModificationUserData();
        String query = "SELECT * user_login WHERE user_id = "+ID;
        ResultSet r1 = QueryExecutor.result(query,GlobalSettings.socket);
        try {
            r1.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(currentPassword.isEmpty() || newPassword.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Puste pole");
            alert.setTitle("Puste pole");
            alert.setContentText("Pola nie mogą być puste");
            alert.show();
        }
        else {
            if (r1.getString("PASSWORD") == SHA256Hashing.hashStringToSHA256(currentPassword) && Register.includePasswordConditions(newPassword)) {
                changePasswordObj.changePassword(ID, currentPassword, newPassword);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Poprawna zmiana hasła");
                alert.setTitle("Poprawna zmiana hasła");
                alert.setContentText("Zmiana hasło przebiegła poprawnie");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Niepoprawna zmiana hasła");
                alert.setTitle("Niepoprawna zmiana hasła");
                alert.setContentText("Zmiana hasło nie powiodła się");
                alert.show();
            }
        }


    }

    public void editPassClicked(ActionEvent actionEvent) {
        this.editPass.setVisible(true);
        this.editPass.setDisable(false);

    }
}
