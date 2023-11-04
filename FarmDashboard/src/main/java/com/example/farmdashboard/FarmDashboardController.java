package com.example.farmdashboard;

import javafx.fxml.FXML;

import java.io.IOException;
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
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.Parent;
import java.util.List;
import javafx.concurrent.Task;
import main.java.surelyhuman.jdrone.control.physical.tello.TelloFlight;

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
    @FXML
    private Button existing_container;
    @FXML
    private Button delete_container;
    @FXML
    private Button change_container;
    @FXML
    private Button launch_drone;

    @Override public void initialize(URL arg0, ResourceBundle arg1){
        // storing a list of the panes
        ArrayList<Pane> existingPanes = new ArrayList<>();
        existingPanes.add(barn);
        existingPanes.add(cattle);
        existingPanes.add(drone_pane);
        existingPanes.add(command_center);

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
        TreeItem<String> barn = new TreeItem<>("Barn");
        TreeItem<String> cattle = new TreeItem<>("Cattle");
        TreeItem<String> command_center = new TreeItem<>("Command Center");
        TreeItem<String> drone_pane = new TreeItem<>("Drone");
        barn.getChildren().add(cattle);
        command_center.getChildren().add(drone_pane);
        rootItem.getChildren().addAll(barn, command_center);
        tree_view.setRoot(rootItem);

        // Create New Container Window
        add_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label containerLabel = new Label("Enter Container Name");
            TextField textField = new TextField("");
            Label priceLabel = new Label("Enter Price");
            TextField pricetextField = new TextField("");
            Label lengthLabel = new Label("Enter the length of the pane:");
            TextField lengthTextField = new TextField("");
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
                double length = Double.parseDouble(lengthTextField.getText());
                double height = Double.parseDouble(heightTextField.getText());
                double x = Double.parseDouble(xTextField.getText());
                double y = Double.parseDouble(yTextField.getText());
                if (!name.isEmpty()) {
                    Pane newPane = new Pane();
                    newPane.setId(name);
                    Label paneLabel = new Label(name);
                    newPane.setStyle("-fx-border-color: red;");
                    newPane.setPrefSize(length, width);
                    newPane.setLayoutX(x);
                    newPane.setLayoutY(y);
                    newPane.getChildren().add(paneLabel);
                    root_pane.getChildren().add(newPane);
                    TreeItem<String> newBranchItem = new TreeItem<>(name);
                    rootItem.getChildren().add(newBranchItem);
                    existingPanes.add(newPane);
                    stage.close();
                }
            });

            vbox.getChildren().addAll(containerLabel, textField,priceLabel,pricetextField, lengthLabel, lengthTextField, widthLabel, widthTextField, heightLabel, heightTextField,  xLabel, xTextField, yLabel, yTextField, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 500);
            stage.setScene(scene);
            stage.show();
        });

        // Add container to existing container
        existing_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label existingPanesLabel = new Label("Select From Existing Panes");
            ChoiceBox<Pane> select_pane = new ChoiceBox<>();
            select_pane.getItems().addAll(existingPanes);
            select_pane.getSelectionModel().selectFirst();
            Label containerLabel = new Label("Enter Container Name");
            TextField textField = new TextField("");
            Label priceLabel = new Label("Enter Price");
            TextField pricetextField = new TextField("");
            Label lengthLabel = new Label("Enter the length of the pane:");
            TextField lengthTextField = new TextField("");
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
                Pane selectedPane = select_pane.getValue();
                String name = textField.getText();
                // how to read the input and turn it into a double: https://www.geeksforgeeks.org/double-parsedouble-method-in-java-with-examples/#
                double length = Double.parseDouble(lengthTextField.getText());
                double width = Double.parseDouble(widthTextField.getText());
                double height = Double.parseDouble(heightTextField.getText());
                double x = Double.parseDouble(xTextField.getText());
                double y = Double.parseDouble(yTextField.getText());
                if (!name.isEmpty()) {
                    Pane newPane = new Pane();
                    newPane.setId(name);
                    Label paneLabel = new Label(name);
                    newPane.setStyle("-fx-border-color: red;");
                    newPane.setPrefSize(length, width);
                    newPane.setLayoutX(x);
                    newPane.setLayoutY(y);
                    newPane.getChildren().add(paneLabel);


                    // Add the newPane to the selectedPane
                    selectedPane.getChildren().add(newPane);

                    // Create a new TreeItem for the added item
                    TreeItem<String> newItem = new TreeItem<>(name);

                    // Find the TreeItem for the selectedPane and add the new item to it
                    TreeItem<String> selectedPaneItem = findTreeItem(rootItem, selectedPane.getId());
                    if (selectedPaneItem != null) {
                        selectedPaneItem.getChildren().add(newItem);
                    }


                    existingPanes.add(newPane);
                    stage.close();
                }
            });

            vbox.getChildren().addAll(existingPanesLabel, select_pane, containerLabel, textField,priceLabel, pricetextField, lengthLabel, lengthTextField,widthLabel, widthTextField,heightLabel, heightTextField, xLabel, xTextField, yLabel, yTextField, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 600);
            stage.setScene(scene);
            stage.show();

        });

        // Deleting Pane
        delete_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label existingPanesLabel = new Label("Select From Existing Panes");
            ChoiceBox<Pane> select_pane = new ChoiceBox<>();
            select_pane.getItems().addAll(existingPanes);
            select_pane.getSelectionModel().selectFirst();
            Button confirmButton = new Button("Delete Container");

            confirmButton.setOnAction(e -> {
                Pane selectedPane = select_pane.getValue();
                Parent parent = selectedPane.getParent();
                ((Pane) parent).getChildren().remove(selectedPane);

                // Remove the container from the TreeView
                TreeItem<String> itemToRemove = findTreeItem(rootItem, selectedPane.getId());
                if (itemToRemove != null) {
                    rootItem.getChildren().remove(itemToRemove);
                }

                // Remove the container from the list of existing panes
                existingPanes.remove(selectedPane);

                stage.close();
            });
            vbox.getChildren().addAll(existingPanesLabel, select_pane, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 500);
            stage.setScene(scene);
            stage.show();
        });

        // Changing Container Vars
        change_container.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label existingPanesLabel = new Label("Select From Existing Panes");
            Label containerLabel = new Label("Enter New Container Name");
            TextField textField = new TextField("");
            Label priceLabel = new Label("Enter Price");
            TextField pricetextField = new TextField("");
            Label lengthLabel = new Label("Enter the new length of the pane:");
            TextField lengthTextField = new TextField("");
            Label widthLabel = new Label("Enter the new width of the pane:");
            TextField widthTextField = new TextField("");
            Label heightLabel = new Label("Enter the new height of the pane:");
            TextField heightTextField = new TextField("");
            Label xLabel = new Label("Enter the new X position of the pane:");
            TextField xTextField = new TextField("");
            Label yLabel = new Label("Enter the new Y position of the pane:");
            TextField yTextField = new TextField("");
            ChoiceBox<Pane> select_pane = new ChoiceBox<>();
            select_pane.getItems().addAll(existingPanes);
            select_pane.getSelectionModel().selectFirst();

            Button confirmButton = new Button("Confirm");

            confirmButton.setOnAction(e -> {
                Pane selectedPane = select_pane.getValue();
                double newLength = Double.parseDouble(lengthTextField.getText());
                double newWidth = Double.parseDouble(widthTextField.getText());
                double newHeight = Double.parseDouble(heightTextField.getText());
                double newX = Double.parseDouble(xTextField.getText());
                double newY = Double.parseDouble(yTextField.getText());
                double newPrice = Double.parseDouble(pricetextField.getText());

                String name = textField.getText();
                if (!name.isEmpty()) {
                    Label label = (Label) selectedPane.getChildren().get(0);
                    label.setText(name);

                    selectedPane.setStyle("-fx-border-color: red;");
                    selectedPane.setPrefSize(newLength, newWidth);
                    selectedPane.setLayoutX(newX);
                    selectedPane.setLayoutY(newY);

                    // Update the name in the TreeView
                    TreeItem<String> selectedItem = findTreeItem(rootItem, selectedPane.getId());
                    if (selectedItem != null) {
                        selectedItem.setValue(name); // Update the value of the TreeItem
                    }

                    // Update the name in the list of existing panes
                    int index = existingPanes.indexOf(selectedPane);
                    if (index != -1) {
                        existingPanes.set(index, selectedPane);
                    }

                    selectedPane.setId(name); // Update the ID of the existing pane

                    stage.close();
                }
            });

            vbox.getChildren().addAll(existingPanesLabel, select_pane, containerLabel, textField,priceLabel, pricetextField, lengthLabel, lengthTextField, widthLabel, widthTextField,heightLabel, heightTextField,xLabel,xTextField,yLabel,yTextField, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 600);
            stage.setScene(scene);
            stage.show();
        });

        launch_drone.setOnAction(actionEvent -> {
            System.out.println("Launching drone demo...");

            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    TelloFlight.flight();
                    return null;
                }
            };

            task.setOnSucceeded(event -> {
                System.out.println("Done launching drone demo!");
                // Update the UI if needed
            });

            new Thread(task).start();
        });

    }

    private TreeItem<String> findTreeItem(TreeItem<String> root, String name) {
        for (TreeItem<String> item : root.getChildren()) {
            if (item.getValue().equals(name)) {
                return item;
            }
            TreeItem<String> foundItem = findTreeItem(item, name);
            if (foundItem != null) {
                return foundItem;
            }
        }
        return null;
    }

    public void selectItem(){

    }

}