package com.example.elearningfra;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloApplication extends Application {
//Hallo
    private static Stage ourstage;

    public static Stage getOurstage() {
        return ourstage;
    }
    @Override
    public void start(Stage stage) throws IOException {
        ourstage = stage;
        SceneController.setScenecontrollerstage(ourstage);
        SceneController.init(ourstage);
    }

    public static void main(String[] args) {

        launch();
    }
}