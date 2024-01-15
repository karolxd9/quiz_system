package com.goncalves.project.service;

import com.auth.Auth;
import com.auth.SHA256Hashing;
import com.conf.GlobalSettings;
import com.goncalves.project.dao.UserDAO;
import com.goncalves.project.model.User;

import java.sql.SQLException;

public class UserService {
    private static UserService instance;
    private UserDAO userDAO;
    private User loggedInUser;
    public UserService() {}

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }


    public boolean loginUser(String username, String password) {
        try {
            Auth auth = new Auth();
            // Tutaj możesz dodać dodatkową logikę lub walidację, jeśli to konieczne
            return auth.login1step(username, password, GlobalSettings.socket);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public boolean registerUser(User user) throws SQLException {
        // Check if a user with the provided username already exists
        if (getUserDAO().getUserByUsername(user.getUsername())) {
            // A user with the provided username already exists
            return false;
        }
        // If no user with the provided username exists, register a new user
        return getUserDAO().registerUser(user);
    }

    public User forgotPassword(String username, String question, String answer)  {
        return getUserDAO().forgotPassword(username, question, answer);
    }
    public boolean changePassword(String username, String newPassword)  {
        return getUserDAO().changePassword(username, newPassword);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void logout() {
        loggedInUser = null;
    }
}
