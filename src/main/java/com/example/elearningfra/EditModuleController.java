package com.example.elearningfra;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.sql.SQLException;
import java.util.ArrayList;
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
    @FXML
    private Button addProfessorButton;
    @FXML
    private Button addMaterialButton;
    @FXML
    private Button backButton;
    @FXML
    private Label errorMessage;

    private int modulId;
    private List<Professor> professors = new ArrayList<>();
    private List<Material> materials = new ArrayList<>();

    public void initialize() {
        cpField.getItems().addAll("5", "10", "15", "20");
        cpField.setValue("5");
        semesterField.getItems().addAll("1", "2", "3", "4", "5", "6");
        semesterField.setValue("1");
        setFieldsDisabled(false);  // Sayfa açıldığında alanları aktif hale getiriyoruz
    }

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

    @FXML
    private void handleSaveButton() {
        String moduleName = moduleNameField.getText();
        String koordination = koordinationField.getText();
        String voraussetzung = voraussetzungField.getText();

        // Required fields validation
        boolean isValid = true;

        if (moduleName.isEmpty()) {
            moduleNameField.getStyleClass().add("text-field-error");
            isValid = false;
        } else {
            moduleNameField.getStyleClass().remove("text-field-error");
        }

        if (koordination.isEmpty()) {
            koordinationField.getStyleClass().add("text-field-error");
            isValid = false;
        } else {
            koordinationField.getStyleClass().remove("text-field-error");
        }

        if (voraussetzung.isEmpty()) {
            voraussetzungField.getStyleClass().add("text-field-error");
            isValid = false;
        } else {
            voraussetzungField.getStyleClass().remove("text-field-error");
        }

        boolean atLeastOneProfessorValid = professors.stream().anyMatch(p -> !p.getNachname().isEmpty());
        if (!atLeastOneProfessorValid) {
            errorMessage.setText("Please add at least one professor with a valid last name.");
            errorMessage.setVisible(true);
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                errorMessage.setVisible(false);
            });
            isValid = false;
        }

        if (!isValid) {
            errorMessage.setText("Please fill in all required fields.");
            errorMessage.setVisible(true);
            Platform.runLater(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                errorMessage.setVisible(false);
            });
            return;
        }

        try {
            int cp = Integer.parseInt(cpField.getValue());
            String pruefung = pruefungField.getText();
            int semester = Integer.parseInt(semesterField.getValue());
            String sprache = spracheField.getText();
            String verfuegbarkeit = verfuegbarkeitField.getText();
            boolean vorleistung = vorleistungField.isSelected();
            String inhalt = inhaltField.getText();

            // Güncellemeleri yapıyoruz
            Database.updateModule(modulId, moduleName, cp, pruefung, semester, sprache, koordination, verfuegbarkeit, voraussetzung, vorleistung, inhalt);

            // Mevcut professor ve material bilgilerini güncelliyoruz
            for (Professor professor : professors) {
                Database.updateProfessor(professor);
            }

            for (Material material : materials) {
                Database.updateMaterial(material);
            }

            // Yeni eklenen professor ve material bilgilerini ekliyoruz
            for (Professor professor : professors) {
                if (professor.getProfessorId() == 0) {  // Yeni eklenen professor
                    Database.addProfessor(professor, modulId);
                }
            }

            for (Material material : materials) {
                if (material.getMaterialId() == 0) {  // Yeni eklenen material
                    Database.addMaterial(material, modulId);
                }
            }

            setFieldsDisabled(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddProfessor() {
        Professor newProfessor = new Professor(0, "", "", "", "");  // Dummy values
        professors.add(newProfessor);
        addProfessorToUI(newProfessor);
    }

    @FXML
    private void handleAddMaterial() {
        Material newMaterial = new Material(0, modulId, "", "", "", "");  // Dummy values
        materials.add(newMaterial);
        addMaterialToUI(newMaterial);
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
        addProfessorButton.setDisable(disabled);
        addMaterialButton.setDisable(disabled);
        // backButton artık deaktive edilmeyecek
    }

    @FXML
    private void back() throws SQLException {
        ModulAdminController modulAdminController;
        FXMLLoader loader = SceneController.getLoader(6);
        modulAdminController = loader.getController();
        modulAdminController.loadModules();
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
        professors.clear();
        materials.clear();
        setFieldsDisabled(false);  // Geri dönerken formu aktif hale getiriyoruz
        errorMessage.setVisible(false); // Hata mesajını gizle
    }
}





