module com.example.quiz_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires javafaker;
    requires com.google.common;

    opens com.example.quiz_system to javafx.fxml;
    exports com.example.quiz_system;
    exports com.conf;
    opens com.conf to javafx.fxml;


}