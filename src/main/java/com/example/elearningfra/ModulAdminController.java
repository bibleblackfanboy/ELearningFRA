package com.example.elearningfra;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.Map;

/**
 * This class serves as the controller for the module administration view.
 * It handles the interactions for displaying, editing, and deleting modules.
 */
public class ModulAdminController {

    @FXML
    private TableView<Module> moduleTable;

    @FXML
    private TableColumn<Module, Integer> idColumn;

    @FXML
    private TableColumn<Module, String> nameColumn;

    @FXML
    private TableColumn<Module, Button> deleteColumn;

    @FXML
    private TableColumn<Module, Button> editColumn;

    @FXML
    private Button addButton;

    private ObservableList<Module> moduleList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * Sets up the table columns and loads the module data.
     */
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        deleteColumn.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
        editColumn.setCellValueFactory(new PropertyValueFactory<>("editButton"));

        try {
            loadModules();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads the module data from the database and populates the table.
     *
     * @throws SQLException if a database access error occurs
     */
    public void loadModules() throws SQLException {
        moduleList.clear();
        Map<Integer, String> modules = Database.getAllModuleNames();
        for (Map.Entry<Integer, String> entry : modules.entrySet()) {
            int id = entry.getKey();
            String name = entry.getValue();
            Button deleteButton = new Button("Delete");
            deleteButton.setOnAction(e -> handleDeleteButton(id));
            Button editButton = new Button("Edit");
            editButton.setOnAction(e -> {
                try {
                    handleEditButton(id);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            moduleList.add(new Module(id, name, deleteButton, editButton));
        }
        moduleTable.setItems(moduleList);
    }

    /**
     * Handles the action for the delete button.
     * Deletes the module from the database and updates the table.
     *
     * @param id the ID of the module to be deleted
     */
    private void handleDeleteButton(int id) {
        try {
            Database.deleteModule(id);
            moduleList.removeIf(module -> module.getId() == id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the action for the edit button.
     * Loads the details of the selected module into the edit module controller and switches to the edit page.
     *
     * @param id the ID of the module to be edited
     * @throws SQLException if a database access error occurs
     */
    private void handleEditButton(int id) throws SQLException {
        EditModuleController editModuleController;
        FXMLLoader loader = SceneController.getLoader(8);
        editModuleController = loader.getController();
        editModuleController.loadModuleDetails(id);
        SceneController.switchToPage(8);
    }

    /**
     * Handles the action for the add button.
     * Switches to the page for adding a new module.
     */
    @FXML
    private void handleAddButton() {
        SceneController.switchToPage(7);
    }

    /**
     * Handles the action for the back button.
     * Switches back to the main page.
     */
    @FXML
    private void back() {
        SceneController.switchToPage(0);
    }
}
