package com.example.elearningfra;

import javafx.scene.control.Button;

public class Module {
    private int id;
    private String name;
    private Button deleteButton;
    private Button editButton;

    public Module(int id, String name, Button deleteButton, Button editButton) {
        this.id = id;
        this.name = name;
        this.deleteButton = deleteButton;
        this.editButton = editButton;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Button getDeleteButton() {
        return deleteButton;
    }

    public Button getEditButton() {
        return editButton;
    }
}