module com.example.quiz_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.quiz_system to javafx.fxml;
    exports com.example.quiz_system;
}