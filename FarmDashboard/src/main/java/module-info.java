module com.example.farmdashboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.bytedeco.javacv;

    opens com.example.farmdashboard to javafx.fxml;
    exports com.example.farmdashboard;
    requires java.desktop;
}