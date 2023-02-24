module com.example.projektrent {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.projektrent to javafx.fxml;
    exports com.example.projektrent;
}