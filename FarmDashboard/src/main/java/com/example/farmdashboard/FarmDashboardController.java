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
import javafx.scene.Parent;
import java.util.List;
import javafx.concurrent.Task;
import main.java.surelyhuman.jdrone.control.physical.tello.TelloFlight;
import javafx.scene.control.Label;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;



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
    @FXML
    private VBox infoPane;
    private List<PaneDimensions> existingPanes = new ArrayList<>();

    @Override public void initialize(URL arg0, ResourceBundle arg1){
        // storing a list of the panes
        ArrayList<PaneDimensions> existingPanes = new ArrayList<>();
        existingPanes.add(new PaneDimensions(barn, 636.0, 436.0, 0, 5000, 5000));
        existingPanes.add(new PaneDimensions(cattle, 28.0, 99.0, 0, 500, 500));
        existingPanes.add(new PaneDimensions(drone_pane, 13.0, 13.0, 0, 200, 200));
        existingPanes.add(new PaneDimensions(command_center, 375.0, 0, 0,5000, 5000));



        // how to change the border color: https://docs.oracle.com/javafx/2/api/javafx/scene/doc-files/cssref.html#border
        root_pane.setStyle("-fx-border-color: blue;");
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
                double price = Double.parseDouble(pricetextField.getText());
                double marketValue = Double.parseDouble(pricetextField.getText());
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
                    updateMarketValue(newPane, existingPanes);
                    existingPanes.add(new PaneDimensions(newPane, width, length, height, price, marketValue));
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
            for (PaneDimensions paneDim : existingPanes) {
                select_pane.getItems().add(paneDim.getPane());
            }
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
                double price = Double.parseDouble(pricetextField.getText());
                double marketValue = Double.parseDouble(pricetextField.getText());
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

                    updateMarketValue(selectedPane, existingPanes);

                    // Create a new TreeItem for the added item
                    TreeItem<String> newItem = new TreeItem<>(name);

                    // Find the TreeItem for the selectedPane and add the new item to it
                    TreeItem<String> selectedPaneItem = findTreeItem(rootItem, selectedPane.getId());
                    if (selectedPaneItem != null) {
                        selectedPaneItem.getChildren().add(newItem);
                    }
                    

                    existingPanes.add(new PaneDimensions(newPane, width, length, height, price, marketValue));
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
            for (PaneDimensions paneDim : existingPanes) {
                select_pane.getItems().add(paneDim.getPane());
            }
            select_pane.getSelectionModel().selectFirst();
            Button confirmButton = new Button("Delete Container");

            confirmButton.setOnAction(e -> {
                Pane selectedPane = select_pane.getValue();
                Parent parent = selectedPane.getParent();

                if (parent instanceof Pane) {
                    ((Pane) parent).getChildren().remove(selectedPane);

                    // Remove the container from the list of existing panes
                    existingPanes.removeIf(paneWithDimensions -> paneWithDimensions.getPane() == selectedPane);
                }

                // Remove the container from the TreeView
                TreeItem<String> itemToRemove = findTreeItem(rootItem, selectedPane.getId());
                if (itemToRemove != null) {
                    TreeItem<String> parentItem = itemToRemove.getParent();
                    if (parentItem != null) {
                        parentItem.getChildren().remove(itemToRemove);
                    }
                }

                updateMarketValue((Pane) selectedPane.getParent(), existingPanes);

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
            ChoiceBox<PaneDimensions> select_pane = new ChoiceBox<>();
            for (PaneDimensions paneDim : existingPanes) {
                select_pane.getItems().add(paneDim);
            }
            select_pane.getSelectionModel().selectFirst();

            // When a container is selected from the list, populate the text fields with its attributes
            select_pane.setOnAction(e -> {
                PaneDimensions selectedPane = select_pane.getValue();
                if (selectedPane != null) {
                    textField.setText(selectedPane.getPane().getId());
                    lengthTextField.setText(Double.toString(selectedPane.getLength()));
                    widthTextField.setText(Double.toString(selectedPane.getWidth()));
                    heightTextField.setText(Double.toString(selectedPane.getHeight()));
                    xTextField.setText(Double.toString(selectedPane.getPane().getLayoutX()));
                    yTextField.setText(Double.toString(selectedPane.getPane().getLayoutY()));
                    pricetextField.setText(Double.toString(selectedPane.getPrice()));
                }
            });


            Button confirmButton = new Button("Confirm");

            confirmButton.setOnAction(e -> {
                PaneDimensions selectedPane = select_pane.getValue();
                double newLength = Double.parseDouble(lengthTextField.getText());
                double newWidth = Double.parseDouble(widthTextField.getText());
                double newHeight = Double.parseDouble(heightTextField.getText());
                double newX = Double.parseDouble(xTextField.getText());
                double newY = Double.parseDouble(yTextField.getText());
                double newPrice = Double.parseDouble(pricetextField.getText());
                double newmarketValue = Double.parseDouble(pricetextField.getText());
                String name = textField.getText();

                if (!name.isEmpty()) {
                    Label label = (Label) selectedPane.getPane().getChildren().get(0);
                    label.setText(name);

                    selectedPane.getPane().setStyle("-fx-border-color: red;");
                    selectedPane.getPane().setPrefSize(newLength, newWidth);
                    selectedPane.getPane().setLayoutX(newX);
                    selectedPane.getPane().setLayoutY(newY);
                    selectedPane.setHeight(newHeight);
                    selectedPane.setPrice(newPrice);
                    selectedPane.setMarketValue(newPrice);

                    // Update the name in the TreeView
                    TreeItem<String> selectedItem = findTreeItem(rootItem, selectedPane.getPane().getId());
                    if (selectedItem != null) {
                        selectedItem.setValue(name);
                    }

                    // Update the name in the list of existing panes
                    int index = existingPanes.indexOf(selectedPane);
                    if (index != -1) {
                        existingPanes.set(index, new PaneDimensions(selectedPane.getPane(), newWidth, newLength, newHeight, newPrice, newmarketValue));
                    }

                    selectedPane.getPane().setId(name);

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
            Stage stage = new Stage();
            stage.setTitle("Confirmation");
            VBox vbox = new VBox();
            Label existingPanesLabel = new Label("Select From Existing Panes");
            Label choice1 = new Label("Starting Spot");
            ChoiceBox<Pane> select_pane = new ChoiceBox<>();
            Label choice2 = new Label("Destination");
            ChoiceBox<Pane> select_pane2 = new ChoiceBox<>();
            for (PaneDimensions paneDim : existingPanes) {
                select_pane.getItems().add(paneDim.getPane());
                select_pane2.getItems().add(paneDim.getPane());
            }
            select_pane.getSelectionModel().selectFirst();
            select_pane2.getSelectionModel().selectFirst();
            Button confirmButton = new Button("Fly To");

            confirmButton.setOnAction(e -> {
                Pane selectedPane1 = select_pane.getValue();
                Pane selectedPane2 = select_pane2.getValue();

                PaneDimensions selectedPaneDim1 = null;
                PaneDimensions selectedPaneDim2 = null;

                for (PaneDimensions paneDim : existingPanes) {
                    if (paneDim.getPane() == selectedPane1) {
                        selectedPaneDim1 = paneDim;
                    }
                    if (paneDim.getPane() == selectedPane2) {
                        selectedPaneDim2 = paneDim;
                    }

                    if (selectedPaneDim1 != null && selectedPaneDim2 != null) {
                        break;
                    }
                }

                if (selectedPaneDim1 != null && selectedPaneDim2 != null) {
                    double width1 = selectedPaneDim1.getWidth();
                    double length1 = selectedPaneDim1.getLength();
                    double height1 = selectedPaneDim1.getHeight();

                    double width2 = selectedPaneDim2.getWidth();
                    double length2 = selectedPaneDim2.getLength();
                    double height2 = selectedPaneDim2.getHeight();

                    System.out.println("Pane 1 - Width: " + width1 + ", Length: " + length1 + ", Height: " + height1);
                    System.out.println("Pane 2 - Width: " + width2 + ", Length: " + length2 + ", Height: " + height2);

                    double widthDist = width1 - width2;
                    double lengthDist = length2 - length1;

                    int pixelWidth = (int) widthDist;
                    int pixelLength = (int) lengthDist;

                    TelloFlight.getDist(pixelWidth, pixelLength, 0);

                    System.out.println(widthDist);
                    System.out.println(lengthDist);

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
                }

                stage.close();
            });
            vbox.getChildren().addAll(existingPanesLabel, choice1, select_pane, choice2, select_pane2, confirmButton);
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);

            Scene scene = new Scene(vbox, 700, 500);
            stage.setScene(scene);
            stage.show();
        });



        // Add a listener to the selection property of the TreeView
        tree_view.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
                                TreeItem<String> newValue) {
                if (newValue != null) {

                    String selectedPaneName = newValue.getValue();

                    // premade containers
                    if (selectedPaneName.equals("Barn")) {
                        displayPriceInformation(findPaneDimensionsByName(existingPanes, "barn"));
                    } else if (selectedPaneName.equals("Cattle")) {
                        displayPriceInformation(findPaneDimensionsByName(existingPanes, "cattle"));
                    } else if (selectedPaneName.equals("Drone")) {
                        displayPriceInformation(findPaneDimensionsByName(existingPanes, "drone_pane"));
                    } else if (selectedPaneName.equals("Command Center")) {
                        displayPriceInformation(findPaneDimensionsByName(existingPanes, "command_center"));
                    } else {


                        PaneDimensions selectedPaneDimensions = findPaneDimensionsByName(existingPanes, selectedPaneName);

                        // Display price information in the new Pane
                        displayPriceInformation(selectedPaneDimensions);
                    }
                }
            }
        });


    }

    private void displayPriceInformation(PaneDimensions paneDimensions) {
        // Clear existing content in infoPane
        infoPane.getChildren().clear();

        // Create labels to display price information
        Label nameLabel = new Label("Name: " + paneDimensions.getPane().getId());
        Label priceLabel = new Label("Price: $" + paneDimensions.getPrice());
        Label marketValueLabel = new Label("Current Market Value: $" + paneDimensions.getMarketValue());

        infoPane.getChildren().addAll(nameLabel, priceLabel, marketValueLabel);
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




    private PaneDimensions findPaneDimensionsByName(List<PaneDimensions> paneDimensionsList, String name) {
        for (PaneDimensions paneDim : paneDimensionsList) {
            if (paneDim.getPane().getId().equals(name)) {
                return paneDim;
            }
        }
        return null;
    }

    // Helper method to update the market value of a container based on its children
    private void updateMarketValue(Pane container, List<PaneDimensions> existingPanes) {
        double marketValue = 0;

        for (PaneDimensions paneDim : existingPanes) {
            if (paneDim.getPane().getParent() == container) {
                marketValue += paneDim.getMarketValue();
            }
        }

        for (PaneDimensions paneDim : existingPanes) {
            if (paneDim.getPane() == container) {
                double previousMarketValue = paneDim.getMarketValue();
                if (previousMarketValue != 0) {
                    // Update market value based on the sum of children's market values
                    paneDim.setMarketValue(marketValue);
                } else {
                    // Calculate market value from scratch
                    paneDim.setMarketValue(paneDim.getPrice() + marketValue);
                }
                displayPriceInformation(paneDim);
                break;
            }
        }
    }







    // Used to store the pane name and location etc
    class PaneDimensions {
        private Pane pane;
        private double width;
        private double length;
        private double height;
        private double price;
        private double marketValue;


        public PaneDimensions(Pane pane, double width, double length, double height,double price, double marketValue) {
            this.pane = pane;
            this.width = width;
            this.length = length;
            this.height = height;
            this.price = price;
            this.marketValue = price;
        }

        public Pane getPane() {
            return pane;
        }

        public double getWidth() {
            return width;
        }

        public double getLength() {
            return length;
        }

        public double getHeight() {
            return height;
        }
        public void setHeight(double height) {
            this.height = height;
        }
        public double getPrice() {
            return price;
        }
        public void setPrice(double price) {
            this.price = price;
        }

        public double getMarketValue() {
            return marketValue;
        }

        public void setMarketValue(double marketValue) {
            this.marketValue = marketValue;
        }

        @Override
        public String toString() {
            return pane.getId();
        }
    }



    public void selectItem(){

    }

}

