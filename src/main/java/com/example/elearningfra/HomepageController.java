package com.example.elearningfra;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

/**
 * Controller for the homepage in the e-learning application.
 */
public class HomepageController {

    /**
     * Switches the scene to the admin page.
     */
    public void switchToAdmin() {
        SceneController.switchToPage(1);
    }

    /**
     * Handles button clicks and switches the scene based on the button ID.
     *
     * @param event the action event triggered by button click
     */
    public void Buttons(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonId = clickedButton.getId();

        switch (buttonId) {
            case "semester1Button":
                switchToModulSelect(1);
                break;
            case "semester2Button":
                switchToModulSelect(2);
                break;
            case "semester3Button":
                switchToModulSelect(3);
                break;
            case "semester4Button":
                switchToModulSelect(4);
                break;
            case "semester5Button":
                switchToModulSelect(5);
                break;
            case "semester6Button":
                switchToModulSelect(6);
                break;
            default:
                System.out.println("Unexpected button ID: " + buttonId);
                break;
        }
    }

    /**
     * Switches the scene to the module selection page for the specified semester.
     *
     * @param semester the semester number to load modules for
     */
    public void switchToModulSelect(int semester) {
        ModulSelectController modulSelectController;
        FXMLLoader loader = SceneController.getLoader(4);
        modulSelectController = loader.getController();
        modulSelectController.loadModules(semester);
        SceneController.switchToPage(4);
    }
}


