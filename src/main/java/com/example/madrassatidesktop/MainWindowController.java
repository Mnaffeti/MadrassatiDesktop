package com.example.madrassatidesktop;


import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private AnchorPane root ;
   /* private void LoadSplashScreen() throws IOException {
        StackPane pane= FXMLLoader.load(getClass().getResource("Splash.FXML"));
        root.getChildren().setAll(pane) ;
    }*/
    private  static  BorderPane rootp ;
   @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
       if (!HelloApplication.isSplashLoaded) {
           loadSplashScreen();
       }



   }
   private void loadSplashScreen() {
       try {
           HelloApplication.isSplashLoaded=true ;
           //Load splash screen view FXML
           StackPane pane = FXMLLoader.load(getClass().getResource(("Splash.fxml")));
           //Add it to root container (Can be StackPane, AnchorPane etc)
           root.getChildren().setAll(pane);

           //Load splash screen with fade in effect
           FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
           fadeIn.setFromValue(0);
           fadeIn.setToValue(1);
           fadeIn.setCycleCount(1);

           //Finish splash with fade out effect
           FadeTransition fadeOut = new FadeTransition(Duration.seconds(5), pane);
           fadeOut.setFromValue(1);
           fadeOut.setToValue(0);
           fadeOut.setCycleCount(1);

           fadeIn.play();

           //After fade in, start fade out
           fadeIn.setOnFinished((e) -> {
               fadeOut.play();
           });

           //After fade out, load actual content
           fadeOut.setOnFinished((e) -> {
               try {
                   AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("testewi.fxml")));
                   root.getChildren().setAll(parentContent);
               } catch (IOException ex) {

               }
           });
       } catch (IOException ex) {

       }
   }


}
