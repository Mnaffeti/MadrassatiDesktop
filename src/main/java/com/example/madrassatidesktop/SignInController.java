package com.example.madrassatidesktop;

import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Entite.Etudiant;
import com.example.madrassatidesktop.Entite.Utilisateur;
import com.example.madrassatidesktop.Service.EtudiantService;
import com.example.madrassatidesktop.Service.UtilisateurService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Thread.sleep;

public class SignInController {
    @FXML
    private TextField BirthDateField;
    @FXML
    private RadioButton teacherChecked;
    @FXML
    private RadioButton StudentChecked;
    @FXML
    private TextField NameField;

    @FXML
    private PasswordField PasswordField;

    @FXML
    private Button SigninBtn;

    @FXML
    private TextField SurnameField;

    @FXML
    private TextField emailField;



    @FXML
    private void handleCancelAction(ActionEvent event) {
        try {
            // Load the Main Window view
            Parent mainWindowParent = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            Scene mainWindowScene = new Scene(mainWindowParent);


            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();


            window.setScene(mainWindowScene);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void handleSignin(ActionEvent event) throws SQLException {
        try {
            String pwd = PasswordField.getText();
            String mail = emailField.getText();
            String nom=NameField.getText();
            String prenom=SurnameField.getText();
            int age= Integer.parseInt(BirthDateField.getText());
           //boolean w=CheckParam(pwd,mail,nom,prenom,age) ;
           //if(!w){return;}
            UtilisateurService service = UtilisateurService.getInstance();
            Utilisateur u = new Utilisateur(mail, pwd);
            service.add(u);
            sleep(3000);

            if (StudentChecked.isSelected()) {
                int tmpid=service.getUserIdByEmail(u.getemail());
                System.out.println(tmpid);
                Etudiant etudiant = new Etudiant(nom, prenom,age,1,tmpid);
                EtudiantService etudiantService=EtudiantService.getInstance();
                etudiantService.add(etudiant);
                showMsg("Notif","Student sucessfully added");
            }
            else
            {
              //todo

            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            showAlert("SQL ALERT",e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean CheckParam(String pwd, String mail, String nom, String prenom, int age) {

            // Check if any string parameter is null or empty
            if (pwd == null || pwd.trim().isEmpty()) {
               showAlert("Alert ","problem in password");
               return false;
            }
            if (mail == null || mail.trim().isEmpty()) {
                showAlert("Alert ","problem in mail");
                return false;
            }
            if (nom == null || nom.trim().isEmpty()) {
                showAlert("Alert ","problem in name");
                return false;
            }
            if (prenom == null || prenom.trim().isEmpty()) {
                showAlert("Alert ","problem in prenom");
                return false ;
            }

            // Check if the password meets your application's requirements (e.g., length, complexity)
            if (pwd.length() < 8) { // Example: check if the password is at least 8 characters long
                showAlert("Alert ","problem in password");
                return false ;
            }

            // Validate the email format (this is a simple pattern; you might need a more complex one)
            if (!mail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
                showAlert("Alert ","invalid mail format");
                return  false ;
            }

            // Check if the age is within a reasonable range
            if (age < 0 || age > 150) { // Example: age must be between 0 and 150
                showAlert("Alert ","age must be between 0 and 150");
                return false;
            }
            return true ;
        }



    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void showMsg(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
