package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.sql.SQLException;

/**
 * Controller class for handling the admin login functionality.
 */
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

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        errorLabel.setText("");
        usernameField.clear();
        passwordField.clear();
    }

    /**
     * Handles the login button action.
     */
    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        try {
            if (Database.validateUser(username, password)) {
                SceneController.switchToPage(6);
                clearFields();
            } else {
                errorLabel.setTextFill(Color.RED);
                errorLabel.setText("Incorrect username or password!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the cancel button action.
     */
    @FXML
    private void handleCancel() {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setText("");
    }

    /**
     * Switches to the home page.
     */
    public void switchToHome() {
        clearFields();
        SceneController.switchToPage(0);
    }

    /**
     * Clears the input fields and error message.
     */
    private void clearFields() {
        usernameField.clear();
        passwordField.clear();
        errorLabel.setText("");
    }
}




