package com.goncalves.project.dao;

import com.goncalves.project.model.User;

import java.sql.*;
import java.time.LocalDate;


public class UserDAO {
    private final Connection connect;

    public UserDAO() {
        // Get connection from DatabaseConnection
        connect = DatabaseConnection.connect();
    }

    // This method checks if a user with the provided username and password exists in the database
    public User checkUser(String username, String password) {
        User user = null;
        String verifyLogin = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(verifyLogin)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                String email = queryResult.getString("email");
                String question = queryResult.getString("question");
                String answer = queryResult.getString("answer");
                user = new User(email, username, password, question, answer);
            }
        } catch (Exception e) {
            System.out.println("An error occurred during user check.");
            e.printStackTrace();
        }

        return user;
    }

    // This method registers a new user in the database
    public boolean registerUser(User user) {
        String insertFields = "INSERT INTO users(username,password,email,question,answer,date) VALUES (?, ?, ?, ?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);

        try (PreparedStatement preparedStatement = connect.prepareStatement(insertFields)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getQuestion());
            preparedStatement.setString(5, user.getAnswer());
            preparedStatement.setDate(6, sqlDate);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred during user registration.");
            e.printStackTrace();
        }

        return false;
    }

    // This method checks if a user with the provided username, question, and answer exists in the database
    public User forgotPassword(String username, String question, String answer) {
        User user = null;
        String verifyLogin = "SELECT * FROM users WHERE username = ? AND question = ? AND answer = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(verifyLogin)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, question);
            preparedStatement.setString(3, answer);
            ResultSet queryResult = preparedStatement.executeQuery();

            if (queryResult.next()) {
                String email = queryResult.getString("email");
                String password = queryResult.getString("password");
                user = new User(email, username, password, question, answer);
            }
        } catch (Exception e) {
            System.out.println("An error occurred during password recovery.");
            e.printStackTrace();
        }

        return user;
    }





    // This method changes the password of a user in the database
    public boolean changePassword(String username, String newPassword) {
        String updatePassword = "UPDATE users SET password = ? WHERE username = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(updatePassword)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred during password change.");
            e.printStackTrace();
        }

        return false;
    }

    // This method checks if a user with the provided username exists in the database
    public boolean getUserByUsername(String username) throws SQLException {
        String selectData = "SELECT * FROM users WHERE username = ?";
        try (
             PreparedStatement prepare = connect.prepareStatement(selectData)) {
            prepare.setString(1, username);
            ResultSet result = prepare.executeQuery();
            return result.next();
        }
    }



}
