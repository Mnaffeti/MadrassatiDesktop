package com.example.madrassatidesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static Boolean isSplashLoaded = false;
    @Override
    public void start(Stage stage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("testewi.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Madrassati");
        stage.setHeight(520);
        stage.setWidth(800);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}