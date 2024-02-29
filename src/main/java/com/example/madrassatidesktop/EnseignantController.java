package com.example.madrassatidesktop

import com.example.madrassatidesktop.Entite.Enseignant;
import com.example.madrassatidesktop.Service.EnseignantService;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class EnseignantController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private TextField specialiteTextField;

    @FXML
    private TableView<Enseignant> enseignantTable;

    private EnseignantService enseignantService;

    public EnseignantController() {
        this.enseignantService = EnseignantService.getInstance();
    }

    @FXML
    void ajouterEnseignant() {
        String nom = nomTextField.getText();
        String prenom = prenomTextField.getText();
        String specialite = specialiteTextField.getText();
        int idUtilisateur = generateId(); // You need to implement a method to generate IDs
        Enseignant enseignant = new Enseignant(nom, prenom, specialite, idUtilisateur);
        try {
            enseignantService.add(enseignant);
            // Refresh table after adding
            enseignantTable.getItems().addAll(enseignant);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // Method to generate a unique ID for enseignant
    private int generateId() {
        // You can implement your own logic for generating IDs
        return (int) (Math.random() * 1000);
    }
}
