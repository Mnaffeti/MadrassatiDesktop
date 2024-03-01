package com.example.madrassatidesktop;

import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Service.EnseignantService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EnseignantFormController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField specialiteTextField;

    private EnseignantService enseignantService;

    private Enseignant teacherToUpdate;
    public EnseignantFormController() {
        this.enseignantService = EnseignantService.getInstance();
    }

    public void initData(Enseignant teacher) {
        this.teacherToUpdate = teacher;
        nomTextField.setText(teacher.getNom());
        prenomTextField.setText(teacher.getPrenom());
        specialiteTextField.setText(teacher.getSpecialite());
    }

    @FXML
    void addTeacher() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String specialite = specialiteTextField.getText();


        if (nom.isEmpty() || prenom.isEmpty() || specialite.isEmpty()) {

            return;
        }


        Enseignant newTeacher = new Enseignant(nom, prenom, specialite, 0); // Assuming idUtilisateur will be generated automatically

        try {

            enseignantService.add(newTeacher);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @FXML
    void updateTeacher() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String specialite = specialiteTextField.getText();


        if (nom.isEmpty() || prenom.isEmpty() || specialite.isEmpty()) {

            return;
        }


        teacherToUpdate.setNom(nom);
        teacherToUpdate.setPrenom(prenom);
        teacherToUpdate.setSpecialite(specialite);
        try {

            enseignantService.update(teacherToUpdate);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
