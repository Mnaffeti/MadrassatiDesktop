package com.example.madrassatidesktop;


import com.example.madrassatidesktop.Entite.Formation;
import com.example.madrassatidesktop.Service.FormationService;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class FormationController {

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField sujetTextField;

    @FXML
    private TextField descriptionTextField;

    @FXML
    private TableView<Formation> formationTable;

    private FormationService formationService;

    public FormationController() {
        this.formationService = FormationService.getInstance();
    }

    @FXML
    void ajouterFormation() {
        String nom = nomTextField.getText();
        String sujet = sujetTextField.getText();
        String description = descriptionTextField.getText();
        int idCategorie = generateId(); // You need to implement a method to generate IDs
        Formation formation = new Formation(nom, sujet, description, idCategorie);
        try {
            formationService.add(formation);
            // Refresh table after adding
            formationTable.getItems().addAll(formation);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
    }

    // Method to generate a unique ID for formation
    private int generateId() {
        // You can implement your own logic for generating IDs
        return (int) (Math.random() * 1000);
    }
}

