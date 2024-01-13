module com.example.quiz_system {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafaker;
    requires com.google.common;
    opens com.goncalves.project to javafx.fxml;
    exports com.goncalves.project;
    exports com.goncalves.project.controller;
    opens com.goncalves.project.controller to javafx.fxml;
    exports com.goncalves.project.model;
    opens com.goncalves.project.model to javafx.fxml;
    exports com.goncalves.project.dao;
    opens com.goncalves.project.dao to javafx.fxml;
    exports com.goncalves.project.service;
    opens com.goncalves.project.service to javafx.fxml;
    exports com.goncalves.project.util;
    opens com.goncalves.project.util to javafx.fxml;
    opens com.example.quiz_system to javafx.fxml;
    exports com.example.quiz_system;
    exports com.conf;
    opens com.conf to javafx.fxml;

}