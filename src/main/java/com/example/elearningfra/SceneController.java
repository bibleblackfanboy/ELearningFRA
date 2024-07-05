package com.example.elearningfra;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Manages the scenes in the application, including scene initialization and switching between scenes.
 */
public class SceneController {

    private static FXMLLoader[] loaders = new FXMLLoader[10];
    private static Parent[] roots = new Parent[10];
    private static Scene[] scenes = new Scene[10];

    private static Stage scenecontrollerstage;

    /**
     * Retrieves the {@code FXMLLoader} for a specific index.
     *
     * @param index The index of the loader.
     * @return The {@code FXMLLoader} for the specified index.
     * @throws IndexOutOfBoundsException if the index is out of bounds.
     */
    public static FXMLLoader getLoader(int index) {
        if (index >= 0 && index < loaders.length) {
            return loaders[index];
        } else {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    /**
     * Gets the current {@code Stage} used for scene management.
     *
     * @return The current {@code Stage}.
     */
    public static Stage getScenecontrollerstage() {
        return scenecontrollerstage;
    }

    /**
     * Sets the {@code Stage} for scene management.
     *
     * @param stage The {@code Stage} to be set.
     */
    public static void setScenecontrollerstage(Stage stage) {
        scenecontrollerstage = stage;
    }

    /**
     * Initializes the scenes for the application and sets up the initial stage.
     *
     * @param stage The primary stage for this application.
     * @throws IOException if loading the FXML files fails.
     */
    public static void init(Stage stage) throws IOException {
        loaders[0] = new FXMLLoader(SceneController.class.getResource("homepage.fxml"));
        roots[0] = loaders[0].load();
        scenes[0] = new Scene(roots[0], 800, 600);
        stage.setScene(scenes[0]);
        stage.setMinWidth(800);
        stage.setMinHeight(600);

        loaders[1] = new FXMLLoader(SceneController.class.getResource("adminlogin.fxml"));
        roots[1] = loaders[1].load();

        loaders[4] = new FXMLLoader(SceneController.class.getResource("modulselect.fxml"));
        roots[4] = loaders[4].load();

        loaders[5] = new FXMLLoader(SceneController.class.getResource("modulcontent.fxml"));
        roots[5] = loaders[5].load();

        loaders[6] = new FXMLLoader(SceneController.class.getResource("moduladmin.fxml"));
        roots[6] = loaders[6].load();

        loaders[7] = new FXMLLoader(SceneController.class.getResource("add.fxml"));
        roots[7] = loaders[7].load();

        loaders[8] = new FXMLLoader(SceneController.class.getResource("edit.fxml"));
        roots[8] = loaders[8].load();

        stage.show();
    }

    /**
     * Switches to a different page based on the given index.
     *
     * @param i The index of the scene to switch to.
     */
    public static void switchToPage(int i) {
        try {
            if (scenes[i] == null) {
                System.out.println("new scene");
                scenes[i] = new Scene(roots[i]);
                scenecontrollerstage.setScene(scenes[i]);
            } else {
                System.out.println("old scene");
                scenes[i].setRoot(roots[i]);
                scenecontrollerstage.setScene(scenes[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
