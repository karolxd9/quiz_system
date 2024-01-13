package com.goncalves.project.util;

import javafx.scene.control.ComboBox;

public class FormValidation {

    // Checks if a String is null or empty
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    // Checks if two passwords match
    public static boolean isPasswordMatch(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    // Checks if a password has at least 8 characters
    public static boolean isPasswordLengthValid(String password) {
        return password.length() < 8;
    }

    // Checks if a ComboBox has a selected item
    public static boolean isComboBoxSelected(ComboBox<String> comboBox) {
        return comboBox.getSelectionModel().getSelectedItem() == null;
    }
}
