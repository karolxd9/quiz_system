module com.example.quiz_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.quiz_system to javafx.fxml;
    exports com.example.quiz_system;
}