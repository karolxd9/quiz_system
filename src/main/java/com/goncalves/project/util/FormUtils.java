package com.goncalves.project.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormUtils {

    // Clears the text of a TextField
    public static void clearTextField(TextField textField) {
        textField.setText("");
    }

    // Clears the text of a PasswordField
    public static void clearPasswordField(PasswordField passwordField) {
        passwordField.setText("");
    }

    // Clears the selection of a ComboBox
    public static void clearComboBox(ComboBox<String> comboBox) {
        comboBox.getSelectionModel().clearSelection();
    }

    // Hides a Node (form)
    public static void hide(Node node) {
        node.setVisible(false);
    }

    // Shows a Node (form)
    public static void show(Node node) {
        node.setVisible(true);
    }

    // Fills a ComboBox with an arraylist of Strings
    public static void fillComboBox(ComboBox<String> comboBox, String[] items){
        List<String> list = new ArrayList<>(Arrays.asList(items));
        ObservableList<String> observableList = FXCollections.observableArrayList(list);
        try {
            comboBox.setItems(observableList);
        }
        catch (NullPointerException e){
            System.out.println("");
        }
    }
}
