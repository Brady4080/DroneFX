package com.example.farmdashboard;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FarmDashboardController {
    // how to set up a combo box: https://stackoverflow.com/questions/19065464/how-to-populate-a-list-values-to-a-combobox-in-javafx
    // another example that helped me learn I need to use initialize() : https://stackoverflow.com/questions/18361195/javafx-how-to-load-populate-values-at-start-up
    @FXML
    private ComboBox comboBox;

    @FXML
    protected void initialize() {
        comboBox.getItems().add("option 1");
        comboBox.getItems().add("option 2");
    }

    @FXML
    private TextArea statusMessage;

    @FXML
    protected void onSelectButtonClick() {
        // how to get comboBox selection: http://www.learningaboutelectronics.com/Articles/How-to-retrieve-data-from-a-ChoiceBox-in-JavaFX.php
        String comboBoxValue = "" + comboBox.getValue();
        statusMessage.appendText(comboBoxValue + " selected \n");
    }

    @FXML
    protected void onDeleteButtonClick() {
        String comboBoxValue = "" + comboBox.getValue();
        statusMessage.appendText(comboBoxValue + " deleted \n");
    }
}