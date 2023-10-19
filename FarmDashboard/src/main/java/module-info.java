module com.example.farmdashboard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.farmdashboard to javafx.fxml;
    exports com.example.farmdashboard;
}