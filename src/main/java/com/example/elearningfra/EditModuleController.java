package com.example.elearningfra;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for editing module details in the e-learning application.
 */
public class EditModuleController {
    @FXML private TextField moduleNameField;
    @FXML private ComboBox<String> cpField;
    @FXML private TextField pruefungField;
    @FXML private ComboBox<String> semesterField;
    @FXML private TextField spracheField;
    @FXML private TextField koordinationField;
    @FXML private TextField verfuegbarkeitField;
    @FXML private TextField voraussetzungField;
    @FXML private CheckBox vorleistungField;
    @FXML private TextField inhaltField;
    @FXML private VBox professorContainer;
    @FXML private VBox materialContainer;
    @FXML private Button saveButton;
    @FXML private Button addProfessorButton;
    @FXML private Button addMaterialButton;
    @FXML private Button backButton;
    @FXML private Label errorMessageLabel;
    @FXML private Label moduleNameLabel;
    @FXML private Label koordinationLabel;
    @FXML private Label voraussetzungLabel;

    private int modulId;
    private List<Professor> professors = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    public void initialize() {
        cpField.getItems().addAll("5", "10", "15", "20");
        cpField.setValue("5");
        semesterField.getItems().addAll("1", "2", "3", "4", "5", "6");
        semesterField.setValue("1");
        setFieldsDisabled(false);
    }

    /**
     * Loads the module details based on the provided module ID.
     * @param modulId the ID of the module to load
     */
    public void loadModuleDetails(int modulId) {
        this.modulId = modulId;
        try {
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
                professors = Database.getProfessorsForModul(modulId);
                materials = Database.getMaterialsForModul(modulId);
                professorContainer.getChildren().clear();
                for (Professor professor : professors) {
                    addProfessorToUI(professor);
                }
                materialContainer.getChildren().clear();
                for (Material material : materials) {
                    addMaterialToUI(material);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a professor's details to the user interface.
     * @param professor the professor to add
     */
    private void addProfessorToUI(Professor professor) {
        TextField nachnameField = new TextField(professor.getNachname());
        TextField vornameField = new TextField(professor.getVorname());
        TextField emailField = new TextField(professor.getEmail());
        TextField sprechzimmerField = new TextField(professor.getSprechzimmer());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            professorContainer.getChildren().removeAll(
                    nachnameField, vornameField, emailField, sprechzimmerField, deleteButton
            );
            try {
                Database.deleteProfessor(professor.getProfessorId());
                professors.remove(professor);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        nachnameField.textProperty().addListener((observable, oldValue, newValue) -> professor.setNachname(newValue));
        vornameField.textProperty().addListener((observable, oldValue, newValue) -> professor.setVorname(newValue));
        emailField.textProperty().addListener((observable, oldValue, newValue) -> professor.setEmail(newValue));
        sprechzimmerField.textProperty().addListener((observable, oldValue, newValue) -> professor.setSprechzimmer(newValue));
        professorContainer.getChildren().addAll(
                new Label("Nachname:"), nachnameField,
                new Label("Vorname:"), vornameField,
                new Label("Email:"), emailField,
                new Label("Sprechzimmer:"), sprechzimmerField,
                deleteButton
        );
    }

    /**
     * Adds material details to the user interface.
     * @param material the material to add
     */
    private void addMaterialToUI(Material material) {
        TextField typField = new TextField(material.getTyp());
        TextField themaField = new TextField(material.getThema());
        TextField beschreibungField = new TextField(material.getBeschreibung());
        TextField urlField = new TextField(material.getUrl());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            materialContainer.getChildren().removeAll(
                    typField, themaField, beschreibungField, urlField, deleteButton
            );  //this part is partly from ChatGPT
            try {
                Database.deleteMaterial(material.getMaterialId());
                materials.remove(material);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        typField.textProperty().addListener((observable, oldValue, newValue) -> material.setTyp(newValue));
        themaField.textProperty().addListener((observable, oldValue, newValue) -> material.setThema(newValue));
        beschreibungField.textProperty().addListener((observable, oldValue, newValue) -> material.setBeschreibung(newValue));
        urlField.textProperty().addListener((observable, oldValue, newValue) -> material.setUrl(newValue));
        materialContainer.getChildren().addAll(
                new Label("Typ:"), typField,
                new Label("Thema:"), themaField,
                new Label("Beschreibung:"), beschreibungField,
                new Label("URL:"), urlField,
                deleteButton
        );
    }

    /**
     * Handles the save button action to update the module details.
     */
    //partly from ChatGPT
    @FXML
    private void handleSaveButton() {
        boolean hasError = false;
        errorMessageLabel.setVisible(false);

        if (moduleNameField.getText().isEmpty()) {
            moduleNameLabel.getStyleClass().add("error-label");
            hasError = true;
        } else {
            moduleNameLabel.getStyleClass().removeAll("error-label");
        }

        if (koordinationField.getText().isEmpty()) {
            koordinationLabel.getStyleClass().add("error-label");
            hasError = true;
        } else {
            koordinationLabel.getStyleClass().removeAll("error-label");
        }

        if (voraussetzungField.getText().isEmpty()) {
            voraussetzungLabel.getStyleClass().add("error-label");
            hasError = true;
        } else {
            voraussetzungLabel.getStyleClass().removeAll("error-label");
        }

        if (hasError) {
            errorMessageLabel.setText("Please fill in all required fields.");
            errorMessageLabel.setVisible(true);
            return;
        }

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
            for (Professor professor : professors) {
                Database.updateProfessor(professor);
            }
            for (Material material : materials) {
                Database.updateMaterial(material);
            }
            for (Professor professor : professors) {
                if (professor.getProfessorId() == 0) {
                    Database.addProfessor(professor, modulId);
                }
            }
            for (Material material : materials) {
                if (material.getMaterialId() == 0) {
                    Database.addMaterial(material, modulId);
                }
            }
            setFieldsDisabled(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action of adding a new professor.
     */
    @FXML
    private void handleAddProfessor() {
        Professor newProfessor = new Professor(0, "", "", "", "");
        professors.add(newProfessor);
        addProfessorToUI(newProfessor);
    }

    /**
     * Handles the action of adding new material.
     */
    @FXML
    private void handleAddMaterial() {
        Material newMaterial = new Material(0, modulId, "", "", "", "");
        materials.add(newMaterial);
        addMaterialToUI(newMaterial);
    }

    /**
     * Disables or enables the fields based on the provided flag.
     * @param disabled true to disable the fields, false to enable them
     */
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
        addProfessorButton.setDisable(disabled);
        addMaterialButton.setDisable(disabled);
    }

    /**
     * Handles the back button action to return to the module admin page.
     * @throws SQLException if there is an error loading the module admin page
     */
    @FXML
    private void back() throws SQLException {
        ModulAdminController modulAdminController;
        FXMLLoader loader = SceneController.getLoader(6);
        modulAdminController = loader.getController();
        modulAdminController.loadModules();
        SceneController.switchToPage(6);
        resetFields();
    }

    /**
     * Resets the fields to their default state.
     */
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
        professors.clear();
        materials.clear();
        setFieldsDisabled(false);
    }
}



