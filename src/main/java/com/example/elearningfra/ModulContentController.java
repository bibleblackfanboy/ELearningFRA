package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Controller class for managing module content in the e-learning application.
 */
public class ModulContentController {

    @FXML private TextField modulNameField;
    @FXML private TextField cpField;
    @FXML private TextField dauerField;
    @FXML private TextField pruefungField;
    @FXML private TextField semesterField;
    @FXML private TextField spracheField;
    @FXML private TextField koordinationField;
    @FXML private TextField verfuegbarkeitField;
    @FXML private TextField voraussetzungField;
    @FXML private TextField vorleistungField;
    @FXML private TextArea inhaltField;
    @FXML private VBox professorList;
    @FXML private ImageView professorImageView;
    @FXML private Button youtubeButton;
    @FXML private Button backButton;
    private int currentModulId;

    /**
     * Loads the module details for a given module ID and updates the UI fields.
     *
     * @param modulId The ID of the module to be loaded.
     */
    public void loadModulDetails(int modulId) {
        this.currentModulId = modulId;
        try {
            Timestamp dbTimestamp = Database.getModulTimestamp(modulId);
            Timestamp localTimestamp = ModulDetails.getLocalModulDetails(modulId) != null ? ModulDetails.getLocalModulDetails(modulId).getTimestampModul() : null;
            //statement is from ChatGPT
            if (dbTimestamp != null && (localTimestamp == null || dbTimestamp.after(localTimestamp))) {
                System.out.println("Data from database");
                ModulDetails modulDetails = Database.getModulDetails(modulId);
                if (modulDetails != null) {
                    updateModulDetailsFields(modulDetails);
                    ModulDetails.updateLocalModulDetails(modulId, modulDetails);
                }
                List<Professor> professors = Database.getProfessorsForModul(modulId);
                if (!professors.isEmpty()) {
                    updateProfessorFields(professors);
                    Professor.updateLocalProfessors(modulId, professors);
                }
                List<Material> materials = Database.getMaterialsForModul(modulId);
                if (!materials.isEmpty()) {
                    Material material = materials.get(0); // Assuming one material for simplicity
                    youtubeButton.setOnAction(event -> openYouTubeVideo(material.getUrl()));
                    Material.updateLocalMaterials(modulId, materials);
                    System.out.println(material.getUrl());
                    System.out.println("youtube successful2");
                } else {
                    System.out.println("No Link Found");
                    youtubeButton.setOnAction(event -> openYouTubeVideo("test"));
                }
            } else {
                System.out.println("Local data is used");
                ModulDetails modulDetails = ModulDetails.getLocalModulDetails(modulId);
                if (modulDetails != null) {
                    updateModulDetailsFields(modulDetails);
                }
                List<Professor> professors = Professor.getLocalProfessors(modulId);
                if (professors != null && !professors.isEmpty()) {
                    updateProfessorFields(professors);
                }
                List<Material> materials = Material.getLocalMaterials(modulId);
                if (materials != null && !materials.isEmpty()) {
                    Material material = materials.get(0); // Assuming one material for simplicity
                    youtubeButton.setOnAction(event -> openYouTubeVideo(material.getUrl()));
                    System.out.println(material.getUrl());
                    System.out.println("youtube successful2");
                } else {
                    System.out.println("No Link Found");
                    youtubeButton.setOnAction(event -> openYouTubeVideo("test"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the UI fields with the details of the module.
     *
     * @param modulDetails The details of the module to be displayed.
     */
    private void updateModulDetailsFields(ModulDetails modulDetails) {
        modulNameField.setText(modulDetails.getModulName());
        cpField.setText(String.valueOf(modulDetails.getCp()));
        dauerField.setText(String.valueOf(modulDetails.getDauer()));
        pruefungField.setText(modulDetails.getPruefung());
        semesterField.setText(String.valueOf(modulDetails.getSemester()));
        spracheField.setText(modulDetails.getSprache());
        koordinationField.setText(modulDetails.getKoordination());
        verfuegbarkeitField.setText(modulDetails.getVerfuegbarkeit());
        voraussetzungField.setText(modulDetails.getVoraussetzung());
        vorleistungField.setText(String.valueOf(modulDetails.isVorleistung()));
        inhaltField.setText(modulDetails.getInhalt());
    }

    /**
     * Updates the list of professors in the UI.
     *
     * @param professors The list of professors to be displayed.
     */
    private void updateProfessorFields(List<Professor> professors) {
        professorList.getChildren().clear();
        for (Professor professor : professors) {
            VBox professorBox = new VBox();
            professorBox.getStyleClass().add("professor-details");
            Label nameLabel = new Label("Name: " + professor.getVorname() + " " + professor.getNachname());
            Label emailLabel = new Label("Email: " + professor.getEmail());
            Label roomLabel = new Label("Sprechzimmer: " + professor.getSprechzimmer());
            professorBox.getChildren().addAll(nameLabel, emailLabel, roomLabel);
            professorList.getChildren().add(professorBox);
        }
    }

    /**
     * Opens a new window to display a YouTube video.
     *
     * @param url The URL of the YouTube video to be displayed.
     */
    private void openYouTubeVideo(String url) {
        WebView webView = new WebView();
        if (url.equals("test")) url = "https://www.youtube.com/embed/B4EkwueekvI?si=v3gL0FMvxaKbOZRr";
        webView.getEngine().load(url);
        webView.setPrefSize(640, 390);

        Stage newStage = new Stage();
        newStage.setTitle("YouTube Video");
        try {
            newStage.setScene(new Scene(webView));
            newStage.show();
        } catch (Exception e) {
            newStage.close();
        }
    }

    /**
     * Switches to the home page of the application.
     */
    public void switchToHome() {
        SceneController.switchToPage(4);
    }
}
