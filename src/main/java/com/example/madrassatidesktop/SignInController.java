package com.example.madrassatidesktop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {
    @FXML
    private void handleCancelAction(ActionEvent event) {
        try {
            // Load the Main Window view
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);

            // Get the stage from the event that triggered the method call
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene on the stage to switch back to the Main Window
            window.setScene(mainWindowScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
