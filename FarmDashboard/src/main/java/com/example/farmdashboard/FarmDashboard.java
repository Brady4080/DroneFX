package com.example.farmdashboard;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FarmDashboard extends Application {

    private static FarmDashboard instance; // Singleton instance

    private FarmDashboard() {
    }

    public static FarmDashboard getInstance() {
        if (instance == null) {
            instance = new FarmDashboard();
        }
        return instance;
    }

    public void initialize(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FarmDashboard.class.getResource("farm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("Farm Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void start(Stage stage) {
    }

    public static void main(String[] args) {
        FarmDashboard dashboard = FarmDashboard.getInstance();
        Platform.runLater(() -> {
            Stage stage = new Stage();
            try {
                dashboard.initialize(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}