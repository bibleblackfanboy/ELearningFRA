package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddModuleController {

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
    private Button addProfessorButton;
    @FXML
    private Button addMaterialButton;
    @FXML
    private Button saveButton;

    private List<Professor> professors = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();

    @FXML
    private void initialize() {
        // Semester alanı için varsayılan değeri 1 olarak ayarla
        semesterField.getItems().setAll("1", "2", "3", "4", "5", "6");
        semesterField.setValue("1");

        // CP alanı için varsayılan değeri 5 olarak ayarla
        cpField.getItems().setAll("5", "10", "15");
        cpField.setValue("5");
    }

    @FXML
    private void handleSaveButton() {
        String moduleName = moduleNameField.getText().isEmpty() ? null : moduleNameField.getText();
        int cp = Integer.parseInt(cpField.getValue());
        String pruefung = pruefungField.getText().isEmpty() ? null : pruefungField.getText();
        int semester = Integer.parseInt(semesterField.getValue());
        String sprache = spracheField.getText().isEmpty() ? null : spracheField.getText();
        String koordination = koordinationField.getText().isEmpty() ? null : koordinationField.getText();
        String verfuegbarkeit = verfuegbarkeitField.getText().isEmpty() ? null : verfuegbarkeitField.getText();
        String voraussetzung = voraussetzungField.getText().isEmpty() ? null : voraussetzungField.getText();
        boolean vorleistung = vorleistungField.isSelected();
        String inhalt = inhaltField.getText().isEmpty() ? null : inhaltField.getText();

        try {
            Database.addModule(moduleName, cp, pruefung, semester, sprache, koordination, verfuegbarkeit, voraussetzung, vorleistung, inhalt);
            int moduleId = Database.getLastInsertedModuleId(); // Assuming you have a method to get the last inserted module ID

            for (int i = 0; i < professorContainer.getChildren().size(); i += 8) {
                TextField nachnameField = (TextField) professorContainer.getChildren().get(i + 1);
                TextField vornameField = (TextField) professorContainer.getChildren().get(i + 3);
                TextField emailField = (TextField) professorContainer.getChildren().get(i + 5);
                TextField sprechzimmerField = (TextField) professorContainer.getChildren().get(i + 7);
                Professor professor = new Professor(0, nachnameField.getText(), vornameField.getText(), emailField.getText(), sprechzimmerField.getText());
                Database.addProfessor(professor, moduleId);
            }

            for (int i = 0; i < materialContainer.getChildren().size(); i += 8) {
                TextField typField = (TextField) materialContainer.getChildren().get(i + 1);
                TextField themaField = (TextField) materialContainer.getChildren().get(i + 3);
                TextField beschreibungField = (TextField) materialContainer.getChildren().get(i + 5);
                TextField urlField = (TextField) materialContainer.getChildren().get(i + 7);
                Material material = new Material(0, 0, typField.getText(), themaField.getText(), beschreibungField.getText(), urlField.getText());
                Database.addMaterial(material, moduleId);
            }

            // Alanları değiştirilemez yap
            setFieldsDisabled(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddProfessorButton() {
        // Add UI elements to enter professor details
        TextField nachnameField = new TextField();
        TextField vornameField = new TextField();
        TextField emailField = new TextField();
        TextField sprechzimmerField = new TextField();
        professorContainer.getChildren().addAll(
                new Label("Nachname:"), nachnameField,
                new Label("Vorname:"), vornameField,
                new Label("Email:"), emailField,
                new Label("Sprechzimmer:"), sprechzimmerField
        );
    }

    @FXML
    private void handleAddMaterialButton() {
        // Add UI elements to enter material details
        TextField typField = new TextField();
        TextField themaField = new TextField();
        TextField beschreibungField = new TextField();
        TextField urlField = new TextField();
        materialContainer.getChildren().addAll(
                new Label("Typ:"), typField,
                new Label("Thema:"), themaField,
                new Label("Beschreibung:"), beschreibungField,
                new Label("URL:"), urlField
        );
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
        addProfessorButton.setDisable(disabled);
        addMaterialButton.setDisable(disabled);
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

