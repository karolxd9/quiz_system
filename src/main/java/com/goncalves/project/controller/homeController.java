package com.goncalves.project.controller;


import com.goncalves.project.model.User;
import com.goncalves.project.service.UserService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class homeController implements Initializable {

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> usernameColumn;
    @FXML
    private TableColumn<User, String> emailColumn;


    private UserService userService;
    public Button logout_btn;
    @FXML
    private Label welcomeLabel;

    // You can define additional methods and fields for the main form controller here

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.userService = UserService.getInstance();
        User loggedUser = userService.getLoggedInUser();

        // Initialize the user table
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        // Add more columns as needed

        // Add the logged-in user to the table
        if (loggedUser != null) {
            userTable.getItems().add(loggedUser);
            welcomeLabel.setText("Welcome, " + loggedUser.getUsername() + "!");
        }
    }



    // Other methods and event handlers for the main form can be defined here
    public void logout() throws IOException {

        userService.logout();
        // navigateToLoginForm();
        // Load main form
        Parent loginForm = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/views/loginForm.fxml")));
        Scene loginFormScene = new Scene(loginForm);

        // Get the Stage from the current scene
        Stage window = (Stage) logout_btn.getScene().getWindow();

        // Set the scene to the stage
        window.setScene(loginFormScene);

        // Display the stage
        window.show();
    }
}



