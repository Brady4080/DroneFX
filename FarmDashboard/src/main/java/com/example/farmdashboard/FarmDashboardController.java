package com.example.farmdashboard;

import javafx.fxml.FXML;
import java.net.URL;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ResourceBundle;

public class FarmDashboardController implements Initializable {
    @FXML
    private BorderPane borderPane;

    @FXML
    private TreeView tree_view;

    @FXML
    private Button add_container;

    @Override public void initialize(URL arg0, ResourceBundle arg1){
        // how to change the border color: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#border
        borderPane.setStyle("-fx-border-color: red;");

        // setting the text for the Barn Pane
        Label label = new Label("Barn");
        label.setAlignment(Pos.TOP_LEFT);
        borderPane.setTop(label);

        // TreeView Pane
        // how to get use the TreeView: https://www.youtube.com/watch?v=CNLHTrY3Nh8&ab_channel=BroCode
        TreeItem<String> rootItem = new TreeItem<>("Root");
        TreeItem<String> branchItem1 = new TreeItem<>("Barn");
        //rootItem.setValue(root_pane);
        //branchItem1.setValue(borderPane);
        rootItem.getChildren().addAll(branchItem1);
        tree_view.setRoot(rootItem);


        // Confirmation Window
        add_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            Label label_temp = new Label("This is the content of the new Stage.");
            Scene scene = new Scene(label_temp, 300, 200);
            stage.setScene(scene);
            stage.show();
        });

    }

    public void selectItem(){

    }

}