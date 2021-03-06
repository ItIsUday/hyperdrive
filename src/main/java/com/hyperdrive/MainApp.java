package com.hyperdrive;

import com.hyperdrive.utils.Constants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle(Constants.APP_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}