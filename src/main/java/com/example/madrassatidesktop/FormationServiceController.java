package com.example.madrassatidesktop;

import com.example.madrassatidesktop.Entite.Formation;
import com.example.madrassatidesktop.Service.FormationService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.List;

public class FormationServiceController {

    // Assume the FormationService instance is injected or initialized elsewhere
    private FormationService formationService = FormationService.getInstance();

    @FXML
    private TextField idField;

    @FXML
    private TextField nomField;

    @FXML
    private TextField sujetField;

    @FXML
    private TextField descriptionField;

    @FXML
    private TextField idCategorieField;

    @FXML
    private void addFormation() {
        try {
            String nom = nomField.getText();
            String sujet = sujetField.getText();
            String description = descriptionField.getText();
            int idCategorie = Integer.parseInt(idCategorieField.getText());

            Formation formation = new Formation(nom, sujet, description, idCategorie);
            formationService.add(formation);

            showAlert(Alert.AlertType.INFORMATION, "Formation Added", "Formation added successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid number for idCategorie.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to add formation: " + e.getMessage());
        }
    }

    @FXML
    private void updateFormation() {
        try {
            int id = Integer.parseInt(idField.getText());
            String nom = nomField.getText();
            String sujet = sujetField.getText();
            String description = descriptionField.getText();
            int idCategorie = Integer.parseInt(idCategorieField.getText());

            Formation formation = new Formation(id, nom, sujet, description, idCategorie);
            formationService.update(formation);

            showAlert(Alert.AlertType.INFORMATION, "Formation Updated", "Formation updated successfully.");
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter valid numbers for id and idCategorie.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to update formation: " + e.getMessage());
        }
    }

    @FXML
    private void deleteFormation() {
        try {
            int id = Integer.parseInt(idField.getText());
            Formation formation = new Formation();
            formation.setId(id);

            boolean deleted = formationService.delete(formation);

            if (deleted) {
                showAlert(Alert.AlertType.INFORMATION, "Formation Deleted", "Formation deleted successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete formation: Formation not found.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid number for id.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete formation: " + e.getMessage());
        }
    }

    @FXML
    private void findById() {
        try {
            int id = Integer.parseInt(idField.getText());
            Formation formation = new Formation();
            formation.setId(id);

            Formation foundFormation = formationService.findById(formation);

            if (foundFormation != null) {
                showAlert(Alert.AlertType.INFORMATION, "Formation Found", "Found Formation: " + foundFormation);
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Formation not found.");
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please enter a valid number for id.");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to find formation: " + e.getMessage());
        }
    }

    @FXML
    private void findAll() {
        try {
            List<Formation> formations = formationService.findAll();
            StringBuilder result = new StringBuilder();
            for (Formation formation : formations) {
                result.append("ID: ").append(formation.getId()).append("\n");
                result.append("Nom: ").append(formation.getNom()).append("\n");
                result.append("Sujet: ").append(formation.getSujet()).append("\n");
                result.append("Description: ").append(formation.getDescription()).append("\n");
                result.append("ID Categorie: ").append(formation.getIdCategorie()).append("\n");
                result.append("\n");
            }
            showAlert(Alert.AlertType.INFORMATION, "All Formations", result.toString());
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to retrieve formations: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
