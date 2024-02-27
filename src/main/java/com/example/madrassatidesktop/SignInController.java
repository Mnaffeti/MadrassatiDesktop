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

            // Get the stage from the event that triggered the method call
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the scene on the stage to switch back to the Main Window
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
            }
            else
            {
              //  int tmpid=service.getUserIdByEmail(u.getemail());
              //  System.out.println(tmpid);
              //  Enseignant ens = new Enseignant(nom, prenom,"a",1,tmpid);
               // EtudiantService etudiantService=EtudiantService.getInstance();
               // etudiantService.add(etudiant);

            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            showAlert("SQL ALERT",e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
