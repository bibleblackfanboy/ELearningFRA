package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class NewUserController {

    @FXML
    private TextField newUsernameField;

    @FXML
    private TextField newPasswordField;

    @FXML
    private TextField newEmailField;

    @FXML
    private Button saveButton;

    @FXML
    private Button backButton;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        statusLabel.setText("");
        newUsernameField.clear();
        newPasswordField.clear();
        newEmailField.clear();
        saveButton.setDisable(false);
    }

    @FXML
    private void handleSave() {
        String username = newUsernameField.getText();
        String password = newPasswordField.getText();
        String email = newEmailField.getText();

        try {
            Database.saveUser(username, password, email);
            statusLabel.setTextFill(Color.GREEN);
            statusLabel.setText("User saved successfully!");
            saveButton.setDisable(true);
        } catch (SQLException e) {
            e.printStackTrace();
            statusLabel.setTextFill(Color.RED);
            statusLabel.setText("Error saving user!");
        }
    }

    @FXML
    private void handleBack() {
        clearFields();
        SceneController.switchToPage(1);
        // SceneController.switchToPage(0);
    }

    private void clearFields()
    {
        newUsernameField.clear();
        newPasswordField.clear();
        newEmailField.clear();
        saveButton.setDisable(false);
        statusLabel.setText("");
    }
}
