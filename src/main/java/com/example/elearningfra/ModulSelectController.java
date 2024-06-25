
package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class ModulSelectController {
    @FXML
    private AnchorPane anchorPane;




    public void loadModules(int semester) {
        try {
            Map<Integer, String> moduleNames;
            java.sql.Timestamp dbTimestamp = Database.getTimestamp(semester);
            java.sql.Timestamp localTimestamp = Modul.getSemesterTimestamp(semester);

            if (dbTimestamp != null && (localTimestamp == null || dbTimestamp.after(localTimestamp))) {
                System.out.println("data from database");
                moduleNames = Database.getModuleNames(semester);
                Modul.updateLocalModuleNames(semester, moduleNames);
                Modul.updateSemesterTimestamp(semester, dbTimestamp);
            } else {
                System.out.println("local data is used");
                moduleNames = Modul.getLocalModuleNames(semester);
            }

            if (moduleNames != null) {
                createButtons(moduleNames);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception
        }
    }




    private void createButtons(Map<Integer, String> moduleNames) {
        anchorPane.getChildren().clear();
        int yOffset = 10;
        int buttonWidth = 200;
        int buttonHeight = 40;
        int paneWidth = (int) anchorPane.getPrefWidth();
        int paneHeight = (int) anchorPane.getPrefHeight();
        int xOffset = (paneWidth - buttonWidth) / 2;

        for (Map.Entry<Integer, String> entry : moduleNames.entrySet()) {
            Integer moduleId = entry.getKey();
            String moduleName = entry.getValue();

            Button button = new Button(moduleName);
            button.setPrefWidth(buttonWidth);
            button.setPrefHeight(buttonHeight);
            button.setLayoutX(xOffset);
            button.setLayoutY(yOffset);
            button.setOnAction(event -> handleModuleSelection(moduleId));
            anchorPane.getChildren().add(button);
            yOffset += buttonHeight + 10;
        }
    }



private void handleModuleSelection(Integer moduleId) {
    ModulContentController modulContentController;
    FXMLLoader loader = SceneController.getLoader(5);
    modulContentController = loader.getController();
    modulContentController.loadModulDetails(moduleId);
    SceneController.switchToPage(5);
}



    public void switchToHome()
    {
        SceneController.switchToPage(0);
    }

}


