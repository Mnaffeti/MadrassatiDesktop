package com.example.madrassatidesktop;


import com.example.madrassatidesktop.Service.UtilisateurService;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
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
    @FXML
    private Label LoginLabel ;
    @FXML
    private MediaView mediaView;
    @FXML
    private VBox vbox;
    @FXML
    private TextField UserMailField ;
    @FXML
    private PasswordField UserPwdField ;


    @Override
   public void initialize(URL url, ResourceBundle resourceBundle) {
       if (!HelloApplication.isSplashLoaded) {
           loadSplashScreen();
       }

       String videoUrl = getClass().getResource("IntroVol2.mp4").toExternalForm(); // Ensure the path is correct
       Media media = new Media(videoUrl);
       MediaPlayer mediaPlayer = new MediaPlayer(media);
       mediaView.setMediaPlayer(mediaPlayer);
       mediaView.fitWidthProperty().bind(vbox.widthProperty());
       mediaView.fitHeightProperty().bind(vbox.heightProperty());
       mediaView.setPreserveRatio(false);
       mediaPlayer.setVolume(0);
       mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        mediaPlayer.play(); // Start playback automatically


   }
   @FXML
   private void LoginBtnOnAction(){
        LoginLabel.setText("Salem you clicked on login");
        String email = UserMailField.getText();
        String password = UserPwdField.getText();

       UtilisateurService service = UtilisateurService.getInstance();
       boolean isAuthenticated = service.authenticate(email, password);

       if (isAuthenticated) {
           // Authentication successful
           navigateToMainScreen();
       } else {
           LoginLabel.setText("LoginFailed");
           // Authentication failed
           showAlert("Login Failed", "Invalid email or password.");
       }
   }



        private void navigateToMainScreen() {
            try {
                // Load the FXML file for the main screen
                FXMLLoader loader = new FXMLLoader(getClass().getResource("DashBoardStudent.fxml")); // Make sure to provide the correct path
                Parent root = loader.load();

                // Get the current stage (window) using the login label
                Stage stage = (Stage) LoginLabel.getScene().getWindow();

                // Set the scene for the stage with the main screen layout
                stage.setScene(new Scene(root));

                // Optionally, set the title for the new stage
                stage.setTitle("Main Screen");

                // Show the new scene
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception (e.g., log the error, show an error alert)
            }
        }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



       @FXML
    private void handleSignInAction(ActionEvent event) throws IOException {
        // Load the Sign In view
        Parent signInParent = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene signInScene = new Scene(signInParent);

        // Get the stage from the event that triggered the method call
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the scene on the stage
        window.setScene(signInScene);
        window.show();
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
                   AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("MainWindow.fxml")));
                   root.getChildren().setAll(parentContent);
               } catch (IOException ex) {

               }
           });
       } catch (IOException ex) {

       }
   }


}
