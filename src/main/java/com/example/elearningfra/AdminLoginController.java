package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;

public class AdminLoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button newUserButton;

    @FXML
    private Label errorLabel;

    @FXML
    public void initialize() {
        errorLabel.setText("");
        usernameField.clear();
        passwordField.clear();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (Database.validateUser(username, password)) {
                SceneController.switchToPage(6);
                clearFields();
                // SceneController.switchToPage(1);
            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Incorrect username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleCancel() {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setText("");
    }



    public void switchToHome()
    {
        clearFields();
        SceneController.switchToPage(0);
    }

    private void clearFields()
    {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setText("");
    }
}





