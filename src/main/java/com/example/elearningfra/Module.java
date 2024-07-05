package com.example.elearningfra;

import javafx.scene.control.Button;

/**
 * Represents a module with associated actions such as delete and edit.
 */
public class Module {

    private int id;
    private String name;
    private Button deleteButton;
    private Button editButton;

    /**
     * Constructs a new {@code Module} object.
     *
     * @param id           The ID of the module.
     * @param name         The name of the module.
     * @param deleteButton The button used to delete the module.
     * @param editButton   The button used to edit the module.
     */
    public Module(int id, String name, Button deleteButton, Button editButton) {
        this.id = id;
        this.name = name;
        this.deleteButton = deleteButton;
        this.editButton = editButton;
    }

    /**
     * Gets the ID of the module.
     *
     * @return The module ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the name of the module.
     *
     * @return The module name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the button used to delete the module.
     *
     * @return The delete button.
     */
    public Button getDeleteButton() {
        return deleteButton;
    }

    /**
     * Gets the button used to edit the module.
     *
     * @return The edit button.
     */
    public Button getEditButton() {
        return editButton;
    }
}
