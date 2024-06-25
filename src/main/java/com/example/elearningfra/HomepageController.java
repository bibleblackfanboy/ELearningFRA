
package com.example.elearningfra;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;



public class HomepageController {

    public void switchToAdmin()
    {
    SceneController.switchToPage(1);
    }


    public void switchToModulPage()
    {
        SceneController.switchToPage(2);
    }





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



    public void switchToModulSelect(int semester)
    {
        ModulSelectController modulSelectController;
        FXMLLoader loader = SceneController.getLoader(4);
        modulSelectController = loader.getController();
        modulSelectController.loadModules(semester);
        SceneController.switchToPage(4);
    }







}




