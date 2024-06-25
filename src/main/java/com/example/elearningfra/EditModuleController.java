package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.List;

public class EditModuleController {

    @FXML
    private TextField moduleNameField;
    @FXML
    private ComboBox<String> cpField;
    @FXML
    private TextField pruefungField;
    @FXML
    private ComboBox<String> semesterField;
    @FXML
    private TextField spracheField;
    @FXML
    private TextField koordinationField;
    @FXML
    private TextField verfuegbarkeitField;
    @FXML
    private TextField voraussetzungField;
    @FXML
    private CheckBox vorleistungField;
    @FXML
    private TextField inhaltField;
    @FXML
    private VBox professorContainer;
    @FXML
    private VBox materialContainer;
    @FXML
    private Button saveButton;

    private int modulId;

    @FXML
    private void initialize() {
        // Semester alanı için varsayılan değeri 1 olarak ayarla
        semesterField.getItems().setAll("1", "2", "3", "4", "5", "6");
        semesterField.setValue("1");

        // CP alanı için varsayılan değeri 5 olarak ayarla
        cpField.getItems().setAll("5", "10", "15");
        cpField.setValue("5");
    }

    public void loadModuleDetails(int modulId) throws SQLException {
        this.modulId = modulId;
        ModulDetails modulDetails = Database.getModulDetails(modulId);

        if (modulDetails != null) {
            moduleNameField.setText(modulDetails.getModulName());
            cpField.setValue(String.valueOf(modulDetails.getCp()));
            pruefungField.setText(modulDetails.getPruefung());
            semesterField.setValue(String.valueOf(modulDetails.getSemester()));
            spracheField.setText(modulDetails.getSprache());
            koordinationField.setText(modulDetails.getKoordination());
            verfuegbarkeitField.setText(modulDetails.getVerfuegbarkeit());
            voraussetzungField.setText(modulDetails.getVoraussetzung());
            vorleistungField.setSelected(modulDetails.isVorleistung());
            inhaltField.setText(modulDetails.getInhalt());

            List<Professor> professors = Database.getProfessorsForModul(modulId);
            for (Professor professor : professors) {
                addProfessorToUI(professor);
            }

            List<Material> materials = Database.getMaterialsForModul(modulId);
            for (Material material : materials) {
                addMaterialToUI(material);
            }
        }
    }

    private void addProfessorToUI(Professor professor) {
        TextField nachnameField = new TextField(professor.getNachname());
        TextField vornameField = new TextField(professor.getVorname());
        TextField emailField = new TextField(professor.getEmail());
        TextField sprechzimmerField = new TextField(professor.getSprechzimmer());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            professorContainer.getChildren().remove(nachnameField);
            professorContainer.getChildren().remove(vornameField);
            professorContainer.getChildren().remove(emailField);
            professorContainer.getChildren().remove(sprechzimmerField);
            professorContainer.getChildren().remove(deleteButton);
            try {
                Database.deleteProfessor(professor.getProfessorId());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        professorContainer.getChildren().addAll(
                new Label("Nachname:"), nachnameField,
                new Label("Vorname:"), vornameField,
                new Label("Email:"), emailField,
                new Label("Sprechzimmer:"), sprechzimmerField,
                deleteButton
        );
    }

    private void addMaterialToUI(Material material) {
        TextField typField = new TextField(material.getTyp());
        TextField themaField = new TextField(material.getThema());
        TextField beschreibungField = new TextField(material.getBeschreibung());
        TextField urlField = new TextField(material.getUrl());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            materialContainer.getChildren().remove(typField);
            materialContainer.getChildren().remove(themaField);
            materialContainer.getChildren().remove(beschreibungField);
            materialContainer.getChildren().remove(urlField);
            materialContainer.getChildren().remove(deleteButton);
            try {
                Database.deleteMaterial(material.getMaterialId());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        materialContainer.getChildren().addAll(
                new Label("Typ:"), typField,
                new Label("Thema:"), themaField,
                new Label("Beschreibung:"), beschreibungField,
                new Label("URL:"), urlField,
                deleteButton
        );
    }

    @FXML
    private void handleSaveButton() {
        String moduleName = moduleNameField.getText();
        int cp = Integer.parseInt(cpField.getValue());
        String pruefung = pruefungField.getText();
        int semester = Integer.parseInt(semesterField.getValue());
        String sprache = spracheField.getText();
        String koordination = koordinationField.getText();
        String verfuegbarkeit = verfuegbarkeitField.getText();
        String voraussetzung = voraussetzungField.getText();
        boolean vorleistung = vorleistungField.isSelected();
        String inhalt = inhaltField.getText();

        try {
            Database.updateModule(modulId, moduleName, cp, pruefung, semester, sprache, koordination, verfuegbarkeit, voraussetzung, vorleistung, inhalt);

            // Alanları değiştirilemez yap
            setFieldsDisabled(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setFieldsDisabled(boolean disabled) {
        moduleNameField.setDisable(disabled);
        cpField.setDisable(disabled);
        pruefungField.setDisable(disabled);
        semesterField.setDisable(disabled);
        spracheField.setDisable(disabled);
        koordinationField.setDisable(disabled);
        verfuegbarkeitField.setDisable(disabled);
        voraussetzungField.setDisable(disabled);
        vorleistungField.setDisable(disabled);
        inhaltField.setDisable(disabled);
        professorContainer.setDisable(disabled);
        materialContainer.setDisable(disabled);
        saveButton.setDisable(disabled);
    }

    public void back() throws SQLException {
        MainController mainController;
        FXMLLoader loader = SceneController.getLoader(6);
        mainController = loader.getController();
        mainController.loadModules();
        SceneController.switchToPage(6);
        resetFields();
    }

    private void resetFields() {
        moduleNameField.clear();
        cpField.setValue("5");
        pruefungField.clear();
        semesterField.setValue("1");
        spracheField.clear();
        koordinationField.clear();
        verfuegbarkeitField.clear();
        voraussetzungField.clear();
        vorleistungField.setSelected(false);
        inhaltField.clear();
        professorContainer.getChildren().clear();
        materialContainer.getChildren().clear();
        setFieldsDisabled(false);
    }
}