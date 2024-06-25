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

public class MainController {

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

    public void loadModules() throws SQLException {
        moduleList.clear();
        Map<Integer, String> modules = Database.getAllModuleNames(); // Example semester value
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

    private void handleDeleteButton(int id) {
        try {
            Database.deleteModule(id);
            moduleList.removeIf(module -> module.getId() == id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void handleEditButton(int id) throws SQLException {
        EditModuleController editModuleController;
        FXMLLoader loader = SceneController.getLoader(8);
        editModuleController = loader.getController();
        editModuleController.loadModuleDetails(id);
        SceneController.switchToPage(8);
    }

    @FXML
    private void handleAddButton() {
        SceneController.switchToPage(7);
    }

    @FXML
    private void back()
    {
        SceneController.switchToPage(0);
    }
}
