package com.example.farmdashboard;

import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.ResourceBundle;

public class FarmDashboardController implements Initializable {
    @FXML
    private Pane barn;
    @FXML
    private Pane cattle;
    @FXML
    private Pane drone_pane;
    @FXML
    private Pane command_center;
    @FXML
    private TreeView<String> tree_view;
    @FXML
    private Button add_container;
    @FXML
    private AnchorPane root_pane;

    @Override public void initialize(URL arg0, ResourceBundle arg1){
        // how to change the border color: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#border
        barn.setStyle("-fx-border-color: red;");
        cattle.setStyle("-fx-border-color: red;");
        drone_pane.setStyle("-fx-border-color: red;");
        command_center.setStyle("-fx-border-color: red;");

        // setting labels for the panes
        Label label0 = new Label("Barn");
        barn.getChildren().add(label0);
        Label label1 = new Label("Cattle");
        cattle.getChildren().add(label1);
        Label label2 = new Label("Drone");
        drone_pane.getChildren().add(label2);
        Label label3 = new Label("Command Center");
        command_center.getChildren().add(label3);


        // TreeView Pane
        // how to get use the TreeView: https://www.youtube.com/watch?v=CNLHTrY3Nh8&ab_channel=BroCode
        TreeItem<String> rootItem = new TreeItem<>("Root");
        TreeItem<String> branchItem1 = new TreeItem<>("Barn");
        //rootItem.setValue(root_pane);
        //branchItem1.setValue(Pane);
        rootItem.getChildren().addAll(branchItem1);
        tree_view.setRoot(rootItem);


        // Confirmation Window
        add_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label containerLabel = new Label("Enter Container Name");
            TextField textField = new TextField("");
            Label widthLabel = new Label("Enter the width of the pane:");
            TextField widthTextField = new TextField("");
            Label heightLabel = new Label("Enter the height of the pane:");
            TextField heightTextField = new TextField("");
            Label xLabel = new Label("Enter the X position of the pane:");
            TextField xTextField = new TextField("");
            Label yLabel = new Label("Enter the Y position of the pane:");
            TextField yTextField = new TextField("");
            Button confirmButton = new Button("Create Pane");

            confirmButton.setOnAction(e -> {
                String name = textField.getText();
                // how to read the input and turn it into a double: https://www.geeksforgeeks.org/double-parsedouble-method-in-java-with-examples/#
                double width = Double.parseDouble(widthTextField.getText());
                double height = Double.parseDouble(heightTextField.getText());
                double x = Double.parseDouble(xTextField.getText());
                double y = Double.parseDouble(yTextField.getText());
                if (!name.isEmpty()) {
                    Pane newPane = new Pane();
                    Label paneLabel = new Label(name);
                    newPane.setStyle("-fx-border-color: red;");
                    newPane.setPrefSize(width, height);
                    newPane.setLayoutX(x);
                    newPane.setLayoutY(y);
                    newPane.getChildren().add(paneLabel);
                    root_pane.getChildren().add(newPane);
                    stage.close();
                }
            });

            vbox.getChildren().addAll(containerLabel, textField, widthLabel, widthTextField, heightLabel, heightTextField, xLabel, xTextField, yLabel, yTextField, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 500);
            stage.setScene(scene);
            stage.show();
        });

    }

    public void selectItem(){

    }

}